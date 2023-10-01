# Tests

## Установка и запуск
1. Из корня родительского проекта и компилим нужный проект `.\mvnw clean compile -pl spring-tests`
2. Создаем docker image `cd spring-tests && docker build -t hexhoc/spring-tests .`
3. Запускаем docker images `cd docker-compose/spring-tests && docker compose up -d --build`


Классификация тестирования по уровню приложения
1. Модульное (unit). Проверка работоспособности отдельных методов класса.
2. Интеграционное. Проверяют, как компоненты приложения взаимодействуют между собой.
3. Системное. Проверяет, как приложение работает в целом также её масштабируемость и производительность.
4. Приёмочное. Проверяет работу системы с точки зрения бизнес требований заказчика.

## 1. unit testing

**Важно!** Не нужно в unit тестах использовать аннотацию **@SpringBootTest** она используется для интеграционного тестирования, и загружает весь контекст приложения загрузка может длиться от 4 секунд.

**Важно!** Не нужно в классах использовать внедрение зависимости через поля. Это явно скрывает зависимость, делая ее не очевидной. И её можно внедрить только через рефлексию.

### Простой пример unit теста
1. **@MockitoExtension** говорит что в этом классе присутствуют поля с аннотацией @Mock. Без этой аннотации моки работать не будут.
2. **@Mock**. Создает объект-пустышку. Но мы можем его создать через **UserRepository userRepository = Mockito.mock(UserRepository.class);**
3. **@InjectMocks**. Внедряет все объекты пустышки в указанный класс. Можем самостоятельно создать объекта класса через конструктор, добавив туда моки.

```java
@ExtendWith(MockitoExtension.class)
class RegisterUseCaseTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private RegisterUseCase registerUseCase;

  @Test
  void savedUserHasRegistrationDate() {
    User user = new User("zaphod", "zaphod@mail.com");
    when(userRepository.save(any(User.class))).then(returnsFirstArg());
    User savedUser = registerUseCase.registerUser(user);
    assertThat(savedUser.getRegistrationDate()).isNotNull();
  }
}
```

## 2. unit testing (controllers)

Spring Boot provides the @WebMvcTest annotation to fire up an application context that contains only the beans needed for testing a web controller:

**WebMvcTest** не делает тесты **интеграционными** т.к. мы тестируем только слой контроллеров, и мокаем слой бизнес логики, и работы с базой.

**@MockBean** помогает вытаскивать моки из контекста который создается при использовании аннотации **@WebMvcTest**

```java
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisterRestController.class)
class RegisterRestControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private RegisterUseCase registerUseCase;

  @Test
  void whenValidInput_thenReturns200() throws Exception {
    mockMvc.perform(...);
  }
}
```

### 1. Verifying HTTP Request Matching
Verifying that a controller listens to a certain HTTP request is pretty straightforward. We simply call the perform() method of MockMvc and provide the URL we want to test

```java
mockMvc.perform(post("/forums/42/register")
    .contentType("application/json"))
    .andExpect(status().isOk());
```

### 2. Verifying Input Deserialization
To verify that the input is successfully deserialized into Java objects, we have to provide it in the test request

```java
@Test
void whenValidInput_thenReturns200() throws Exception {
  UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");
  
   mockMvc.perform(post("/forums/{forumId}/register", 42L)
        .contentType("application/json")
        .param("sendWelcomeMail", "true")
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk());
}
```

### 3. Verifying Input Validation
```java
@Test
void whenNullValue_thenReturns400() throws Exception {
  UserResource user = new UserResource(null, "zaphod@galaxy.net");
  
  mockMvc.perform(post("/forums/{forumId}/register", 42L)
      ...
      .content(objectMapper.writeValueAsString(user)))
      .andExpect(status().isBadRequest());
}
```

### 4. Verifying Output Serialization
After the business logic has been called, we expect the controller to map the result into a JSON string and include it in the HTTP response. In our case, we expect the HTTP response body to contain a valid UserResource object in JSON form:
```java
@Test
void whenValidInput_thenReturnsUserResource() throws Exception {
  MvcResult mvcResult = mockMvc.perform(...)
      ...
      .andReturn();

  UserResource expectedResponseBody = ...;
  String actualResponseBody = mvcResult.getResponse().getContentAsString();
  
  assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
              objectMapper.writeValueAsString(expectedResponseBody));
}
```

### 5. Verifying Exception Handling
```java
@Test
void whenNullValue_thenReturns400AndErrorResult() throws Exception {
  UserResource user = new UserResource(null, "zaphod@galaxy.net");

  MvcResult mvcResult = mockMvc.perform(...)
          .contentType("application/json")
          .param("sendWelcomeMail", "true")
          .content(objectMapper.writeValueAsString(user)))
          .andExpect(status().isBadRequest())
          .andReturn();

  ErrorResult expectedErrorResponse = new ErrorResult("name", "must not be null");
  String actualResponseBody = 
      mvcResult.getResponse().getContentAsString();
  String expectedResponseBody = 
      objectMapper.writeValueAsString(expectedErrorResponse);
  assertThat(actualResponseBody)
      .isEqualToIgnoringWhitespace(expectedResponseBody);
}
```

## unit testing (repository)
Изначально создание таблиц и подключение к базе данных берётся из профиля по умолчанию.

Поэтому для избежания ошибок в тесте нужно уже иметь подготовленную инфраструктуру.

Если мы хотим использовать in memory базу данных h2 в таком случае, нам нужно чтобы hibernate создавал таблицы самостоятельно (spring.jpa=create-drop) и отключить накатывание миграций

## Test containers

https://jschmitz.dev/posts/testcontainers_how_to_use_them_in_your_spring_boot_integration_tests/
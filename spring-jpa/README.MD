# SPRING JPA

## Сколько уровней кеша?

Hibernate содержит 2 уровня кеша:

1.  *First level cache* - это session-level cache. Кеширует сущности в PersistanceContext и включен всегда по умолчанию. Кеш
    работает только для одной сессии и в пределах действия этой сессии.
    Кеш НЕ будет доступен из другой сессии. т.е. если в рамках одного
    вызова, мы совершим несколько одинаковых запросов, то в первый раз
    запрос выполнится, второй раз данные будут взяты из кеша.

2.  *Second level cache* - это multi-session-level cache. Хранит данные
    результатов запросов, которые доступны для всех сессий. Если одна
    сессия запросила данные, то они будут закешированы, и в дальнейшем
    доступны для других сессий. Данный кеш нужно включать вручную. Так
    же вручную настраивать результаты каких методов нужно кешировать и
    при каких условиях.

## Какая разница между hibernate, jpa и Data JPA?
**JPA (Java Persistence API)** is a specification for managing the mapping of Java objects to a relational database.

**Hibernate** is an implementation of the **JPA** specification. In other words, Hibernate is a library/framework that provides a set of tools for implementing the JPA specification. It provides additional features not defined in the JPA specification such as caching, advanced querying, and more. So, Hibernate is a JPA provider.

**Spring Data JPA** is a library/module in the Spring Framework that is built on top of Hibernate and provides a higher-level abstraction for data access. It provides a set of repository interfaces for commonly used CRUD operations, as well as support for pagination, sorting and more. Spring Data JPA also provides integration with Spring's other data access technologies, such as JDBC and MongoDB.

## FETCH MODES

### 1. @Fetch(FetchMode.SELECT)
```java
@OneToMany
@Fetch(FetchMode.SELECT)
private Set<Orders> orders;
```

Для каждого отдельного заказа будет создан отдельный запрос. Если 10 заказов, то будет 10 запросов. Чтобы был один запрос можно добавить аннотацию `@BatchSize(size=10)`

### 2. @Fetch(FetchMode.JOIN)

```java
@OneToMany
@Fetch(FetchMode.JOIN)
private Set<Orders> orders;
```
Является **жадным** типом. При запросe основной сущности (customer) при помощи jeft join цепляет сразу все связанные.

Если вызываем `customer.getOrders()` то выполняет следующийй sql
```sql
select *
from
    customer customer0_ 
left outer join
    order order1 
        on customer.id=order.customer_id 
where
    customer.id=?
```

### 3. @Fetch(FetchMode.SUBSELECT)

```java
@OneToMany
@Fetch(FetchMode.SUBSELECT)
private Set<Orders> orders
```
Создает подзапрос для получения списка заказов т.е. всe заказы вытаскивает одним запросов.

Если вызываем `customer.getOrders()` то выполняет следующийй sql
```sql
select *
from
    order order0_
where
    order0_.customer_id in (
        select
            customer0_.id
        from
            customer customer0_)
```

## CASCADE TYPES
Примеры будут рассмотренны на таблица topics и comments которые имеют связь один ко многим.

### Важно! у CascadeType нет дефолтного состояния. Поэтому если мы явно не указали CascadeType то он вообще использоваться не будет. т.е. никакого сайд эффекта на дочерние сущности не будет.

### 1. CascadeType.PERSIST
Cascade Type PERSIST propagates the persist operation from a parent to a child entity. When we save the person entity, the address entity will also get saved.
```java
@Test
@DisplayName("добавление топика с комментариями")
public void whenAddTopic_commentsShouldBeAdded() {
        Topic topic = new Topic("Topic1");
        Comment comment1 = new Comment("comment1");
        Comment comment2 = new Comment("comment2");
        topic.addComment(comment1);
        topic.addComment(comment2);
        topicRepository.save(topic);
        Assertions.assertEquals(2, topicRepository.count());
        Assertions.assertEquals(5, commentRepository.count());
}
```
```sql
insert into topic (title, id) values (?, ?)
insert into comment (text, topic_id, id) values (?, ?, ?)
insert into comment (text, topic_id, id) values (?, ?, ?)
```


### 2. CascadeType.MERGE
При изменении родительского и дочернего элемента по отдельности, произойдет их слияние при записи родительского

В примере ниже при записи топика, данные комментарий будут так же записаны.
```java
@Test
@DisplayName("редактирование топика с комментариями")
public void whenMergeTopic_commentsShouldBeMerged() {
    Topic topic = topicRepository.getOne(-1l);
    topic.setTitle("Updated Title");
    Comment comment = commentRepository.getOne(-4l);
    comment.setText("Updated Text");
    topicRepository.save(topic);
    Assertions.assertEquals(3, commentRepository.count());
}
```
```sql
update topic set title=? where id=?
update comment set text=?, topic_id=? where id=?
```


### 3. CascadeType.REMOVE
CascadeType.REMOVE is a cascading type in Hibernate that specifies that the delete operation should be cascaded from the parent entity to the child entities.

When CascadeType.REMOVE is used, any child entities associated with a parent entity will be automatically deleted when the parent entity is deleted. However, updates or modifications made to the parent entity will not be cascaded to the child entities.
```java
@Test
@DisplayName("удаление топика с комментариями")
public void whenDeleteTopic_commentsShouldBeDeleted() {
    Topic topic = topicRepository.getOne(-1l);
    topicRepository.delete(topic);
    Assertions.assertEquals(0, commentRepository.count());
}
```

```java
Hibernate: delete from comment where id=?
Hibernate: delete from comment where id=?
Hibernate: delete from comment where id=?
Hibernate: delete from topic where id=?
```
#### orphanRemoval vs CascadeType.REMOVE
Если кратко, `orphanRemoval` удаляет комментарий из базы при удалении комментария из топика.
```java
@Test
@DisplayName("если orphanRomoval=true, то при удалении комментария из топика он удаляется из базы")
public void givenOrphanRomovalTrue_whenRemoveCommentFromTopic_thenItRemovedFromDatabase() {
    Topic topic = topicRepository.getOne(-1l);
    topic.removeComment(topic.getComments().get(0));
    Assertions.assertEquals(2, commentRepository.count());
}
```

```sql
delete from comment where id=?
```

### 4. CascadeType.DETACH
Операция отсоединения удаляет объект из постоянного контекста. 
Когда мы используем CascadeType.DETACH , дочерняя сущность также будет удалена из постоянного контекста (**DETACH**).

### 5. CascadeType.REFRESH
Когда мы используем эту операцию с каскадным типом REFRESH, дочерняя сущность также перезагружается из базы данных всякий раз, 
когда обновляется родительская сущность.

### 6. CascadeType.ALL
Представляем собой одновременное включение всех каскадных типов.


## Способы реализации соединений сущностей
### 1. One to one.

Example: Each one user related with one address.
```java
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    //... 

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // ... getters and setters
}

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    //...

    @OneToOne(mappedBy = "address")
    private User user;

    //... getters and setters
}
```

### 2. One to many.
Example: One cart has many items
```java
@Entity
@Table(name="CART")
public class Cart {

    //...

    @OneToMany(mappedBy="cart")
    private Set<Item> items;
	
    // getters and setters
}

@Entity
@Table(name="ITEMS")
public class Item {
    
    //...
    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    public Item() {}
    
    // getters and setters
}
```

### 3. Many to many.
Example: Each student can have many courses and each course can have many students

```java
@Entity
class Student {

    @Id
    Long id;

    @ManyToMany
    @JoinTable(
    name = "course_like", 
    joinColumns = @JoinColumn(name = "student_id"), 
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> likedCourses;

    // additional properties
    // standard constructors, getters, and setters
}

@Entity
class Course {

    @Id
    Long id;

    @ManyToMany(mappedBy = "likedCourses")
    Set<Student> likes;

    // additional properties
    // standard constructors, getters, and setters
}
```

## @Transactional

Создаёт прокси класс на инстансом нашего класса. Где перед вызовом метода отмеченного **@Transactional** начинает транзакцию (используя вспомогательный класс **TransactionalManager**) и в случае исключения откатывает ее, или коммитит

Если поставим над классов то эта аннотация будет приминена ко всем **public** методам

Если поставим над **private, protected** методом, то она будет проигнорирована.

Если из метода с аннотацией **@Transactional** вызвать другой метод с этой аннотацией, то новая транзакция не начнется. Даже если метод не помечен аннотацией, а мы вызывает тот что помечен, то транзакция не будет открыта. Потому что фактически мы работаем в прокси, а вызываем метод который вне прокси.

### Какие propagation есть?
- **REQUIRED**. По умолчанию. Создает новую транзакцию, либо использует уже существующий если есть.
- **SUPPORTS**. Если уже есть транзакция, то использует существующую, если транзакции нет, то выполняется в без транзакции.
- **MANDATORY**. Если уже есть транзакция, то использует существующую, если транзакции нет, то выбрасывает исключение.
- **NEVER**. Метод должен быть без транзакции. Если уже есть активная транзакция, то выбрасывает исключение.
- **NOT_SUPPORTED**. Если есть активная транзакция, то ставит её в ожидание и выполняет метод без транзакции.
- **REQUIRES_NEW**. Если есть активная транзакция, то ставит её в ожидании и создаёт новую транзакцию.
- **NESTED**. Если уже существует транзакция то создаётся save point и затем создаёт новую транзакцию.

Как ведут себя транзакции, если выложенные транзакции, выбрасывают исключения?

### Что делает read-only в аннотации?
Оптимизирует запрос таким образом чтобы он выполнялся при условии только для чтения. Он может наложить оптимизацию на стороне JPA. Так же в зависимости от базы данных на стороне БД (например shared (S) блокировку)

## Persistence context

Each entity has one of several states:
![image info](./static/db/1.png)


In Hibernate, a persistence context is a set of managed entity instances. It is used to track the lifecycle of these entities and to manage their state. The persistence context is responsible for managing the relationship between the entities and the underlying database.

When an entity is first loaded from the database or created, it is added to the persistence context. Once an entity is in the persistence context, any changes made to its properties will be tracked by Hibernate and will be persisted to the database when the transaction is committed.

The persistence context also manages the caching of entities, so that when an entity is retrieved multiple times within the same context, Hibernate will return the same instance from the cache rather than hitting the database again. This can greatly improve performance by reducing the number of database queries required.

## Чем отличается save от flush?

Если действие происходит вне транзакции, то между ними разницы нет. В обоих случаях объект будет сразу записан в базу.

Если действие происходит в транзакции то в случае с **save** объект записывается в **persistence context**, а непосредственно сама запись объекта в базу происходит в конце транзакции. Но если мы используем метод **saveAndFlush** то запись объекта в базу происходит сразу, не дожидаясь окончания транзакции.

## Аудит сущности

Во множестве сущностей у нас могут быть служебные поля для аудита - дата создания, дата изменения, кем создана, кем изменена. Чтобы не дублировать эти поля для каждой сущности, обычно создается базовая сущность каркас.
```java
@MappedSuperclass 
@EntityListeners(AuditingEntityListener.class) 
public abstract class AbstractAuditableEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @CreatedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Instant updatedDate;

}
```

`@MappedSuperclass` позволяет вынести общие поля в родительский класс, но при этом не создавать для него отдельную таблицу. При такой стратегии классы-наследники преобразуются в независимые таблицы. @MappedSuperclass никак не влияет на структуру в базе — это просто способ вынести общие поля.

`@EntityListeners(AuditingEntityListener.class)` позволяет указать класс, который будет
выполнять инициализацию полей аудита перед сохранением сущности. Здесь мы используем стандартный спринговый класс
AuditingEntityListener, но при необходимости вы можете определить и свой. Без него аннотации
@CreatedBy, @CreatedDate, @LastModifiedBy, @LastModifiedDate работать не будут.

Чтобы подключить поля аудита к сущность, достаточно использовать extends `UserEntity extends AbstractAuditableEntity` 
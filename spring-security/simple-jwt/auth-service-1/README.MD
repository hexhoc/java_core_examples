# SECURITY

## Описание
Простая реализация защищеного сервиса при помощи Spring security и jwt аутентификации

## Терминология
**JWT (Json Web Token)** - Токен для хранения пользовательской информации - username, validity_date, email etc. Важно! никакой критической информации - номер карты, пароль и т.д.

Backend сервис создает JWT и отправляет клиенту, далее клиент в каждом запросе будет использовать JWT передавая его в headers

**Security web filter** - это фильтры которые обрабатывают входящие запросы на контроллеры. Фильтров может быть несколько и они могут оборачивать друг друга.

Примеры фильтров:
 - DisableEncodeUrlFilter
 - WebAsyncManagerIntegrationFilter
 - SecurityContextHolderFilter
 - HeaderWriterFilter
 - LogoutFilter
 - RequestCacheAwareFilter
 - SecurityContextHolderAwareRequestFilter
 - AnonymousAuthenticationFilter
 - SessionManagementFilter
 - ExceptionTranslationFilter
 - AuthorizationFilter

Мы добавим два фильтра
1. **JwtAuthFilter**. Проверяет наличие JWT и выполняет его валидацию для каждого запроса.
2. **UsernamePasswordAuthFilter**. Обычная аутентификация при помощи логина и пароля. Потому что пользователь должен пройти аутентификацию прежде чем мы ему выдадим JWT.

## Процесс работы

1. Пользователь посылает запрос на доступ к данным
2. Мы проверяем его логин и пароль
3. Генерируем токен если все ок. Сохраняем информацию о пользователе в **SecurityContextHolder**
4. Пользователь снова посылает запрос, но крепит JWT токен в headers
5. Проводим валидацию токена, пропускаем пользователя если все ОК.

## Тестирование

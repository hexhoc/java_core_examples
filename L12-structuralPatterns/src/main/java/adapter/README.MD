# Шаблон: Адаптер (Adapter)
=======================================================

### Цель:
Преобразование интерфейса одного класса в интерфейс того класса который необходим клиенту
=======================================================

### Для чего используется:
Для обеспечения совместной работы классов, интерфейсы которых не совместимы

=======================================================
### Пример использования: 
- Интерфейс класса, который мы хотим использовать не соответствует нашим потребностям, необходим класс который ему известен для взаимодействия

=======================================================

### Текущий пример

У нас есть сервис class Computer, у которого есть методы способенные взаимодействовать с классами реализующими интерфейс
USB. У нас есть класс MemoryCard который мы хотим использовать в этих методах, но вот незадача, он не реализует
интерфейс USB. Мы создаем новый класс MemoryCardUsbAdapter, который будет реализовывать интерфейс USB а так же
инкапсулировать внутри наш класс MemoryCard. Благодаря этому классу-обертке, мы можем использовать MemoryCard в классе
Computer

=======================================================

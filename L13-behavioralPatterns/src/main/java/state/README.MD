### Шаблон: Состояние (state)
==========================================================================================

### Цель:
Управление поведением объекта в зависимости от состояния

==========================================================================================

### Для чего используется:
Позволяет нам управлять поведением объекта в зависимости от состояния объекта

==========================================================================================

### Пример использования:
- Поведение объекта зависит от его состояния и изменяется во время выполнения
- Когда встречается большое кол-во условных операторов, и выбор ветви зависит от конкретного состояния

==========================================================================================

### Текущий пример:
У нас есть класс-заказ (Order) у него есть состояние (OrderStatus). Нам необходимо чтобы каждый раз при смене состояния, выполнялась определенная бизнес-логика, в том числе с заказом. Сама бизнес логика реализована внутри каждого класса-состояния


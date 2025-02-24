# Шаблон: Мост (Bridge)
=======================================================

### Цель:
Используется чтобы «разделять абстракцию и реализацию так, чтобы они могли изменяться независимо». Шаблон мост использует инкапсуляцию, агрегирование и может использовать наследование для того, чтобы разделить ответственность между классами.

=======================================================

### Для чего используется:
Отделить абстракцию от реализации так, чтобы и то и другое можно было изменять независимо. При использовании наследования реализация жестко привязывается к абстракции, что затрудняет независимую модификацию.

=======================================================

### Текущий пример

В системе онлайн-покупок платежная система должна иметь возможность обрабатывать платежи с помощью различных способов оплаты, таких как кредитные карты, PayPal и банковские переводы. В то же время интернет-магазин должен иметь возможность принимать платежи любым способом оплаты без необходимости изменять его реализацию.

Чтобы решить эту проблему, шаблон Bridge можно использовать для отделения платежной системы от интернет-магазина. Интерфейс платежной системы определяется как абстракция, в то время как реализация способа оплаты определяется как конкретная реализация. Интернет-магазин взаимодействует только с интерфейсом платежной системы, а реализация платежной системы обеспечивается отдельным модулем.

Например, предположим, что интернет-магазин использует шаблон Bridge для поддержки нескольких способов оплаты. Интерфейс платежной системы определяет методы, необходимые для обработки платежей, такие как подтверждение платежа, авторизация платежа и фиксация платежа. Реализация способа оплаты предоставляет реализацию для каждого способа оплаты, такого как реализация оплаты кредитной картой, реализация оплаты PayPal и реализация оплаты банковским переводом.

Когда клиент выбирает способ оплаты при оформлении заказа, интернет-магазин передает платежные реквизиты в интерфейс платежной системы, который, в свою очередь, вызывает соответствующую реализацию способа оплаты. Реализация способа оплаты обрабатывает платеж и возвращает результат в интерфейс платежной системы, который, в свою очередь, уведомляет интернет-магазин о статусе платежа.

Используя шаблон Bridge, интернет-магазин может легко поддерживать несколько способов оплаты без необходимости изменять его реализацию. Реализация платежной системы может быть обновлена или заменена без ущерба для реализации интернет-магазина, обеспечивая гибкость и ремонтопригодность системы.

=======================================================

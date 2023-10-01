# GARBAGE-COLLECTOR

## Установка и запуск
1. Из корня родительского проекта и компилим нужный проект `.\mvnw clean compile -pl garbage-collector`
2. Создаем docker image `cd garbage-collector && docker build -t hexhoc/garbage-collector .`
3. Запускаем docker images `cd docker-compose/garbage-collector && docker compose up -d --build`


## Жизненный цикл объектов в памяти
1. Все новые объекты попадают в **Eden**.

2. Когда eden переполняется, то запускается сборка мусора.

3. Объекты которые переживают сборку мусора, попадают в **survivor
   space**

4. Дальше просто ведется счетчик по каждому объекту, кто и сколько раз
   пережил чистку

Но новому поколению сборка запускается часто, по старому крайне редко.
Считается что если объект пережил пару чисток, то он будет жить вечно.

На время каждой сборки мусора происходит STOP THE WORLD, все работа
приложения останавливается.

## Garbage collectors
1. **SERIAL GC** - Сборка мусора выполняется в один поток.


2. **PARALLEL GC** - Сборка мусора выполняется в несколько потоков
   параллельно.

3. **CONCURRENT MARK SWEEP (CMS, depricated since java 9)** - сильно
   сокращает stop the world за счет того что разделяет операции на те что
   можно делать только во время stop the world и те что можно делать во
   время работы приложения. Он делает stop the world, и помечается объекты
   на удаление, запускает приложение и ищет на них ссылки, потом снова stop
   the world и делает чистку.

4. **G1 (used since java 11)** - Принцип работы такой же как и у CMS,
   только намного более оптимизированный.

5. **ZGC** (с java 11 в виде экспериментального, с java 14 на постоянке) -
   максимально потимизированный GC у котором stop the world занимает не
   более 10 ms.

## Управление memory model
1. `-Xmx`: This parameter sets the maximum heap size for the Java Virtual Machine (JVM). For example, -Xmx512m sets the maximum heap size to 512 megabytes. This parameter controls the amount of memory available for Java objects.

   **The default initial heap size is often set to a relatively small value**, such as 1/64th of the physical memory or 1 megabyte, whichever is larger. So, for a system with 8 GB of RAM, the default -Xms might be around 128 MB.

2. `-Xms`: This parameter sets the initial heap size for the JVM. For example, -Xms256m sets the initial heap size to 256 megabytes. It determines how much memory is allocated to the heap when the JVM starts.

   **The default maximum heap size is typically larger and may be set to 1/4th or 1/2 of the physical memory**, depending on the JVM implementation and the system's available memory. For example, on a system with 8 GB of RAM, the default -Xmx might be set to 2 GB or 4 GB.

### Включение конкретных GC
1. Serial Collector `-XX:+UseSerialGC`
2. Parallel Collector`-XX:+UseParallelGC`
3. CMS `-XX:+UseConcMarkSweepGC`
4. G1 `-XX:+UseG1GC`
5. ZGC `-XX:+UseZGC`

## Проблемы

**Memory leak** как такого не существует в java. Есть возможность его
сыметировать, это создать множество объектов, которые по факту не будут
использоваться, но ссылки на них будут хранится. В таком случае объекты
на которые есть ссылки будут недоступны для GC и они будут занимать
место в памяти.


## Профилирование
1. В **pom.xml** в плагине **maven-surefire-plugin** мы указываем параметры запуска java. Например `-Xmx1m` или `-XX:+UseSerialGC`.
2. Запускаем **visual VM**
3. Запускаем тесты и подключаемся к запущенному java приложению



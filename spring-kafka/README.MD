# LOGGING

## Установка и запуск
1. Из корня родительского проекта и компилим нужный проект `.\mvnw clean compile -pl spring-logging`
2. Создаем docker image `cd spring-logging && docker build -t hexhoc/spring-logging .`
3. Запускаем docker images `cd docker-compose/spring-logging && docker compose up -d --build`
4. Открываем Kibana http://localhost:5601/ 
5. Вкладка Analytics, create view data, смотрим логи

## Логирование в docker и kubernetes

Сборка логов построенна по схеме указаной ниже
![image info](/static/elastic-stack.png)

Так схема выглядит в docker
![image info](/static/services-and-elastic-stack.png)


1. FileBeat собирает логи из файлов
2. Logstash занимается транформацией логов
3. Elasticsearch хранением
4. Kibana UI для просмотра


### 1. Добавляем файл настройки логов logback-spring.xml
В проект спринга добавляем файл **logback-spring.xml** в котором указываем два отдельных **springProfile**, для запуска в докере и вне докера.

В **root level** указываем минимальный уровень с которого мы начинаем сборку логов.

Порядок уровней следующий:
 - TRACE
 - DEBUG
 - INFO
 - WARN
 - ERROR

И указываем какие appender будут использоваться.

Appender определяет как будут выглядеть наши логи и куда мы их будет выводить - STDOUT, FILE, DB, и т.п.

### 2. Конфигурируем FileBeat для сборки логов

Все логи контейнеров по умолчанию хранятся на host машине на которой запущен **docker** и **kubernetes** по пути `/var/lib/docker/containers`

Мы можем прокинуть их через том в FileBeat контейнер, и он уже будет заниматься их сборкой

Добавляем файл с настройкой docker контейнера filebeat - `filebeat.docker.yml`

Главной ключевой настройкой будет `container.labels.collect_logs_with_filebeat: "true"` по которой мы будем отслеживать контейнеры сервисов логи которых будем собирать

### 3. Конфигурируем Logstash для обработки логов

![image info](/static/diagram-logstash-pipeline.png)

Добавляем файл с настройкой для контейнера logstash - `logstash.conf`

В файле `logstash.conf`, конфигурируем следующее:

- Получение логов приходит на порт **5044**
- Обработайте события, добавив тег **logstash_filter_applied**
- Отправить обработанные логи в **Elasticsearch** запущенный на порту **9200**


## Лучшие практики логирование

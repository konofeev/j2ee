# Изучение Java Enterprise Edition

## Состав (изучение технологий)

- Сборка с помощью `Gradle`
- Использование сервера приложений `Glass Fish`

## Предварительные настройки

Необходимо настроить:
- `Jdk 8`
- `Gradle`
- `GlassFish`

Переменные окружения должны быть настроены следующим образом:
- `GlassFish` доступен из командной строки, а именно команда `asadmin` (переменная `PATH`)
- `Gradle` доступен из командой строки, а именно команда `gradle` (переменная `PATH`)
- переменная `GLASSFISH_HOME` указывает на каталог сервере приложений (пример `GLASSFISH_HOME=C:\Work\program\glassfish\`)

## Gradle

Для сборки необходимо выполнить команду: `gradle build`

Скрипт выполняет компиляцию исходных файлов, упаковку в `war-файл`, разворачивание приложения на сервер приложений, а также запуск сайта.

Прописываем в файле `~/.gradle/gradle.properties` переменные:
- `glassfishModules` до каталога с модулями сервера (путь примерно такой `.../glassfish/glassfish/modules`) (используется для разворачивания проекта)
- `webBrowser` до исполняемого файла веб-браузера (используется для запуска сайта)

## GlassFish

Запуск реализуется командой `asadmin start-domain`. Таким образом поднимается домен по умолчанию - `domain1`.

Для запуска в режиме отладки добавляем ключ `--debug`.

Запуск подключение БД выполняется командой `asadmin start-database`. Необходимо выполнять до разворачивания приложений, работающих с БД.

Разворачивание проекта (war-файла) реализуется командой `asadmin deploy <name-war-file>.war`.

Список развернутых приложений можно посмотреть командой `asadmin list-applications`.

Развернутое приложение можно деактивировать (undeploy) командой `asadmin undeploy <name-war-file>`. Суффикс `war` не указываем.

Добавление подключения:

`asadmin create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property user=<user-name>:password=<password>:DatabaseName=<database-name>:ServerName=localhost:port=3306 <name-connection-pool>`

`asadmin create-jdbc-resource --connectionpoolid <name-connection-pool> jdbc/<name-resource>`


## JDB

Отладка кода.

В файле build.gradle прописываем настройку компиляции с отладочной информацией: ` compileJava { options.debug = true } `

Не забываем стартовать glassfish с параметром: `--debug`

Подключаемся командой: `jdb -connect com.sun.jdi.SocketAttach:port=9009 -sourcepath <path-to-source-file>`. Где <path-to-source-file> прописываем для корректного отображения кода в отладке.

## Разработка

### Ресурсы сообщений

Для корректного отображения ресурсов необходимо использовать файл в кодировке `UNICODE`. Его можно сформировать с помощью утилиты native2ascii, которая входит в состав JDK. Для ОС Windows исходный файл ресурсов должен быть в кодировке `cp1251`.
Пример:
`native2ascii message-ru.properties message.properties`

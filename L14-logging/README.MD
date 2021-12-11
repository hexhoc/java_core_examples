
# Logging

## Description

Logging is a powerful aid for understanding and debugging program's run-time behavior. Logs capture and persist the
important data and make it available for analysis at any point in time.

This article discusses the most popular java logging framewloorks, Log4j 2 and Logback, along with their predecessor
Log4j, and briefly touches upon SLF4J, a logging facade that provides a common interface for different logging
frameworks.

We can save our logs in:
- files
- database
- network service (syslog)
- kafka
- ELK
- sentry

Java has one single logging facade slf4j. Exist two log engine that use slf4j - logback and log4j2. Spring use logback
by default!

Simple logging facade for java (slf4j) serves as simple facade or abstraction for various logging framework (
java.util.logging, logback, log4j2)

Log facade - it is like interface

Log engine - it is like implementation of interface

Logger has 5 debug level:
 - TRACE
 - DEBUG
 - INFO
 - WARN
 - ERROR

Logger engine has 3 main class:
 - Logger. The logger is the one who writes
 - Appenders. These are the ones who take logs from the logger and write them to media (console, file, database)
 - Layouts.  The recording format. Date, time, message tex

## EXAMPLES

### 1. Standard log with filter in file and console - config/logback_02.xml
### 2. Log with file rotation, if one file is full, logger create new log file and archive old file - config/logback_03.xml
###





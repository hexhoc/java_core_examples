
# JDBC

## Description

Each DBMS is developed by a specific company. To interact with the database, the
manufacturer releases a special driver - JDBC. It is used to establish a connection,
change data, send requests and process their results.
All the main entities in the JDBC API are interfaces: **Connection, Statement, PreparedStatement,
CallableStatement, ResultSet, Driver, DatabaseMetaData**. The JDBC-specific database driver
provides their implementations.
DriverManager is a singleton that contains information about all registered drivers.
The getConnection() method, based on the URL parameter, finds the java.sql.Driver
of the corresponding database and calls the connect() method from it.

## EXERCISE:
For do this exercises you need next prepare:
1. Create docker container with postgres
2. Install dependency - JDBC PostgreSQL
3. Create class DBConnector that create connection to db.
4. Create simple table "Students" with id, name and score

### Insert batch query
1. Create method "insertBatchQuery"
2. Disable autocommit
3. insert 100 new students with random score. Using batch insert and prepared statement
4. Commit transaction

### insert uncommitted query
1. Create method "insertUncommittedQuery"
2. Disable autocommit
3. insert 100 new students with random score. Using non-batch insert and prepared statement
4. Make if record count is greater than 50 then throw exception and rollback transaction

### Simple query
1. Create method "selectQuery"
2. Select students with score greater than 80


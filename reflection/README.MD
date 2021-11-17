# Reflection

## Description

Create own annotations and use reflection for access to them

### Get class info using reflection (easy)
1. Create class ReflectionGetInfo
2. Given class reflection.DemoClass
3. Print class simple name
4. Print all constructor
5. print all public method

### Create object(easy)
1. Create class ReflectionCreateObject
2. Get class for DemoClass.class
3. Create constructor
4. Create instance from constructor 

### Execute private method (easy)
1. Create class ReflectionPrivate
2. Create Class-object from DemoClass.Class
3. Create Method-object 
4. Execute method
5. Change private field value and execute method again

### Get method info (easy)
1. Create class ReflectionMethod
2. Print method modificators
3. Print method annotations

### Create test framework (hard)
1. Create annotations - @Test, @Before, @After
2. Create a test-class that describes the methods for the test
3. Create launch-class that will find own annotations and execute method
4. An exception in one test should not interrupt the others
5. When test is finished, print statistics about successes and failure tests
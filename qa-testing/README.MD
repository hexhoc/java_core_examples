# qa and testing

## Description

This project shows an example of the implementation of unit tests using a stack of technologies JUnit, mockito and
assert

### cover all class MessageBuilderImpl with tests.
1. Create class MessageBuilderImplTest
2. Create final private field - TEMPLATE_NAME, TEMPLATE_TEXT, MESSAGE_TEXT, MESSAGE_SIGN
3. Create method SetUp before each test, and create mock template provider and non-mock message builder
4. Create test for check that method buildMessage generate correct message
5. Create test for check that method getMessageTemplate from mock template provider will call at least once.
6. Create test for check that method buildMessage will throw templateNonFoundException.
7. Create test for check that MessageBuilder.longMethod() is executed less than 3 seconds
8. Create parametrized test for check that buildMessage return message length more than 0. Use static class "generatedValues" for generate values for test
   - Use for test next arguments:
     - "template 1", "message 1", "sign 1"
     - "template 2", "message 2", "sign 2"
     - "template 3", "message 3", "sign 3"
9. Add display name to each test

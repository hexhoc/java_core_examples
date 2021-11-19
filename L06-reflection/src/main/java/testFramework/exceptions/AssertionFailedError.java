package testFramework.exceptions;

public class AssertionFailedError extends Error{

    public AssertionFailedError(String message) {
        super(message != null && message.trim().length() != 0 ? message : "");
    }

}

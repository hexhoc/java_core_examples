package message;

public interface MessageBuilder {
    String buildMessage(String templateName, String text, String signature);
    void longMethod();
}
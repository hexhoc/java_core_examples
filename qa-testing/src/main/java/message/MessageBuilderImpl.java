package message;

public class MessageBuilderImpl implements MessageBuilder {

    private final MessageTemplateProvider templateProvider;

    public MessageBuilderImpl(MessageTemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    @Override
    public String buildMessage(String templateName, String text, String signature) {
        String messageTemplate = templateProvider.getMessageTemplate(templateName);
        if (messageTemplate == null || messageTemplate.isEmpty()) {
            throw new TemplateNotFoundException();
        }
        return String.format(messageTemplate, text, signature);
    }

    @Override
    public void longMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
import message.DefaultMessageTemplateProvider;
import message.MessageBuilder;
import message.MessageBuilderImpl;

public class Main {
    public static void main(String[] args) {
        MessageBuilder mb = new MessageBuilderImpl(new DefaultMessageTemplateProvider());
        String message = mb.buildMessage("DEFAULT","JOPA", "sign");
        System.out.println(message);
    }
}

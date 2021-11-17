import exercise_1.DefaultMessageTemplateProvider;
import exercise_1.MessageBuilder;
import exercise_1.MessageBuilderImpl;

public class Main {
    public static void main(String[] args) {
        MessageBuilder mb = new MessageBuilderImpl(new DefaultMessageTemplateProvider());
        String message = mb.buildMessage("DEFAULT","JOPA", "sign");
        System.out.println(message);
    }
}

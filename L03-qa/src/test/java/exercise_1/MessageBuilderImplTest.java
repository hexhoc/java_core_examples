package exercise_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class MessageBuilderImpl")
public class MessageBuilderImplTest {

    private final String TEMPLATE_NAME = "DEFAULT";
    private final String TEMPLATE_TEXT = "Hi!\n %s \n With best regards, %s";
    private final String MESSAGE_TEXT = "test_content";
    private final String MESSAGE_SIGN = "test_sign";

    private MessageBuilder messageBuilder;
    private MessageTemplateProvider provider;

    public static Stream<Arguments> generatedValues() {
        return Stream.of(
                Arguments.of("template 1", "message 1", "sign 1"),
                Arguments.of("template 2", "message 2", "sign 2"),
                Arguments.of("template 3", "message 3", "sign 3")
        );
    }

    @BeforeEach
    public void setUp() {
        provider = Mockito.mock(DefaultMessageTemplateProvider.class);
        messageBuilder = new MessageBuilderImpl(provider);
    }

    @DisplayName("Should create correct message for the specified template, text and sign")
    @Test
    public void testBuildMessage1() {
        //configure mock
        Mockito.when(provider.getMessageTemplate(TEMPLATE_NAME)).thenReturn(TEMPLATE_TEXT);

        String message = messageBuilder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, MESSAGE_SIGN);
        assertEquals(String.format(TEMPLATE_TEXT, MESSAGE_TEXT, MESSAGE_SIGN), message);
    }

    @DisplayName("Should call once expected dependency when build message")
    @Test
    public void testBuildMessage2() {
        //configure mock
        Mockito.when(provider.getMessageTemplate(TEMPLATE_NAME)).thenReturn(TEMPLATE_TEXT);

        messageBuilder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, MESSAGE_SIGN);

        //We check that the mock class method was called only once
        Mockito.verify(provider, Mockito.times(1)).getMessageTemplate(TEMPLATE_NAME);
    }

    @DisplayName("Should throw expected exception when dependency return null instead of template")
    @Test
    public void testBuildMessage3() {

        Assertions.assertThrows(TemplateNotFoundException.class,
                () -> messageBuilder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, MESSAGE_SIGN));
    }

    @DisplayName("Should execute less or equal than 3 seconds")
    @Test
    public void testBuildMessage4() {
        Assertions.assertTimeout(Duration.ofSeconds(3), () -> messageBuilder.longMethod());
    }

    @DisplayName("Should return message with length more than 0")
    @ParameterizedTest
    @MethodSource("generatedValues")
    public void testBuildMessage5(String template, String text, String sign) {
        Mockito.when(provider.getMessageTemplate(template)).thenReturn(template);

        String message = messageBuilder.buildMessage(template, text, sign);
        assertTrue(message.length() > 0);
        //We check that the mock class method was called only once
        Mockito.verify(provider, Mockito.times(1)).getMessageTemplate(template);
    }

}
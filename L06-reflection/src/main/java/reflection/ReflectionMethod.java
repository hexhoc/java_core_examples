package reflection;

import reflection.annotations.SimpleAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionMethod {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<DemoClass> demoClass = DemoClass.class;
        Method method = demoClass.getDeclaredMethod("setValue", String.class);

        System.out.println("--- ANNOTATIONS");
        Annotation annotation = method.getDeclaredAnnotation(SimpleAnnotation.class);
        System.out.println(annotation);
        System.out.println(annotation);
        System.out.println("======================================");

        System.out.println("--- MODIFICATORS");
        int modifiers = method.getModifiers();

        System.out.println("is public: "+ Modifier.isPublic(modifiers));
        System.out.println("is static: "+ Modifier.isStatic(modifiers));
        System.out.println("is final: "+ Modifier.isFinal(modifiers));
        System.out.println("======================================");

    }
}

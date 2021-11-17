package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionPrivate {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class<DemoClass> demoClass = DemoClass.class;

        //CREATE INSTANCE OF THE DEMOCLASS
        DemoClass demoInstance = new DemoClass("HELLO");

        //PRINT DECLARED METHODS
        Arrays.stream(demoClass.getDeclaredMethods()).forEach(i -> System.out.println(i.getName()));

        System.out.println("--- INVOKE PRIVATE METHOD");
        Method method = demoClass.getDeclaredMethod("privateMethod");
        method.setAccessible(true);
        method.invoke(demoInstance);
        System.out.println("======================================");

        System.out.println("--- CHANGE PRIVATE FIELD");
        Field field = demoClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(demoInstance, "GOOD BYE");

        method.invoke(demoInstance);

        System.out.println("======================================");

    }
}

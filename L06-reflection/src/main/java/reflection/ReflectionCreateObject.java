package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectionCreateObject {
    public static void main(String[] args) throws Exception {
        Class<DemoClass> demoClass = DemoClass.class;

        //print all current constructor
        Arrays.stream(demoClass.getConstructors()).forEach(i -> {
            System.out.println(i.getName());
        });

        //CREATE CONSTRUCTOR
        Constructor<DemoClass> demoConstructor = demoClass.getConstructor(String.class);
        //CREATE INSTANCE
        DemoClass demo = demoConstructor.newInstance("HELLO FROM DEMO OBJECT");
//        System.out.println(demo.getValue());

    }
}

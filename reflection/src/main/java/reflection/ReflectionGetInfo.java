package reflection;

import java.util.Arrays;

public class ReflectionGetInfo {
    public static void main(String[] args) {

        Class<DemoClass> demoClass = DemoClass.class;
        System.out.println(demoClass.getSimpleName());

        System.out.println("--- CONSTRUCTORS");
        Arrays.stream(demoClass.getConstructors()).forEach(System.out::println);
        System.out.println("======================================");

        System.out.println("--- PUBLIC METHODS");
        Arrays.stream(demoClass.getMethods()).forEach((i) -> {
            System.out.println(i.getName());
        });
        System.out.println("======================================");

        System.out.println("--- ALL METHOD");
        Arrays.stream(demoClass.getDeclaredMethods()).forEach((i)->{
            System.out.println(i.getName());
        });
        System.out.println("======================================");

        System.out.println("--- PUBLIC FIELD");
        Arrays.stream(demoClass.getFields()).forEach((i)->{
            System.out.println(i.getName());
        });
        System.out.println("======================================");

        System.out.println("--- ALL FIELD");
        Arrays.stream(demoClass.getDeclaredFields()).forEach((i)->{
            System.out.println(i.getName());
        });
        System.out.println("======================================");
    }
}

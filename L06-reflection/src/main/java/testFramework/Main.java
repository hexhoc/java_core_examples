package testFramework;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import reflection.annotations.After;
import reflection.annotations.Before;
import reflection.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Reflections reflections = new Reflections("testFramework.test", new SubTypesScanner(false));

        var allClasses = reflections.getSubTypesOf(Object.class);

        for (var testClass : allClasses) {
            var constructor = testClass.getConstructor();
            var instance = constructor.newInstance();

            HashSet<Method> beforeMethods = new HashSet<>();
            HashSet<Method> tests = new HashSet<>();
            HashSet<Method> afterMethods = new HashSet<>();

            getTestMethods(testClass, beforeMethods, tests, afterMethods);

            //execute before
            for(var method : beforeMethods) {
                method.invoke(instance);
            }

            //execute tests
            for(var method : tests) {
                try {
                    method.invoke(instance);
                    System.out.println("\u001B[42m" + "test: " + method.getName() + " success" + "\u001B[0m");
                } catch (Exception e) {
                    System.out.println("\u001B[41m" + "test: " + method.getName() + " failed with " + e.getMessage() + "\u001B[0m");
                }
            }

            //execute after
            for(var method : afterMethods) {
                method.invoke(instance);
            }
        }
    }

    public static void getTestMethods(Class<? extends Object> testClass,
                                      HashSet<Method> beforeMethods,
                                      HashSet<Method> tests,
                                      HashSet<Method> afterMethods) {
        //get method with annotation before and launch it
        Arrays.stream(testClass.getDeclaredMethods()).forEach(new Consumer<Method>() {
            @Override
            public void accept(Method method) {
                if (method.getDeclaredAnnotation(Before.class) != null) {
                    beforeMethods.add(method);
                } else if (method.getDeclaredAnnotation(Test.class) != null) {
                    tests.add(method);
                } else if (method.getDeclaredAnnotation(After.class) != null) {
                    afterMethods.add(method);
                }
            }
        });
    }
}


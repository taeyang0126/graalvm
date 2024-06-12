package com.lei.graalvm;

import java.lang.reflect.Method;

/**
 * <p>
 * HelloWorld
 * </p>
 *
 * @author 伍磊
 */
public class HelloWorld {

    public static void main(String[] args) throws ReflectiveOperationException {
        System.out.println("hello world");

        String className = args[0];
        String methodName = args[1];
        String input = args[2];

        Class<?> clazz = Class.forName("com.lei.graalvm." + className);
        Method method = clazz.getDeclaredMethod(methodName, String.class);
        Object result = method.invoke(null, input);
        System.out.println(result);
    }

}

class StringReverser {
    static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

class StringCapitalizer {
    static String capitalize(String input) {
        return input.toUpperCase();
    }
}

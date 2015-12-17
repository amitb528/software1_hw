package org.ar.tester.util;

import com.sun.istack.internal.Nullable;
import com.sun.javafx.beans.annotations.NonNull;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amit on 12/16/2015.
 */
public class Utils {

    private static final String ERROR_TEXT = "<! ERROR: %s>";
    private static final String I_WANT_INPUT_SIGN = ">>";
    private static final String BULLSHIT_DELIMITER =
            "##!!&@@#!$#@$@#%#@$BULLSHIT_DELIMITER@#&^!!%@#$@!@!%$$!!##";

    public static final String CLASS_PATTERN = "(?<packageName>(?:\\w+\\.)*)(?<className>\\w+)";
    public static final String METHOD_PATTERN = "(?<methodName>\\w+)(?:\\(\\))?";
    public static final String FULL_METHOD_PATTERN =
            String.format("%s\\.%s", CLASS_PATTERN, METHOD_PATTERN);

    public static final Pattern classPattern = Pattern.compile(CLASS_PATTERN);
    public static final Pattern methodPattern = Pattern.compile(METHOD_PATTERN);
    public static final Pattern fullMethodPattern = Pattern.compile(FULL_METHOD_PATTERN);

    public static Object getValue(Scanner scanner) throws ValueParsingException {
        Class<?> valueClass = getClass(scanner, "Enter value class name:");
        if (valueClass == null) {
            throw new ValueParsingException();
        }

        return getValue(scanner, valueClass);
    }

    public static Object getValue(Scanner scanner, Class<?> valueClass) throws ValueParsingException {
        while (!Thread.interrupted()) {
            try {
                if(valueClass.equals(Void.class)){
                    return null;
                }
                if (valueClass.equals(String.class)) {
                    System.out.println("Enter a string value:");
                    return scanner.nextLine();
                }
                if (valueClass.equals(Integer.class)) {
                    System.out.println("Enter an integer:");
                    return Integer.parseInt(scanner.nextLine());
                }
                if (valueClass.equals(Boolean.class)) {
                    System.out.println("Enter a boolean:");
                    return Boolean.parseBoolean(scanner.nextLine());
                }
                if (valueClass.equals(Character.class)) {
                    System.out.println("Enter a character:");
                    try {
                        return scanner.nextLine().trim().charAt(0);
                    } catch (IndexOutOfBoundsException e) {
                        Utils.error("There is no character.");
                    }
                }
                if (valueClass.equals(Float.class)) {
                    System.out.println("Enter a float:");
                    return Float.parseFloat(scanner.nextLine());
                }
                if (valueClass.equals(Double.class)) {
                    System.out.println("Enter a double:");
                    return Double.parseDouble(scanner.nextLine());
                } else {
                    System.out.println("The given class is not supported"); //TODO: give an option to get a parser
                    throw new ValueParsingException();
                }
            } catch (NumberFormatException e) {
                error("Number format incorrect.");
            }
        }
        throw new ValueParsingException();
    }

    public static
    @Nullable
    Class<?> getClass(String fullClass) {
        Matcher matcher = classPattern.matcher(fullClass);
        if (!matcher.matches()) {
            error("Malformed class name.");
            return null;
        }

        String packageName = matcher.group("packageName");
        String className = matcher.group("className");
        String fullClassName = packageName + className;
        try {
            if (fullClassName.equals("String")) {
                return String.class;
            } else if (fullClassName.equals("Integer")) {
                return Integer.class;
            } else if (fullClassName.equals("Boolean")) {
                return Boolean.class;
            } else if (fullClassName.equals("Character")) {
                return Character.class;
            } else if (fullClassName.equals("Float")) {
                return Float.class;
            } else if (fullClassName.equals("Double")) {
                return Double.class;
            }
            return Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            error(String.format("Class not found: %s", fullClassName));
            return null;
        }
    }

    public static
    @Nullable
    Class<?> getClass(Scanner scanner, String prompt) {
        while (!Thread.interrupted()) {
            System.out.println(prompt);

            Class<?> c = getClass(scanner.nextLine());
            if(c != null){
                return c;
            }
        }
        return null;
    }

    public static
    @Nullable
    Class<?> getClass(Scanner scanner) {
        return getClass(scanner, "Enter a class name:");
    }

    public static
    @Nullable
    Method getMethod(@NonNull Class<?> cls, String method) {
        Matcher matcher = methodPattern.matcher(method);
        if (!matcher.matches()) {
            error("Malformed method name.");
            return null;
        }

        String methodName = matcher.group("methodName");
        try {
            return cls.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            error(String.format("There is no such no-args method: %s", methodName));
            return null;
        }
    }

    public static
    @Nullable
    Method getMethod(@NonNull Class<?> cls, Scanner scanner, String prompt) {
        while (!Thread.interrupted()) {
            System.out.println(prompt);

            Method m = getMethod(cls, scanner.nextLine());
            if(m != null){
                return m;
            }
        }
        return null;
    }

    public static
    @Nullable
    Method getMethod(@NonNull Class<?> cls, Scanner scanner) {
        return getMethod(cls, scanner, "Enter a method name:");
    }

    public static @Nullable Method getMethod(String fullMethod){
        Matcher matcher = fullMethodPattern.matcher(fullMethod);
        if(!matcher.matches()){
            error("Malformed full method name.");
            return null;
        }

        String packageName = matcher.group("packageName");
        String className = matcher.group("className");
        String methodName = matcher.group("methodName");
        Class<?> cls = getClass(packageName + className);

        if(cls == null){
            return null;
        }

        try {
            return cls.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            error(String.format("There is no such no-args method: %s", methodName));
            return null;
        }
    }

    public static BufferedReader bufferIfNeeded(Reader reader) {
        if (reader instanceof BufferedReader) {
            return (BufferedReader) reader;
        } else {
            return new BufferedReader(reader);
        }
    }

    public static Class<?> classify(Class<?> cls) {
        if (cls.isPrimitive()) {
            if(cls.equals(void.class)){
                return Void.class;
            }
            if (cls.equals(int.class)) {
                return Integer.class;
            }
            if (cls.equals(boolean.class)) {
                return Boolean.class;
            }
            if (cls.equals(char.class)) {
                return Character.class;
            }
            if (cls.equals(float.class)) {
                return Float.class;
            }
            if (cls.equals(double.class)) {
                return Double.class;
            }
        }

        return cls;
    }

    //TODO: long short byte
    public static void wantInput() {
        System.out.print(I_WANT_INPUT_SIGN);
    }

    public static void error(String message) {
        System.out.println(String.format(ERROR_TEXT, message));
    }

    public static void bullshit() {
        System.out.println(BULLSHIT_DELIMITER);
    }
}

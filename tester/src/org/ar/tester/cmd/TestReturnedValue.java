package org.ar.tester.cmd;

import org.ar.tester.Tester;
import org.ar.tester.util.Utils;
import org.ar.tester.util.ValueParsingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by Amit on 12/16/2015.
 */
public class TestReturnedValue extends Command {
    private static final String NAME = "testReturnedValue";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int run(String args, Scanner scanner) {
        String[] arguments = args.split("\\s+");

        Method method;
        if(arguments.length >= 1 && !arguments[0].isEmpty()){
            method = Utils.getMethod(arguments[0]);
        }else {
            Class<?> cls = Utils.getClass(scanner);
            if (cls == null) {
                return 1;
            }
            method = Utils.getMethod(cls, scanner);
        }

        if (method == null) {
            Utils.error("Could not find method.");
            return 1;
        }

        Object value;
        try{
            value = Utils.getValue(scanner, Utils.classify(method.getReturnType()));
        }catch (ValueParsingException e){
            Utils.error("Could not parse value");
            return 1;
        }

        System.out.println(String.format("Starting %s():", method.getName()));
        Utils.bullshit();
        Object returnedValue;
        try {
            returnedValue = method.invoke(null);
        } catch (Exception e) {
            Utils.error("Could not call the method");
            return 1;
        }
        Utils.bullshit();

        System.out.println(String.format("Returned value is:\n%s", returnedValue));
        boolean equal;
        if (value != null) {
            equal = value.equals(returnedValue);
        } else {
            equal = returnedValue == null;
        }
        System.out.println(String.format("Returned value equal value? %s",
                equal));

        return 0;
    }
}

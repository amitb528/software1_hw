package org.ar.tester.cmd;

import java.util.Scanner;

/**
 * Created by Amit on 12/16/2015.
 */
public class Echo extends Command{
    private static final String NAME = "echo";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int run(String args, Scanner scanner) {
        System.out.println(args);
        return 0;
    }
}

package org.ar.tester.cmd;

import java.util.Scanner;

/**
 * Created by Amit on 12/16/2015.
 */
public class Exit extends Command{
    private static final String NAME = "exit";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int run(String args, Scanner scanner) {
        System.exit(0);
        return 0;
    }
}

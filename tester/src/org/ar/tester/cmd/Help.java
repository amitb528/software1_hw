package org.ar.tester.cmd;

import java.util.Scanner;

/**
 * Created by Amit on 12/16/2015.
 */
public class Help extends Command {
    private static final String NAME = "help";
    private static final String HELP_TEXT = "Use the 'listCommands' command to list all commands";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int run(String args, Scanner scanner) {
        System.out.println(HELP_TEXT);

        return 0;
    }
}

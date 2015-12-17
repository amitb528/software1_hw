package org.ar.tester.cmd;

import org.ar.tester.Tester;

import java.util.Scanner;

/**
 * Created by Amit on 12/16/2015.
 */
public class ListCommands extends Command{
    private static final String NAME = "listCommands";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int run(String args, Scanner scanner) {
        for(Command command : Tester.getCommands()){
            System.out.println(command.getName());
        }
        return 0;
    }
}

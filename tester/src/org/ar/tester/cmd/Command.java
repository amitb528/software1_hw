package org.ar.tester.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Amit on 12/16/2015.
 */
public abstract class Command {
    public abstract String getName();

    public String getHelp(){
        return "I don't want to help you.";
    }

    /**
     * Run the command
     * @param args
     * @return 0 if no problems occurred
     */
    public abstract int run(String args, Scanner scanner);
}

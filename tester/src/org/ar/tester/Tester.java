package org.ar.tester;

import org.ar.tester.cmd.*;
import org.ar.tester.util.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amit on 12/15/2015.
 */
public class Tester {

    private static final String HOLA_TEXT =
            "Hola!!\n" +
                    "My name is org.ar.tester.Tester...\n" +
                    "I test things!\n" +
                    "What do you want to test??\n" +
                    "If you need any help type 'help'. To exit type 'exit'.";
    private static final String UNKNOWN_COMMAND_TEXT =
            "There is no such command, maybe you are stupid.. The 'help' command may help you.";

    private static List<Command> commands;
    private static Pattern commandLinePattern =
            Pattern.compile("(?<commandName>\\w+)(?:[ \t]+(?<args>.*))?");

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run(){
        System.out.println(HOLA_TEXT);

        setUpCommands();

        Scanner scanner = new Scanner(System.in);
        while(true){
            Utils.wantInput();

            String line = scanner.nextLine();

            Matcher matcher = commandLinePattern.matcher(line);
            if(matcher.matches()) {
                String commandName = matcher.group("commandName");
                String args = matcher.group("args");

                runCommand(commandName, args != null ? args : "", scanner);
            }else{
                Utils.error("You are stupid.");
            }
        }
    }

    public static void setUpCommands(){
        commands = new ArrayList<Command>();

        commands.add(new Help());
        commands.add(new Exit());
        commands.add(new ListCommands());
        commands.add(new TestReturnedValue());
        commands.add(new Echo());
    }

    public static void runCommand(String commandName, String args, Scanner scanner){
        Iterator<Command> iter = commands.iterator();
        while(iter.hasNext()){
            Command command = iter.next();
            if(command.getName().equals(commandName)){
                command.run(args, scanner);
                return;
            }
        }
        System.out.println(UNKNOWN_COMMAND_TEXT);
    }

    //TODO: delete check
    public static void testConsole(Scanner scanner, File expected) {
        BufferedReader expectedReader;
        try {
            expectedReader = new BufferedReader(new FileReader(expected));
        } catch (FileNotFoundException e) {
            Utils.error("File not found");
            return;
        }

        Reader testedReader = System.console().reader();
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                }
            });
            System.out.println(
                    String.format(
                            "Is the console's content equal to the file's content? %s",
                            isEqual(testedReader, expectedReader)));
        } catch (IOException e) {
            Utils.error("There was a problem comparing the console's content and the file");
        }
    }

    public static boolean isEqual(Reader tested, Reader wanted) throws IOException {
        BufferedReader testedReader = Utils.bufferIfNeeded(tested);
        BufferedReader wantedReader = Utils.bufferIfNeeded(wanted);

        String line;
        boolean foundDifference = false;
        while ((line = testedReader.readLine()) != null) {
            String otherLine = wantedReader.readLine();
            if (!line.equals(otherLine)) {
                foundDifference = true;
                break;
            }
        }

        tested.close();
        wanted.close();
        return !foundDifference;
    }

    public static List<Command> getCommands(){
        return commands;
    }
}

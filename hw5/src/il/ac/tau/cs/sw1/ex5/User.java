package il.ac.tau.cs.sw1.ex5;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class User {
    int userID;
    String location;
    int age;

    private static final int NO_AGE = -1;
    private static final int MAX_USERS_IN_FILE = 20000;


    public User(int userID, String location, int age){
        this.userID = userID;
        this.location = location;
        this.age = age;
    }

    public User(int userID, String location){
        this.userID = userID;
        this.location = location;
        this.age = NO_AGE;
    }

    public int getUserID(){
        return userID;
    }

    public String getLocation(){
        return location;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString(){
        return userID + "," + location + "," + age;
    }

    /**
     *
     * @return
     * @post ($ret == true) <=> (this.age != NO_AGE)
     */
    public boolean hasAge(){
        return age != NO_AGE;
    }

    /**
     *
     * @param fileName
     * @return
     * @throws Exception
     * @pre fileName is a legal fileName, the format of the file is as expected
     * @post $ret is an Arrays of User objects read from the file fileName
     */

    public static User[] loadUsersData(String fileName) throws Exception{
        User[] users = new User[MAX_USERS_IN_FILE];

        Scanner scanner = new Scanner(new File(fileName));
        scanner.nextLine();

        int count = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(";");

            int userID = Integer.parseInt(values[0].replace("\"", ""));
            String location = values[1].replace("\"", "");
            if(values[2].equals("NULL")){
                users[count] = new User(userID, location);
            }else{
                int age = Integer.parseInt(values[2].replace("\"", ""));
                users[count] = new User(userID, location, age);
            }

            count++;
        }

        scanner.close();

        return Arrays.copyOf(users, count);
    }
}

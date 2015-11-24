/**
 * Created by Amit on 10/20/2015.
 */
public class AverageCalculator{
    public static void main(String[] args){
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        System.out.println("The sum is: " + (a+b+c) + ".");
        System.out.println();
        System.out.println("The average is: " + ((double)(a+b+c)/3) + ".");
    }
}
package il.ac.tau.cs.sw1.shapes;

import java.util.Scanner;

/**
 * Created by Amit on 12/15/2015.
 */
public class Circle implements Shape{
    private int x;
    private int y;
    private int radius;

    public static Circle newInstance(Scanner user){
        System.out.print("Please enter X coordinate: ");
        int x = Integer.parseInt(user.next());
        System.out.print("Please enter Y coordinate: ");
        int y = Integer.parseInt(user.next());
        System.out.print("Please enter radius: ");
        int radius = Integer.parseInt(user.next());
        return new Circle(x, y, radius);
    }

    public Circle(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getRadius(){
        return radius;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    @Override
    public float getArea() {
        return (float) (Math.PI * radius * radius);
    }

    @Override
    public float getPerimeter() {
        return (float) (2 * Math.PI * radius);
    }

    @Override
    public String getDetails() {
        return String.format("Circle: X=%s, Y=%s, radius=%s", x, y, radius);
    }
}

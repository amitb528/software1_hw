package il.ac.tau.cs.sw1.shapes;

import java.util.Scanner;

/**
 * Created by Amit on 12/15/2015.
 */
public class Rectangle implements Shape{
    private int x;
    private int y;
    private int width;
    private int height;

    public static Rectangle newInstance(Scanner user){
        System.out.print("Please enter X coordinate: ");
        int x = Integer.parseInt(user.next());
        System.out.print("Please enter Y coordinate: ");
        int y = Integer.parseInt(user.next());
        System.out.print("Please enter width: ");
        int width = Integer.parseInt(user.next());
        System.out.print("Please enter height: ");
        int height = Integer.parseInt(user.next());
        return new Rectangle(x, y, width, height);
    }

    public Rectangle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth(){
        return width;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    @Override
    public float getArea() {
        return width*height;
    }

    @Override
    public float getPerimeter() {
        return 2*(width + height);
    }

    @Override
    public String getDetails() {
        return String.format("Rectangle: X=%s, Y=%s, Width=%s, Height=%s",
                x, y, width, height);
    }
}

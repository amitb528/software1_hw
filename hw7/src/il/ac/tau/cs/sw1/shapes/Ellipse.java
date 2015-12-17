package il.ac.tau.cs.sw1.shapes;

import java.util.Scanner;

/**
 * Created by Amit on 12/15/2015.
 */
public class Ellipse implements Shape {
    private int x;
    private int y;
    private int semiMajorAxis;
    private int semiMinorAxis;

    public static Ellipse newInstance(Scanner user){
        System.out.print("Please enter X coordinate: ");
        int x = Integer.parseInt(user.next());
        System.out.print("Please enter Y coordinate: ");
        int y = Integer.parseInt(user.next());
        System.out.print("Please enter semi-major axis length: ");
        int semiMajorAxis = Integer.parseInt(user.next());
        System.out.print("Please enter semi-minor axis length: ");
        int semiMinorAxis = Integer.parseInt(user.next());
        return new Ellipse(x, y, semiMajorAxis, semiMinorAxis);
    }

    public Ellipse(int x, int y, int semiMajorAxis, int semiMinorAxis){
        this.x = x;
        this.y = y;
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
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

    public int getSemiMajorAxis(){
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(int semiMajorAxis){
        this.semiMajorAxis = semiMajorAxis;
    }

    public int getSemiMinorAxis(){
        return semiMinorAxis;
    }

    public void setSemiMinorAxis(int semiMinorAxis){
        this.semiMinorAxis = semiMinorAxis;
    }

    @Override
    public float getArea() {
        return (float)(Math.PI * semiMajorAxis * semiMinorAxis);
    }

    @Override
    public float getPerimeter() {
        return (float)(Math.PI * (semiMajorAxis + semiMinorAxis));
    }

    @Override
    public String getDetails() {
        return String.format("Ellipse: X=%s, Y=%s, SemiMajorAxis=%s, SemiMinorAxis=%s",
                x, y, semiMajorAxis, semiMinorAxis);
    }
}

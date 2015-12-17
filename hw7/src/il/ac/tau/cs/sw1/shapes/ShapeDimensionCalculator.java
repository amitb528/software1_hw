package il.ac.tau.cs.sw1.shapes;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;


public class ShapeDimensionCalculator {

    private static final String MENU_TEXT =
            "Please choose shape type:\n" +
                    "E – Ellipse\n" +
                    "R – Rectangle\n" +
                    "C – Circle\n" +
                    "X - Exit";

    public static void main(String[] args) throws IOException {
        Shape[] shapes = getShapesFromUser();
        writeShapesToFile("shapes_output.txt", shapes);

    }

    public static Shape[] getShapesFromUser() {
        Scanner user = new Scanner(System.in);
        Shape[] shapes = new Shape[20];

        System.out.println("ShapeDimensionCalculator\n");

        System.out.println(MENU_TEXT);
        String chosen;
        int index = 0;
        while (!(chosen = user.next().trim()).equals("X")) {
            if (chosen.equals("E")) {
                shapes[index] = Ellipse.newInstance(user);
            } else if (chosen.equals("R")) {
                shapes[index] = Rectangle.newInstance(user);
            } else if (chosen.equals("C")) {
                shapes[index] = Circle.newInstance(user);
            } else {
                System.out.println("Unknown command. Please try again.\n");
                System.out.println(MENU_TEXT);
                continue;
            }
            System.out.println(String.format("Shape added: [%s]", shapes[index].getDetails()));

            index++;
            System.out.println();
            System.out.println(MENU_TEXT);
        }
        return shapes;
    }

    public static void writeShapesToFile(String outputFilename, Shape[] shapes) throws IOException {
        //TODO: float.2
        Writer writer = new FileWriter(outputFilename);

        writer.write("Shape Dimension Calculator\n\n");

        int circlesCount = 0;
        int ellipsesCount = 0;
        int rectanglesCount = 0;
        float areaSum = 0;
        float perimeterSum = 0;

        int i;
        for (i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];
            if (shape == null) {
                break;
            }

            if(shape instanceof Circle){
                circlesCount++;
            }else if(shape instanceof Ellipse){
                ellipsesCount++;
            }else if(shape instanceof Rectangle){
                rectanglesCount++;
            }

            float area = shape.getArea();
            float perimeter = shape.getPerimeter();

            areaSum += area;
            perimeterSum += perimeter;

            writer.write(String.format("%s\nArea: %s, Perimeter: %s\n", shape.getDetails(),
                    area, perimeter));
        }

        writer.write(String.format("Total number of shapes: %s", i));
        writer.write(String.format("Number of Circles: %s", circlesCount));
        writer.write(String.format("Number of Ellipses: %s", ellipsesCount));
        writer.write(String.format("Number of rectangles: %s", rectanglesCount));
        writer.write(String.format("Total Area sum: %s", areaSum));
        writer.write(String.format("Total Perimeter sum: %s", perimeterSum));
    }
}

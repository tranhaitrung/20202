package Bai4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Bai4 {
    public static void main(String[] args) {
        Set<Point> setA = new HashSet<>();
        Set<Point> setB = new HashSet<>();
        Set<Point> setC = new HashSet<>();
        Point A = new Point(800, 800);
        Point B = new Point(4000, 8000);
        Point C = new Point(2400,2400);

        setA = Point.randomPoint(A, 400, 8000);
        setB = Point.randomPoint(B, 500, 10000);
        setC = Point.randomPoint(C, 600, 12000);

//        System.out.println("size A: " + setA.size() +"\n");

        Set<Point> all = new HashSet<>();
        all.addAll(setA);
        all.addAll(setB);
        all.addAll(setC);

        List<Point> list = new ArrayList<Point>(all);
        Collections.shuffle(list);

        try {
            File file = new File("BT123/recoures/Output4.txt");
            FileWriter fw = new FileWriter(file);
            fw.write("SIZE = " + list.size() + "\n");
            for(Point p:list){
                fw.write("("+p.getX()+";"+p.getY()+")\n");
            }
            fw.close();
            System.out.println("DONE ^.^");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

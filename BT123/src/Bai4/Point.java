package Bai4;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Set<Point> randomPoint(Point point, int distance, int size){
        Random random = new Random();
        Set<Point> setPoint = new HashSet<>();
        int cnt = 0;
        while(cnt < size){
            int tmpX = random.nextInt(distance*2) + point.getX() - distance;
            int tmpY = random.nextInt(distance*2) + point.getY() - distance;

            int kc = (int) Math.sqrt((tmpX - point.getX())*(tmpX-point.getX()) + (tmpY - point.getY())*(tmpY-point.getY()));
//            System.out.println("KC " + kc + " - DIS " + distance + "\n");
            if(kc <= distance){
                Point tmpPoint = new Point(tmpX, tmpY);
                if(!setPoint.contains(tmpPoint)){
                    setPoint.add(tmpPoint);
                    cnt++;
                }
            }
//            System.out.println("cnt : " + cnt +"\n");

        }

    return setPoint;
    }
}

package model;

import java.util.ArrayList;
import java.util.Random;

public class Point {
    /**
     *  tọa độ x.
     */
    private int x;
    /**
     * tọa độ y.
     */
    private int y;
    /**
     * khởi tạo điểm 2 chiều.
     * @param yPoint
     * @param xPoint
     */
    public Point(final int xPoint,  final int yPoint) {
        this.x = xPoint;
        this.y = yPoint;
    }
    /**
     * Lấy tọa độ x.
     * @return int
     */
    public int getX() {
        return x;
    }
    /**
     * lấy tọa độ y.
     * @return int
     */
    public int getY() {
        return y;
    }
    /**
     * ghi đè phương thức toString().
     * @return string
     */
    public String toString() {
        return ("(" + this.x + ", " + this.y + ")");
    }
    /**
     * ghi de phuong thuc equals.
     * @param a
     * @return boolean
     */
    public boolean equals(final Point a) {
        if (this.getX() == a.getX() && this.getY() == a.getY()) {
           return true;
        }
        return false;
    }
    /**
     * hàm random các điểm không trùng nhau.
     * @param a
     * @param distance
     * @param amount
     * @return arraylist<point>
     */
    public static ArrayList<Point> randomListPoints(final Point a,
            final int distance, final int amount) {
        ArrayList<Point> arrPoint = new ArrayList<Point>(amount);
        Random rand = new Random(); // sinh so random
        int newX;
        int newY;
        for (int i = 0; i < amount; i++) {
            newX = rand.nextInt(2 * distance) + a.getX() - distance + 1;
            newY = rand.nextInt(2 * distance) + a.getY() - distance + 1;
            Point newPoint = new Point(newX, newY);
            while (newPoint.distance(a) > distance
                    || arrPoint.contains(newPoint)) {
                newX = rand.nextInt(2 * distance) + a.getX() - distance + 1;
                newY = rand.nextInt(2 * distance) + a.getY() - distance + 1;
                newPoint = new Point(newX, newY);
            }
            arrPoint.add(newPoint);
        }
        return arrPoint;
    }
    /**
     * ham tinh khoang cach.
     * @param a
     * @return double
     */
    public double distance(final Point a) {
        return Math.sqrt((this.x - a.getX()) * (this.x - a.getX())
                + (this.y - a.getY()) * (this.y - a.getY()));
    }
}

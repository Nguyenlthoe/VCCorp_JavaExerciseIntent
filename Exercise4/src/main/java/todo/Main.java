package todo;

import java.util.ArrayList;
import java.util.Collections;

import io.WriteFile;
import model.Point;

/**
 * contain main app.
 * @author nguyenlt
 *
 */
public final class Main {
    /**
     * constructor not use.
     */
    private Main() {
        // TODO Auto-generated constructor stub
    }

    /**
     * main app.
     * @param args
     */
    public static void main(final String[] args) {
        ArrayList<Point> listPoints = new ArrayList<Point>();
        final int xA = 800;
        final int yA = 800;
        final int distanceA = 400;
        final int amountA = 8000;
        Point pointA = new Point(xA, yA);
        // sinh ngau nhien 8000 diem cach A(800, 800) khong qua 400
        listPoints.addAll(Point.randomListPoints(pointA, distanceA, amountA));
        final int xB = 4000;
        final int yB = 800;
        final int distanceB = 500;
        final int amountB = 10000;
        Point pointB = new Point(xB, yB);
     // sinh ngau nhien 10000 diem cach B(4000,  800) khong qua 500
        listPoints.addAll(Point.randomListPoints(pointB, distanceB, amountB));
        final int xC = 2400;
        final int yC = 2400;
        final int distanceC = 600;
        final int amountC = 12000;
        Point pointC = new Point(xC, yC);
     // sinh ngau nhien 12000 diem cach C(2400, 2400) khong qua 600
        listPoints.addAll(Point.randomListPoints(pointC, distanceC, amountC));
        // xoa tron cac diem trong mang
        Collections.shuffle(listPoints);
        // ghi cac diem ra file output4.txt
        WriteFile writeFile = new WriteFile("output4.txt");
        writeFile.writeFile(listPoints);
    }

}

package todo;

import java.util.Scanner;
import java.util.Set;

import myset.MySet;

public final class Main {
    /**
     * main app.
     * @param args
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        // NHập kích thước và khoảng giá trị của tập hợp
        System.out.println("Nhập kích thước tập hợp và khoảng "
                + "giá trị của các phần từ: \n");
        int size = sc.nextInt();
        int range = sc.nextInt();
        sc.close();
        // khởi tạo tập hợp A
        Set<Integer> setA = MySet.generateSet(size, range);
        System.out.println("Tập hợp A là: \n");
        setA.forEach(e -> System.out.println(e));
        // khởi tạo tập hợp B
        Set<Integer> setB = MySet.generateSet(size, range);
        System.out.println("Tập hợp B là: \n");
        setB.forEach(e -> System.out.println(e));
        // Tính hợp và giao hai tập hợp
        System.out.println("Hợp của hai tập A và B là: \n");
        MySet.unionTwoSet(setA, setB).forEach(e -> System.out.println(e));
        System.out.println("Giao của hai tập A và B là: \n");
        MySet.intersectionTwoSets(setA, setB).forEach(e ->
                                            System.out.println(e));
    }
    private Main() {
    }
}

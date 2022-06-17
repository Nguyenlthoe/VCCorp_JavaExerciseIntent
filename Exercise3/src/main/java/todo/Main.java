package todo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public final class Main {
    /**
     * main app.
     * @param args
     */
    public static void main(final String[] args) {
        // TODO Auto-generated method stub
        solution2();
    }
    /**
     * giải pháp 2 sử dụng future và callable interface.
     */
    public static void solution2() {
        // khởi tạo các biến cần thiết
        String maxNumberOfThreads = "6";
        String top = "10";
        HashMap<String, Integer> numberOfWords = new HashMap<String, Integer>();
        ExecutorService executor = Executors.newFixedThreadPool(
                Integer.parseInt(maxNumberOfThreads));
        ExecutorCompletionService<HashMap<String, Integer>> service =
           new ExecutorCompletionService<HashMap<String, Integer>>(executor);
        File inputFolder = new File("input"); // file chứa các file text để đọc
        File[] inputFiles = inputFolder.listFiles();
        if (inputFiles != null) {
            for (File input: inputFiles) {
                MyThread myThread = new MyThread(
                        input.getPath());
                service.submit(myThread);
            }
        }
        int count = inputFiles.length;
        while (count != 0) {
            Future<HashMap<String, Integer>> future = service.poll();
            if (future != null) {
                try {
                    future.get().forEach((key, value) -> {
                        numberOfWords.putIfAbsent(key, 0);
                        numberOfWords.put(key, numberOfWords.get(key) + value);
                    });
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Exception when get future!!");
                }
                count--;
            }
        }
        ArrayList<Entry<String, Integer>> sortList = sortByValue(
                numberOfWords, true);
        System.out.println("Các từ có số lượng lớn nhất là: \n");
        for (int i = sortList.size() - 1; i >= sortList.size()
                - Integer.parseInt(top); i--) {
            System.out.println(sortList.get(i).getKey()
                    + ": " + sortList.get(i).getValue());
        }
        System.out.println("Các từ có số lượng ít nhất là: \n");
        for (int i = 0; i < Integer.parseInt(top); i++) {
            System.out.println(sortList.get(i).getKey()
                    + ": " + sortList.get(i).getValue());
        }
        return;
    }
    /**
     * giải pháp 1 của bài toán sử dụng concurrentHashMap.
     */
    public static void solution1() {
        // lưu số lượng các từ vào hashmap.
        String maxNumberOfThreads = "6";
        String top = "10";
        ConcurrentHashMap<String, Integer> numberOfWords =
                new ConcurrentHashMap<String, Integer>();
        ExecutorService executor = Executors.newFixedThreadPool(
                Integer.parseInt(maxNumberOfThreads));
        File inputFolder = new File("input"); // file chứa các file text để đọc
        File[] inputFiles = inputFolder.listFiles();
        if (inputFiles != null) {
            for (File input: inputFiles) {
                MyThread myThread = new MyThread(numberOfWords,
                        input.getPath());
                executor.execute(myThread);
            }
        }
        try {
            executor.awaitTermination(Integer.parseInt(maxNumberOfThreads),
                    TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Exception occur");
        }
        // In có từ có số lượng lớn nhất và ít nhất
        ArrayList<Entry<String, Integer>> sortList = sortByValue(
                new HashMap<String, Integer>(numberOfWords), true);
        System.out.println("Các từ có số lượng lớn nhất là: \n");
        for (int i = sortList.size() - 1; i >= sortList.size()
                - Integer.parseInt(top); i--) {
            System.out.println(sortList.get(i).getKey()
                    + ": " + sortList.get(i).getValue());
        }
        System.out.println("Các từ có số lượng ít nhất là: \n");
        for (int i = 0; i < Integer.parseInt(top); i++) {
            System.out.println(sortList.get(i).getKey()
                    + ": " + sortList.get(i).getValue());
        }
    }
    /**
     * contructor main, not use.
     */
    private Main() {
        // TODO Auto-generated constructor stub
    }
    /**
     * Hàm sắp xếp HashMap theo giá trị tăng dần hoặc giảm dần.
     * @param a
     * @param ascending
     * @return ArrayList
     */
    public static ArrayList<Entry<String, Integer>> sortByValue(
            final HashMap<String, Integer> a, final boolean ascending) {
        ArrayList<Entry<String, Integer>> sortList =
                new ArrayList<>(a.entrySet());
        Comparator<Entry<String, Integer>> comparator;
        if (ascending)  {
            comparator = new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(final Entry<String, Integer> e1,
                        final Entry<String, Integer> e2) {
                    return e1.getValue().compareTo(e2.getValue());
                }
            };
        } else {
            comparator = new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(final Entry<String, Integer> e1,
                        final Entry<String, Integer> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            };
        }
        Collections.sort(sortList, comparator);
        return sortList;
    }

}

package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadFile {
    /**
     * Save link to file for reading.
     */
    private String fileName;
    /**
     * @param file
     * hàm khởi tạo lớp ReadFile.
     */
    public ReadFile(final String file) {
        this.fileName = file;
    }
    /**
     * @return hashmap (contains number of each words)
     */
    public HashMap<String, Integer> countWords() {
        HashMap<String, Integer> countWords = new HashMap<String, Integer>();
        BufferedReader bufferedReader = null;
        // count words
        try {
            System.out.println("Start reading file: " + this.fileName);
            bufferedReader = new BufferedReader(new FileReader(this.fileName));
            String line = bufferedReader.readLine();
            // Bắt đầu đếm số lượng cách từ
            while (line != null) {
             // chuyển sang chữ thường và loại bỏ khoảng trắng thừa
                line = line.toLowerCase();
                line = (line.replaceAll("\\s+", " ")).trim();
                if (line.equals("")) { // loại bỏ các dòng trắng
                    line = bufferedReader.readLine();
                    continue;
                }
                String[] wordsArray = line.split(" ");
                for (String word : wordsArray) {
                 // Thêm từ vào hashmap tránh việc get(key) = null
                    countWords.putIfAbsent(word, 0);
                    countWords.put(word, countWords.get(word) + 1);
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            System.out.println("End reading file: " + this.fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Find not found: " + this.fileName);
        } catch (IOException e) {
            System.out.println("IOException went read file");
        }
        return countWords;
    }
}

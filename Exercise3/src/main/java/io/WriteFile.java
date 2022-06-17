package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteFile {
    /**
     * Save link file for writing.
     */
    private String fileName;
    /**
     * @param file
     * hàm khởi tạo của lớp WriteFile
     */
    public WriteFile(final String file) {
        this.fileName = file;
    }
    /**
     * write file output with hashmap.
     * @param countWords
     */
    public void writeFile(final HashMap<String, Integer> countWords) {
        BufferedWriter bufferedWriter = null;
        try {
            System.out.println("Start writing file: " + this.fileName);
            bufferedWriter = new BufferedWriter(new FileWriter(this.fileName));
            // save String for writing
            StringBuffer writeStringBuffer = new StringBuffer();
            countWords.forEach((key, value) -> {
                writeStringBuffer.append(key + ": " + value + "\n");
            });
            //write file
            bufferedWriter.write(writeStringBuffer + "");
            // close file
            bufferedWriter.close();
            System.out.println("End writing file: " + this.fileName);
        } catch (IOException e) {
            System.out.println("Can't write file: " + this.fileName);
        }
    }
}


package todo;

import java.util.HashMap;

import io.ReadFile;
import io.WriteFile;

final class Main {
    /**
     * @code contructor
     */
    private Main() {
    }

    /**
     * @code main
     * @param args
     */
    public static void main(final String[] args) {
        ReadFile readFile = new ReadFile("input.txt");
        HashMap<String, Integer> numberOfWords = readFile.countWords();
        WriteFile writeFile = new WriteFile("output.txt");
        writeFile.writeFile(numberOfWords);
    }
}

package todo;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import io.ReadFile;
/**
 * lớp MyThread là một luồng để đếm số lượng từ.
 * @author nguyenlt
 */
public final class MyThread implements Runnable,
                                    Callable<HashMap<String, Integer>> {
    /**
     * Lưu một concurenthashmap phục vụ việc đồng bộ hóa đếm từ.
     */
    private ConcurrentHashMap<String, Integer> countWords;
    /**
     * Lưu đường dẫn của file cần đếm.
     */
    private String fileName;
    /**
     * lưu hashmap để trả về cho future.get.
     */
    private HashMap<String, Integer> wordsHM;
    /**
     * khởi tạo luông thực thi đếm từ.
     * @param numberOfWordsCHM
     * @param file
     */
    public MyThread(final ConcurrentHashMap<String, Integer> numberOfWordsCHM,
            final String file) {
        this.countWords = numberOfWordsCHM;
        this.fileName = file;
    }
    /**
     * khởi tạo luồng khi sử dụng callable, future.
     * @param file
     */
    public MyThread(final String file) {
        this.fileName = file;
    }

    @Override
    public void run() {
        System.out.println("Start reading: " + fileName);
        ReadFile readFile = new ReadFile(fileName);
        wordsHM = readFile.countWords();
        wordsHM.forEach((key, value) -> {
            countWords.putIfAbsent(key, 0);
            boolean check = false;
            int tmp;
            while (!check) {
                tmp = countWords.get(key); // lấy số lượng của key đang lưu
                // kiểm tra xem khi get key thì có bị luồng khác thay đổi không
                check = countWords.replace(key, tmp, tmp + value);
            }
        });
        System.out.println("End reading: " + fileName);
    }

    @Override
    public HashMap<String, Integer> call() throws Exception {
        System.out.println("Start reading: " + fileName);
        ReadFile readFile = new ReadFile(fileName);
        wordsHM = readFile.countWords();
        System.out.println("End reading: " + fileName);
        return this.wordsHM;
    }

}

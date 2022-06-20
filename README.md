## 3. Lập trình đếm từ đa luồng cho bài 2 với dữ liệu vào là một folder chứa nhiều file text. Hãy xử lý song song các file và tìm top 10 từ xuất hiện nhiều nhất, và top 10 từ xuất hiện ít nhất của toàn bộ dữ liệu có trong folder. Lưu ý, chỉ được chạy tối đa 6 luồng cùng lúc.
Mở terminal trong folder <br>
Chạy lệnh <b><i>mvn clean package</i></b> hoặc <b><i>mvn exec:java -Dexec.mainClass="todo.Main"</i></b> để chạy chương trình <br> 
Các file text được đặt trong folder <b>input<b>, sửa đổi các f
Chương trình sử dụng hai phương pháp để đồng bộ hóa các luồng: <br>
+ sử dụng CocurrentHashMap để số lượng các từ
+ sử dụng Future, Callable, ExecutorCompletionService để xử lý mỗi khi một file được đọc xong
Để xem sự khác nhau giữa các phương pháp, sửa đổi hàm main trong file src/main/java/todo/Main.java với solution1: sử dụng ConcurrentHashMap và solution 2 sử dụng ExecutorCompletionService  
### nhận xét: cách sử dụng ExecutorCompletionService cho kết quả nhanh hơn

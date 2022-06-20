package maze;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MazePanel extends JPanel {
    private static final long serialVersionUID = -566807999447681130L;
    /**
     *  // khởi tạo ma trận mảng 2 chiều.
     */
    private int[][] maze;
    /**
     * chiều dài ma trận mê cung.
     */
    private int sizeh;
    /**
     * chiều rộng ma trận mê cung.
     */
    private int sizew;
    /**
     * tọa độ khởi hành theo trục x.
     */
    private int start;
    /**
     * tọa độ khởi hành theo trục y.
     */
    private int end;
    /**
     * hàm khởi tạo đối tượng MazePanel.và thực hiện giải, in ra màn hình.
     * @param h
     * @param w
     * @param s
     * @param e
     * @param matrix
     */
    public MazePanel(final int h, final int w,
            final int s, final int e, final int[][] matrix) {
     // Kích thước ma trận
        sizeh = h;
        sizew = w;
        start = s;
        maze = matrix;
        end = e;
    }
    /**
     * Implement một phương pháp tìm đường nào đó.
     * <p>
     * Yêu cầu : Vẽ đường đi từ điểm bắt đầu đến điểm kết thúc. Không hiện
     * các phần thừa - là các phần đường cụt hoặc đường đi bị sai. Chỉ vẽ
     * tuyến đường chính đi từ điểm đầu (màu vàng) đến màu đỏ..
     * <p>
     * Đường đi từ điểm đầu đến điểm cuối được tô màu xanh dương, để tô màu
     * xanh dương hãy set giá trị của điểm trên ma trận sang một số > 2
     */
    public void solve() {
        // Hàm này chứa phương pháp tìm đường từ điểm start đến vị
        // trí màu đỏ trên ma trận
        try1(start, end);
    }
    /**
     * thuật toán quay lui.
     * @param x
     * @param y
     * @return boolean
     */
    public boolean try1(final int x, final int y) {
//        System.out.println(x + " + " + y);
        final int fill = 3;
        maze[x][y] = fill;
        if (x + 1 < sizeh) {
            if (maze[x + 1][y] == 2) {
                return true;
            }
        }
        if (y + 1 < sizew) {
            if (maze[x][y + 1] == 2) {
                return true;
            }
        }
        if (x > 0) {
            if (maze[x - 1][y] == 2) {
                return true;
            }
        }
        if (y > 0) {
            if (maze[x][y - 1] == 2) {
                return true;
            }
        }
        if (x + 1 < sizeh) {
            if (maze[x + 1][y] == 0) {
                if (try1(x + 1, y)) {
                    return true;
                }
                maze[x + 1][y] = 0;
            }
        }
        if (y + 1 < sizew) {
            if (maze[x][y + 1] == 0) {
                if (try1(x, y + 1)) {
                    return true;
                }
                maze[x][y + 1] = 0;
            }
        }
        if (x > 0) {
            if (maze[x - 1][y] == 0) {
                if (try1(x - 1, y)) {
                    return true;
                }
                maze[x - 1][y] = 0;
            }
        }
        if (y > 0) {
            if (maze[x][y - 1] == 0) {
                if (try1(x, y - 1)) {
                    return true;
                }
                maze[x][y - 1] = 0;
            }
        }
        return false;
    }
    /**
     * ve ma trân + loi giai.
     * @param g
     */
    public void paintComponent(final Graphics g) {
        final int size = 20;
        super.paintComponent(g);
        for (int j = 0; j < sizew; j++) {
            for (int i = 0; i < sizeh; i++) {
                if (i == start && j == end) {
                    g.setColor(Color.yellow);
                } else if (maze[i][j] == 0) {
                    g.setColor(Color.white);
                } else if (maze[i][j] == 1) {
                    g.setColor(Color.black);
                } else if (maze[i][j] == 2) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.blue);
                }
                // blue là màu của lời giải.
                g.fillRect(j * size, i * size, size, size);
            }
        }
    }
}

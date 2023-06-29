import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class task1 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        int s = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);

        Stack<Integer>[] towers = new Stack[t];
        for (int i = 0; i < t; i++) {
            towers[i] = new Stack<>();
        }

        for (int i = n; i >= 1; i--) {
            towers[s - 1].push(i);
        }

        try (FileWriter fw = new FileWriter("output_task1.txt")) {
            hanoi(n, t, s, d, towers, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hanoi(int n, int t, int s, int d, Stack<Integer>[] towers, FileWriter fw) throws IOException {
        if (n == 1) {
            moveDisk(s, d, towers, fw);
        } else {
            int aux = findUnusedTower(t, s, d);
            hanoi(n - 1, t, s, aux, towers, fw);
            moveDisk(s, d, towers, fw);
            hanoi(n - 1, t, aux, d, towers, fw);
        }
    }

    public static int findUnusedTower(int t, int s, int d) {
        for (int i = 1; i <= t; i++) {
            if (i != s && i != d) {
                return i;
            }
        }
        return -1;
    }

    public static void moveDisk(int s, int d, Stack<Integer>[] towers, FileWriter fw) throws IOException {
        int disk = towers[s - 1].pop();
        towers[d - 1].push(disk);
        String move = "Move disk " + disk + " from T" + s + " to T" + d;
        System.out.println(move);
        fw.write(move + "\n");
    }
}

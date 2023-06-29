import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class task2 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the input file as a command line argument.");
            return;
        }

        String inputFileName = args[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String[] firstLine = reader.readLine().split("\t");
            int n = Integer.parseInt(firstLine[0]);
            int t = Integer.parseInt(firstLine[1]);
            int s = Integer.parseInt(firstLine[2]);
            int d = Integer.parseInt(firstLine[3]);

            List<Stack<Integer>> towers = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                towers.add(new Stack<>());
            }

            for (int i = n; i >= 1; i--) {
                towers.get(s - 1).push(i);
            }

            String line;
            boolean validMoves = true;
            while ((line = reader.readLine()) != null) {
                String[] move = line.split("\t");
                int disc = Integer.parseInt(move[0]);
                int src = Integer.parseInt(move[1]);
                int dest = Integer.parseInt(move[2]);

                if (!towers.get(src - 1).isEmpty() && towers.get(src - 1).peek() == disc
                        && (towers.get(dest - 1).isEmpty() || towers.get(dest - 1).peek() > disc)) {
                    towers.get(src - 1).pop();
                    towers.get(dest - 1).push(disc);
                } else {
                    validMoves = false;
                    break;
                }
            }
            reader.close();

            boolean allEmptyExceptDest = true;
            for (int i = 0; i < t; i++) {
                if (i != (d - 1) && !towers.get(i).isEmpty()) {
                    allEmptyExceptDest = false;
                    break;
                }
            }

            if (validMoves && allEmptyExceptDest) {
                System.out.println("The sequence of moves is correct.");
            } else {
                System.out.println("The sequence of moves is incorrect.");
            }

        } catch (IOException e) {
            System.out.println("Error reading the input file: " + inputFileName);
        }
    }
}

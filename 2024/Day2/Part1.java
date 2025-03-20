import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Solved but somewhat a lazy and a tad hard to read soultion.
 * The solution is off by one from the real value for some reason
 */
public class Part1 {
    final String FILE_PATH = "input.txt";

    public Part1() throws FileNotFoundException {
        solveProblem(FILE_PATH);
    }

    public void solveProblem(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);
        int count = 0;
        while (reader.hasNextLine()) {
            int line[] = Arrays.stream(reader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (isReportSafe(line)) {
                count++;
            }
        }
        System.out.println(count);

    }

    /**
     * @param report an integer array
     * @return true if the list has both properties:
     *         <ol>
     *         <li>All values are either in an asceding or decending order</li>
     *         <li>Two difference between adjacent values are between 3 and 1
     *         (both included)
     *         </li>
     *         </ol>
     * 
     */
    public boolean isReportSafe(int[] report) {
        boolean isSafe = true;
        int prevDiff = 0;

        for (int i = 0; i < report.length - 1; i++) {
            int currDiff = report[i] - report[i + 1];
            if (Math.abs(currDiff) > 3 || Math.abs(currDiff) < 1) {
                return false;
            }

            if (prevDiff != 0 && !doesHaveSameSigns(prevDiff, currDiff)) {
                return false;
            }
            prevDiff = currDiff;
        }
        return isSafe;
    }

    public boolean doesHaveSameSigns(int a, int b) {
        return (a > 0 && b > 0) || (a < 0 && b < 0);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Part1 p = new Part1();

    }
}

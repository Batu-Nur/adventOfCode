import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Solved but somewhat a lazy and a tad hard to read soultion
 */
public class Part2 {
    final String FILE_PATH = "input.txt";

    public Part2() throws FileNotFoundException {
        solveProblem(FILE_PATH);
    }

    public void solveProblem(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);
        int count = 0;
        while (reader.hasNextLine()) {
            int line[] = Arrays.stream(reader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            HashSet<Integer> set = badNumIndexes(line);
            if (set.isEmpty()) { // no badnums exist
                count++;
            } else {
                for (Integer badInt : set) { // check all potential badnums
                    int j = 0;
                    int upd[] = new int[line.length - 1];
                    for (int i = 0; i < line.length; i++) {
                        if (i == badInt) {
                            continue;
                        }
                        upd[j++] = line[i];
                    }
                    set = badNumIndexes(upd);

                    if (set.isEmpty()) {
                        System.out.println("Old: " + Arrays.toString(line));
                        System.out.println("New: " + Arrays.toString(upd) + " bad: " + line[badInt]);
                        count++;
                        break;
                    }

                }

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
    public static HashSet<Integer> badNumIndexes(int[] report) {

        HashSet<Integer> set = new HashSet<Integer>();
        int[] differences = new int[report.length - 1];
        for (int i = 0; i < report.length - 1; i++) {
            differences[i] = report[i] - report[i + 1];
        }

        for (int i = 0; i < differences.length - 1; i++) {
            boolean safe = doesHaveSameSigns(differences[i], differences[i + 1]) &&
                    isInSafeRange(differences[i]);

            if (!safe) {
                set.add(i);
                set.add(i + 1);
            }

        }
        if (!isInSafeRange(differences[differences.length - 1])) {
            set.add(differences.length - 1);
            set.add(differences.length);
        }

        return set;
    }

    public static boolean doesHaveSameSigns(int a, int b) {
        return (a > 0 && b > 0) || (a < 0 && b < 0);
    }

    public static boolean isInSafeRange(int a) {
        return (Math.abs(a) >= 1 && Math.abs(a) <= 3);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Part2 p = new Part2();

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part1 {
    final String FILE_PATH = "input.txt";
    ArrayList<Integer> List1 = new ArrayList<Integer>();
    ArrayList<Integer> List2 = new ArrayList<Integer>();

    public part1() throws FileNotFoundException {
        readListsFromFile(FILE_PATH);
    }

    /**
     * Reads from the file and puts the two given sample lists into its own sorted
     * array
     * 
     * @param Path input file from https://adventofcode.com/2024/day/1/input
     * @throws FileNotFoundException
     */
    public void readListsFromFile(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);

        int turn = 0;

        while (reader.hasNextInt()) {
            int i = reader.nextInt();
            if (turn++ % 2 == 0) {
                List1.add(i);
            } else {
                List2.add(i);
            }
        }
        List1.sort((a, b) -> {
            return -1 * a.compareTo(b);
        });
        List2.sort((a, b) -> {
            return -1 * a.compareTo(b);
        });

    }

    public static void main(String[] args) throws FileNotFoundException {
        part1 obj = new part1();
        int sum = 0;
        for (int i = 0; i < obj.List1.size(); i++) {
            sum += Math.abs(obj.List1.get(i) - obj.List2.get(i));
        }
        System.out.println(sum);
    }

}

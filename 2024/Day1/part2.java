import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class part2 {
    final String FILE_PATH = "input.txt";
    ArrayList<Integer> List = new ArrayList<Integer>();
    HashMap<Integer, Integer> map = new HashMap();

    public part2() throws FileNotFoundException {
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
                List.add(i);
            } else {
                Integer count = map.putIfAbsent(i, 1);
                if (count != null) {
                    map.put(i, ++count);
                }
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        part2 obj = new part2();
        int sum = 0;

        for (Integer i : obj.List) {
            Integer occurences = obj.map.get(i);
            if (occurences != null) {
                sum += occurences * i;
            }
        }
        System.out.println(sum);
    }

}

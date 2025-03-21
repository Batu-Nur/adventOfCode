import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solved
 * 
 */
public class Part1 {
    final static String FILE_PATH = "input.txt";

    public static void solveProblem(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);
        int count = 0;

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)"); // find mul(x,y)
        while (reader.hasNextLine()) {
            // use
            String line = reader.nextLine();

            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String nums[] = matcher.group()
                        .replaceAll("^\\D*|\\)", "") // remove all except x, y
                        .split(",");
                count += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
            }

        }
        System.out.println(count);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Part1.solveProblem(FILE_PATH);
    }
}

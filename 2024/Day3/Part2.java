import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solved
 * 
 * 
 */
public class Part2 {
    final static String FILE_PATH = "input.txt";

    public static void solveProblem(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);
        int count = 0;

        boolean doMul = true;
        String doDontPatternString = "do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)" + "|" + doDontPatternString);
        while (reader.hasNextLine()) {
            // use
            String line = reader.nextLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String command = matcher.group();

                // check if command is do,dont or mul (mul is covered by default case).
                switch (command) {
                    case "do()":
                        doMul = true;
                        break;
                    case "don't()":
                        doMul = false;
                        break;
                    default:
                        count += (doMul ? parseMulInstruction(command) : 0);
                        break;
                }
            }

        }
        System.out.println(count);

    }

    private static int parseMulInstruction(String mulInstruction) {
        String nums[] = mulInstruction.replaceAll("^\\D*|\\)", "").split(",");
        return Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Part2.solveProblem(FILE_PATH);
    }
}

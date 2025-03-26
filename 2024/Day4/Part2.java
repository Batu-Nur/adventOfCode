import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * There is some code repetititon some refactoring may be needed
 */
public class Part2 {
    final static String FILE_PATH = "input.txt";

    public static void solveProblem(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);

        String line = reader.nextLine();
        int lineLen = line.length();
        line += "\n";
        while (reader.hasNextLine()) {
            line += reader.nextLine() + "\n";
        }
        reader.close();
        System.out.println(findXmas(line, lineLen));

    }

    public static void main(String[] args) throws FileNotFoundException {
        Part2.solveProblem(FILE_PATH);

        String s = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX""";

        // System.out.println(findXmas(s, 10));

    }

    // "MAS" in an x shape (X-MAS) consists of 9 characters.
    // A must be in the middle so only the positions of M and S in the corners
    // changes.
    // other chars are ignored so we can denote the way to write "X-MAS" with as a
    // 4 character string of "m"and "s" chars there the first char of
    // strings represent the
    // top left corner of X-MAS and the last one the bottom right.
    // Observe that there is allways some symetry, top and bottom row or leftmost
    // and rightmost colums are same.
    // for all 4 ways to create X-mas MMSS, MSMS, SSMM, SMSM.

    /**
     * this method returns all 4 regex patterns that
     * match the X-MAS shapes
     * 
     * hardcoded
     */
    public static String[] generatePatterns(int lineLen) {

        String[] pattternList = new String[4];
        String linePattern = "(.|\\s){" + (lineLen - 2) + "}";
        pattternList[0] = "M.M" + linePattern + ".A." + linePattern + "S.S";
        pattternList[1] = "S.S" + linePattern + ".A." + linePattern + "M.M";

        pattternList[2] = "M.S" + linePattern + ".A." + linePattern + "M.S";
        pattternList[3] = "S.M" + linePattern + ".A." + linePattern + "S.M";

        return pattternList;
    }

    public static int findXmas(String input, int lineLen) {
        int count = 0;
        for (String patternString : generatePatterns(lineLen)) {
            // System.out.println(patternString);
            Matcher matcher = Pattern.compile(patternString).matcher(input);
            int i = 0;
            while (matcher.find(i)) {
                i = matcher.start() + 1;
                count++;
            }
        }
        return count;
    }
}

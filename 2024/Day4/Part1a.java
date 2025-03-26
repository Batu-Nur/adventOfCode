import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * A somewhat refactored version of Part1
 */
public class Part1a {
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
        // System.out.println(line);
        System.out.println(findInWordSearch(line, "XMAS", lineLen));

    }

    public static void main(String[] args) throws FileNotFoundException {
        Part1a.solveProblem(FILE_PATH);

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
        // generatPatterns("XMAS", 10);
        // System.out.println(findInWordSearch(s, "XMAS", 10));

    }

    public static int findInWordSearch(String input, String word, int lineLen) {
        int count = 0;

        for (String patternString : generatePatterns(word, lineLen)) {
            System.out.println(patternString);
            Matcher matcher = Pattern.compile(patternString).matcher(input);
            int i = 0;
            while (matcher.find(i)) {
                i = matcher.start() + 1;
                count++;
            }
        }

        return count;
    }

    // PROBLEM -> We may find same both horizontal and vertical sharing same start
    // pos.

    public static String[] generatePatterns(String word, int lineLen) {

        String[] patternString = new String[4];

        // diagonal vertical and diag left patterns
        // offset 0 makes vertical, the others diagonal
        int[] offsets = { -1, 0, 1 };

        for (int offset : offsets) {

            int index = 1 + offset;

            patternString[index] = "(";
            for (int i = 0; i < word.length() - 1; i++) {
                patternString[index] += word.charAt(i) + rowPattern(lineLen, offset);
            }
            patternString[index] += word.charAt(word.length() - 1);
            patternString[index] += ")|(";

            // reversed
            for (int i = word.length() - 1; i > 0; i--) {
                patternString[index] += word.charAt(i) + rowPattern(lineLen, offset);
            }
            patternString[index] += word.charAt(0);
            patternString[index] += ")";
        }

        // horisontal pattern
        patternString[3] = joinPatternStrings(word, new StringBuilder(word).reverse().toString());

        // System.out.println(Arrays.toString(patternString));
        return patternString;
    }

    public static String joinPatternStrings(String pat1, String pat2) {
        return "(" + pat1 + ")|(" + pat2 + ")";
    }

    public static String rowPattern(int lineLen, int offset) {
        return "(.|\\s){" + (lineLen + offset) + "}";
    }
}

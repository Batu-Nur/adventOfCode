import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * There is way to much code repetititon needs refactoring
 * Try to make functions for creating these patterns
 * then join these patterns and use them in the matcher instead
 */
public class Part1 {
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
        Part1.solveProblem(FILE_PATH);

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
        int count = findInWordSearch(s, "XMAS", 10);
        // System.out.println(count);

    }

    public static int findInWordSearch(String input, String word, int lineLen) {
        int count = 0;
        count += findWordsHorisontal(input, word);
        count += findWordsVertical(input, word, lineLen);
        count += findWordsDiagonal(input, word, lineLen);
        return count;
    }

    /**
     * Finds how many occurrences of the provided word exist int the input string.
     * Search done horisontally.
     * 
     * @param input word search input
     * @param word  the word to be searched
     * @return count of the words found
     */
    public static int findWordsHorisontal(String input, String word) {
        int count = 0;
        String reversedWord = new StringBuilder(word).reverse().toString();
        Pattern pattern = Pattern.compile("(" + word + ")|(" + reversedWord + ")");
        Matcher matcher = pattern.matcher(input);

        int i = 0;
        while (matcher.find(i)) {
            i = matcher.start() + 1;
            count++;
        }
        return count;
    }

    public static int findWordsVertical(String input, String word, int lineLen) {
        int count = 0;
        String nextlinePattern = "(.|\\s){" + lineLen + "}";

        // construct the pattern for downwards search
        String pattern = "(";
        for (int i = 0; i < word.length() - 1; i++) {
            pattern += word.charAt(i) + nextlinePattern;
        }
        pattern += word.charAt(word.length() - 1);
        pattern += ")|(";

        // construct the pattern for upwards search
        for (int i = word.length() - 1; i > 0; i--) {
            pattern += word.charAt(i) + nextlinePattern;
        }
        pattern += word.charAt(0) + ")";

        // System.out.println(pattern);
        Matcher matcher = Pattern.compile(pattern).matcher(input);

        int i = 0;
        while (matcher.find(i)) {
            i = matcher.start() + 1;

            count++;
        }
        return count;
    }

    public static int findWordsDiagonal(String input, String word, int lineLen) {
        return findWordsDiagonalRight(input, word, lineLen) + findWordsDiagonalLeft(input, word, lineLen);
    }

    public static int findWordsDiagonalRight(String input, String word, int lineLen) {
        int count = 0;
        String pattern = "(";

        // right to left diagonals
        String nextlinePattern = "(.|\\s){" + (lineLen + 1) + "}";
        for (int i = 0; i < word.length() - 1; i++) {
            pattern += word.charAt(i) + nextlinePattern;
        }
        pattern += word.charAt(word.length() - 1) + ")";

        pattern += "|(";
        for (int i = word.length() - 1; i > 0; i--) {
            pattern += word.charAt(i) + nextlinePattern;
        }
        pattern += word.charAt(0) + ")";

        // System.out.println(pattern);
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        int i = 0;
        while (matcher.find(i)) {
            i = matcher.start() + 1;
            count++;
        }
        return count;
    }

    public static int findWordsDiagonalLeft(String input, String word, int lineLen) {
        int count = 0;
        String pattern = "(";

        String nextlinePattern = "(.|\\s){" + (lineLen - 1) + "}";
        for (int i = 0; i < word.length() - 1; i++) {
            pattern += word.charAt(i) + nextlinePattern;
        }
        pattern += word.charAt(word.length() - 1);
        pattern += ")";

        pattern += "|(";
        // construct the pattern for upwards search
        for (int i = word.length() - 1; i > 0; i--) {
            pattern += word.charAt(i) + nextlinePattern;
        }
        pattern += word.charAt(0) + ")";

        // System.out.println(pattern);
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        int i = 0;
        while (matcher.find(i)) {
            i = matcher.start() + 1;
            count++;
        }
        return count;
    }

}

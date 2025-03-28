import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 
 */
public class Part1 {
    final static String FILE_PATH = "input.txt";
    final static String TESTFILE_PATH = "test.txt";

    static HashMap<Integer, HashSet<Integer>> ruleMap = new HashMap<Integer, HashSet<Integer>>();

    public static void solveProblem(String Path) throws FileNotFoundException {
        File file = new File(Path);
        Scanner reader = new Scanner(file);

        String rulePattern = "\\d+\\|\\d+";
        // parse rules
        while (reader.hasNext(rulePattern)) {
            int[] rules = Stream.of(reader.next(rulePattern).split("\\|"))
                    .mapToInt(Integer::parseInt).toArray();
            HashSet<Integer> set = ruleMap.get(rules[0]);
            if (set == null) {
                set = new HashSet<Integer>(rules[1]);
                ruleMap.put(rules[0], set);
            }
            set.add(rules[1]);
        }

        // parse page updates and check if they are correct

        int count = 0;

        while (reader.hasNextLine()) {
            int[] arr = Stream.of(reader.next().split(",")).mapToInt(Integer::parseInt).toArray();
            // System.out.println(Arrays.toString(arr));
            Map<Integer, Integer> pages = IntStream
                    .range(0, arr.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> arr[i], i -> i));

            if (!isUpdateOrderCorrect(pages, ruleMap)) {
                System.out.println(pages);
            }
        }

        reader.close();

        System.out.println(count);
    }

    public static boolean isUpdateOrderCorrect(Map<Integer, Integer> pages,
            HashMap<Integer, HashSet<Integer>> ruleMap) {

        for (Integer pagenum : pages.keySet()) {
            HashSet<Integer> ruleset = ruleMap.get(pagenum);
            if (ruleset == null) {
                continue;
            }
            Iterator<Integer> rulesetIter = ruleset.iterator();

            // check each successor in the ruleset of this pagenumber
            while (rulesetIter.hasNext()) {
                int successor = rulesetIter.next();

                // get index of the successor & if successor is ealier than pagenum then
                // ordering is wrong
                Integer index = pages.get(successor);
                if (index != null) {
                    if (pages.get(pagenum) > pages.get(successor)) {
                        return false;
                    }
                }

            }

        }

        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {

        solveProblem(TESTFILE_PATH);
    }

}

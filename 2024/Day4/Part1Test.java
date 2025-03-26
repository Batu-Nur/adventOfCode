import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Part1Test {
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

    String xmas = "XMAS";
    String smax = "SMAX";
    String conjoined = "XMASAMX";

    @Test
    public void findWordsHorisontal() {

        int count = Part1.findWordsHorisontal(xmas, xmas);
        assertEquals("Incorrect number found: " + count, count, 1);
    }

    @Test
    public void testfindMultipleHorisontalXmas() {

        String tested = "";
        int i = 0;
        for (i = 0; i < 10; i++) {
            tested += xmas;
        }
        int count = Part1.findWordsHorisontal(tested, xmas);
        assertEquals("Incorrect number found: " + count, count, i);
    }

    public void testfindReversedHorisontalXmas() {

        int count = Part1.findWordsHorisontal(smax, xmas);
        assertEquals("Incorrect number found: " + count, count, 1);
    }

    @Test
    public void testfindMultipleReversedHorisontalXmas() {
        String xmas = "SMAX";
        String tested = "";
        int i = 0;
        for (i = 0; i < 10; i++) {
            tested += xmas;
        }
        int count = Part1.findWordsHorisontal(tested, xmas);
        assertEquals("Incorrect number found: " + count, count, i);
    }

    @Test
    public void findConjoinedWordsHorisontal() {

        int count = Part1.findWordsHorisontal(conjoined, xmas);
        assertEquals("Incorrect number found: " + count, count, 2);
    }

    @Test
    public void findConjoinedXmasVertical() {
        String in = "";
        for (int i = 0; i < conjoined.length(); i++) {
            in += "AB" + conjoined.charAt(i) + "CD\n";
        }
        int count = Part1.findWordsVertical(in, xmas, 5);
        assertEquals("Incorrect number found: " + in, count, 2);
    }

    @Test
    public void testAll() {
        assertEquals(Part1.findInWordSearch(s, "XMAS", 10), 18);
        assertEquals(Part1.findWordsHorisontal(s, "XMAS"), 5);
        assertEquals(Part1.findWordsVertical(s, "XMAS", 10), 3);
        assertEquals(Part1.findWordsDiagonal(s, "XMAS", 10), 10);
    }
}

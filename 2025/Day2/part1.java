import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.w3c.dom.ranges.Range;

public class part1 {
    


    public part1() throws FileNotFoundException {
       
    }

    public void solve(String filePath) throws FileNotFoundException{
        ArrayList<Long[]> ranges = readFromFile(filePath);
        ArrayList<Long> invalidIds = new ArrayList<>();
        
        Long sum  = 0L;
        for (Long[] integers : ranges) {
            for (Long i = integers[0]; i <= integers[1]; i++) {

                if (hasInvalidRepetitions(i)) {
                    invalidIds.add(i);
                    sum += i;
                }
            }
        }

        System.out.println(invalidIds.toString());

        // add all numbers in validids
        System.out.println(sum);
    }



    // should have done is valid and assume invalid, only 
    // set is valid bool to true if either odd or both halves not equal
    public boolean hasInvalidRepetitions(Long number) {
        // if number does not have even number of digits, skip as it cannot have proper
        // repetitions

        int numLen = (int) (Math.log10(number) + 1);
        if (numLen % 2 != 0) {
            return false;
        }

       

        // divide number to two halves, upper and lower and check equality
        Long upper = number / (int) Math.pow(10, numLen / 2);
        Long lower = number % (int) Math.pow(10, numLen / 2);
        //System.out.println("number: " + number + " upper: " + upper + " lower: " + lower);
       

        return Long.compare(upper, lower) == 0;
    }


    public ArrayList<Long[]> readFromFile(String filePath) throws FileNotFoundException {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            reader.useDelimiter(",");
            
            ArrayList<Long[]> ranges = new ArrayList<>();
            while(reader.hasNext()){
                String[] idRange = reader.next().trim().split("-");
                
                ranges.add(new Long[]{ Long.decode(idRange[0]) , Long.decode(idRange[1])}); 
            }
            reader.close();
   
         return ranges;   
        }

    public static void main(String[] args) throws FileNotFoundException {
        final String FILE_PATH = "2025\\Day2\\input.txt";
        final String TEST_PATH = "2025\\Day2\\test.txt";
        new part1().solve(TEST_PATH);
        
        // System.out.println(new part1().hasInvalidRepetitions(Long.valueOf(1188511885)));
    }

}

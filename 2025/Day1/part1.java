import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class part1 {
    final String FILE_PATH = "2025\\Day1\\input.txt";

    private int position;

    public part1(int position) throws FileNotFoundException {
        this.position = position;
        this.solve(this.readFromFile());
    }

    public void solve(ArrayList<Integer> rotations){
        int zerotimes = 0;

        for (int rotation : rotations) {
            position =  (position + rotation)  % 100;
            if(position ==  0)
                zerotimes++;
        }
        System.out.println(zerotimes); 
    }


    public ArrayList<Integer> readFromFile() throws FileNotFoundException {
            File file = new File(FILE_PATH);
            Scanner reader = new Scanner(file);
            ArrayList<Integer> numbers = new ArrayList<>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int number = Integer.parseInt(line.substring(1)) * (line.charAt(0) == 'R' ? 1 : -1);
                // System.out.println("Line: " + line + ", Number: " + number);
                numbers.add(number);
            }
            reader.close();
            return numbers;
        }

    public static void main(String[] args) throws FileNotFoundException {
        new part1(50);
        
        
    }

}

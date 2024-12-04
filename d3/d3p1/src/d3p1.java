import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class d3p1 {
    public static void main(String[] args) {

        File file = new File("D:\\JavaProjects\\AOC_24\\d3\\d3p1\\src\\input.txt");
        int total = 0;

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] array_ofLine_split_by_mul = line.split("mul");
                for (int i = 0; i < array_ofLine_split_by_mul.length; i++) {
                    if (array_ofLine_split_by_mul[i].matches("^[(]\\d{1,3},\\d{1,3}[)].*")) {
                        String without_first_parenthesis = array_ofLine_split_by_mul[i].substring(1);
                        String without_both_parentheses = without_first_parenthesis.substring(0, without_first_parenthesis.indexOf(")"));
                        String[] string_array_numbers = without_both_parentheses.split(",");
                        int[] numbers = new int[2];
                        numbers[0] = Integer.parseInt(string_array_numbers[0]);
                        numbers[1] = Integer.parseInt(string_array_numbers[1]);
                        total += numbers[1] * numbers[0];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        System.out.println(total);
    }
}

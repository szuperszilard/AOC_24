import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("D:\\JavaProjects\\AOC_24\\d3\\d3p1\\src\\input.txt");
        int total = 0;
        StringBuilder all_strings_together = new StringBuilder();
        try {

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                all_strings_together.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");

        }
        String[] array_split_by_dont = all_strings_together.toString().split("don't()");
        for(int i = 0; i < array_split_by_dont.length; i++) {
            if(i == 0){
                total += SumMulInString(array_split_by_dont[i]);
            }else if(array_split_by_dont[i].contains("do()")){
                String[] doSplit = array_split_by_dont[i].split("do()");
                StringBuilder stringWithoutFirstDo = new StringBuilder();
                for(int k = 1; k < doSplit.length; k++) {
                    stringWithoutFirstDo.append(doSplit[k]);
                }
                total += SumMulInString(stringWithoutFirstDo.toString());

            }
        }
        System.out.println(total);
    }

    public static int SumMulInString(String toBeChecked){

        int sum = 0;
        String[] array_ofLine_split_by_mul =toBeChecked.split("mul");
        for (int j = 0; j < array_ofLine_split_by_mul.length; j++) {
            if (array_ofLine_split_by_mul[j].matches("^[(]\\d{1,3},\\d{1,3}[)].*")) {
                String without_first_parenthesis = array_ofLine_split_by_mul[j].substring(1);
                String without_both_parentheses = without_first_parenthesis.substring(0, without_first_parenthesis.indexOf(")"));
                String[] string_array_numbers = without_both_parentheses.split(",");
                sum += Integer.parseInt(string_array_numbers[0]) * Integer.parseInt(string_array_numbers[1]);
            }
        }
        return sum;
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> first_Column = new ArrayList<>();
        ArrayList<Integer> second_Column = new ArrayList<>();
        File file = new File("D:\\JavaProjects\\AoC2024\\aoc_d1_p1\\src\\data.txt");
        Reader(file, first_Column, second_Column);

        long total = 0;
        for(int i = 0; i < first_Column.size(); i++){
            total += Math.abs(first_Column.get(i) - second_Column.get(i));
        }
        System.out.println(total);
    }
    public static void Reader(File file, ArrayList<Integer> first, ArrayList<Integer> second){

        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String line = sc.nextLine().replaceAll("\\s+", ",");;
                String[] line_array = line.split(",");
                first.add(Integer.parseInt(line_array[0]));
                second.add(Integer.parseInt(line_array[1]));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        Collections.sort(first);
        Collections.sort(second);
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("D:\\JavaProjects\\AOC_24\\d4\\d4p1\\src\\in.txt");
        String[][] data = new String[140][140];
        int total_X = 0;

        try {
            Scanner sc = new Scanner(file);
            int row_counter = 0;

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    data[row_counter][i] = String.valueOf(line.charAt(i));
                }
                row_counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        for(int i = 1; i < data.length-1; i++){
            for(int j = 1; j < data.length-1; j++){
                if(Objects.equals(data[i][j], "A")){
                    if(IsItXMAS(i, j, data)){
                        total_X++;
                    }
                }
            }
        }
        System.out.println(total_X);
    }
    public static boolean IsItXMAS(int i, int j, String[][] data){

        boolean isItXMAS = false;
        StringBuilder corners = new StringBuilder();
        corners.append(data[i-1][j-1]);
        corners.append(data[i+1][j-1]);
        corners.append(data[i+1][j+1]);
        corners.append(data[i-1][j+1]);
        
        if(Objects.equals(corners.toString(), "SSMM") || Objects.equals(corners.toString(), "MSSM") || Objects.equals(corners.toString(), "MMSS") || Objects.equals(corners.toString(), "SMMS")){
            isItXMAS = true;
        }
        return isItXMAS;
    }
}

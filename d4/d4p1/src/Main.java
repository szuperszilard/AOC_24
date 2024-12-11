import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("D:\\JavaProjects\\AOC_24\\d4\\d4p1\\src\\in.txt");
        String[][] data = new String[140][140];
        int total_XMAS = 0;


        try{

            Scanner sc = new Scanner(file);
            int row_counter = 0;

            while(sc.hasNextLine()){

                String line = sc.nextLine();
                total_XMAS += CountInString(line);

                for(int i = 0; i < line.length(); i++){
                    data[row_counter][i] = String.valueOf(line.charAt(i));
                }
                row_counter++;
            }
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }

        for(int i = 0; i < data.length; i++){
            StringBuilder columnBuilder = new StringBuilder();
            for(int j = 0; j < data.length; j++){
                columnBuilder.append(data[j][i]);
            }
            String column = columnBuilder.toString();
            total_XMAS += CountInString(column);
        }
        for(int i = 1; i <= data.length; i++){
            StringBuilder diagonalBuilder = new StringBuilder();
            for(int j = 1; j <= i; j++){
                diagonalBuilder.append(data[i-j][j-1]);
            }
            String diagonal = diagonalBuilder.toString();
            total_XMAS += CountInString(diagonal);
        }


        int i = data.length-1, i_origin = data.length-1;
        int j = 1, j_origin = 1;
        for( int elements = data.length-1; elements >= 1; elements--){
            StringBuilder builder = new StringBuilder();
            while( j != data.length){
                builder.append(data[i][j]);
                i--;
                j++;
            }
            i = i_origin;
            j_origin++;
            j = j_origin;
            String string = builder.toString();
            total_XMAS += CountInString(string);
        }

        int j_start = 0;
        for(int elements = 1; elements <= data.length; elements++){
            StringBuilder builder2 = new StringBuilder();
                i = data.length-1;
                j = j_start;
            for(int k = 1; k <= elements; k++){
                builder2.append(data[i][j]);
                i--;
                j--;
            }
            String string = builder2.toString();
            total_XMAS += CountInString(string);
            j_start++;
        }

        int i_start = 0;
        for(int elements = 1; elements <= data.length-1; elements++){
            StringBuilder builder3 = new StringBuilder();
            j = data.length-1;
            i = i_start;
            for(int k = 1; k <= elements; k++){
                builder3.append(data[i][j]);
                j--;
                i--;
            }
            String string = builder3.toString();
            total_XMAS += CountInString(string);
            i_start++;
        }

        System.out.println(total_XMAS);
    }
    public static int CountInString(String string){
        int total = 0;
        String toFindForward = "XMAS";
        String toFindBackward = "SAMX";
        total += string.split(toFindForward, -1).length-1;
        total += string.split(toFindBackward, -1).length-1;
        return total;
    }
}

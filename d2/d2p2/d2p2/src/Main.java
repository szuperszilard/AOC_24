import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        File file = new File("D:\\JavaProjects\\AOC_24\\d2\\d2p1\\src\\input.txt");
        int safe_path_counter = 0;
        try{

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] string_array = line.split(" ");
                int[] int_array = new int[string_array.length];
                for(int i = 0; i < string_array.length; i++){
                    int_array[i] = Integer.parseInt(string_array[i]);
                }
                if(CheckIfSafe(int_array)){
                    safe_path_counter++;
                }else{
                    for(int i = 0; i < int_array.length; i++){
                        int[] new_array = new int[int_array.length-1];
                        for(int j = 0, k = 0; j < int_array.length; j++){
                            if(j != i){
                                new_array[k] = int_array[j];
                                k++;
                            }
                        }
                        if(CheckIfSafe(new_array)){
                            safe_path_counter++;
                            break;
                        }
                    }
                }
            }

        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }

        System.out.println(safe_path_counter);
    }
    public static boolean CheckIfSafe(int[] array){

        return (CheckDifference(array) && CheckOrdered(array));
    }

    public static boolean CheckOrdered(int[] array){

        boolean order = false;
        boolean descending = true;

        int[] ascending_order_array = array.clone();
        int[] original_array = array.clone();
        Arrays.sort(ascending_order_array);


        if(Arrays.equals(ascending_order_array, original_array)){
            order = true;
        }else if(true){

            for (int i = original_array.length - 1; i >= 0; i--) {
                if (ascending_order_array[original_array.length - i - 1] != original_array[i]) {
                    descending = false;
                    break;
                }
            }
        }
        if(descending){
            order = true;
        }

        return order;
    }

    public static boolean CheckDifference(int[] array){

        boolean good = true;
        for(int i = 0; i < array.length-1; i++){
            if(Math.abs(array[i+1] - array[i]) > 3 )  {
                good = false;
                break;
            }else if(Math.abs(array[i+1] - array[i]) < 1){
                good = false;
                break;
            }
        }
        return good;
    }

}

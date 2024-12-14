import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File pairs = new File("D:\\JavaProjects\\AOC_24\\d5\\pairs.txt");
        File updates = new File("D:\\JavaProjects\\AOC_24\\d5\\updates.txt");
        ArrayList<int[]> updatesList = new ArrayList<>();
        ArrayList<int[]> pairsList = new ArrayList<>();
        int totalSUM = 0;

        try{

            Scanner sc = new Scanner(pairs);
            Scanner scan = new Scanner(updates);


            while(scan.hasNextLine()){

                String line = scan.nextLine();
                String[] updatesStringArray = line.split(",");
                int[] updatesIntArray = new int[updatesStringArray.length];
                for(int i = 0; i < updatesStringArray.length; i++){
                    updatesIntArray[i] = Integer.parseInt(updatesStringArray[i]);
                }
                updatesList.add(updatesIntArray);
            }
            while(sc.hasNextLine()){

                String line = sc.nextLine();
                String[] pairsStringArray = line.split("\\|");
                int[] pairsIntArray = new int[2];
                for(int i = 0; i < 2; i++){
                    pairsIntArray[i] = Integer.parseInt(pairsStringArray[i]);
                }
                pairsList.add(pairsIntArray);
            }

        }catch(FileNotFoundException e){
            System.out.println("file not found");
        }

        for(int i = 0; i < updatesList.size(); i++){

            if(Checker(updatesList.get(i), pairsList)){
                int[] updateToFindMiddle = new int[updatesList.get(i).length];
                int middleIndex = updateToFindMiddle.length/2;
                totalSUM += updatesList.get(i)[middleIndex];
            }

        }
        System.out.println(totalSUM);

    }
    public static boolean Checker (int[] update, ArrayList<int[]> listPairs){

        boolean isOK = true;
        int startIndex = 1;

        for(int i = 0; i < update.length-1; i++){
            for(int j = startIndex; j < update.length; j++){
                int[] currentPair = new int[2];
                currentPair[0] = update[i];
                currentPair[1] = update[j];
                if(listPairs.stream().noneMatch(a -> Arrays.equals(a, currentPair))){
                  isOK = false;
                  break;
                }
            }
            startIndex++;
        }
        return isOK;
    }

}

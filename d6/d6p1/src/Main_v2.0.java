import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static boolean end = false;
    public static int iCurrent = 41;
    public static int jCurrent = 96;
    public static int moveCounter = 1;
    public static String direction = "^";

    public static void main(String[] args) {

        File file = new File("D:\\JavaProjects\\AOC_24\\d6\\in.txt");
        String[][] map = new String[130][130];

        try{

            Scanner sc = new Scanner(file);
            int rowCounter = 0;

            while(sc.hasNextLine()){

                String line = sc.nextLine();
                map[rowCounter] = line.split("");
                rowCounter++;
            }

        }catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }

        while(!end){
            switch(direction){

                case "^":
                    MoveUp(map, iCurrent, jCurrent);
                    break;
                case ">":
                    MoveRight(map, iCurrent,jCurrent);
                    break;
                case "v":
                    MoveDown(map, iCurrent,jCurrent);
                    break;
                case "<":
                    MoveLeft(map, iCurrent,jCurrent);
                    break;
            }
        }
        System.out.println(moveCounter);
    }
    public static void MoveUp (String[][] map, int iStart, int jStart){

        if(iStart == 0){
            end = true;
        }else if(Objects.equals(map[iStart - 1][jStart], "#")){
            direction = ">";
           MoveRight(map, iStart, jStart);
        }else{
            Swap(map, iStart, jStart);
            iCurrent--;
        }

    }
    public static void MoveDown (String[][] map, int iStart, int jStart){

        if(iStart == 129){
            end = true;
        }else if(Objects.equals(map[iStart + 1][jStart], "#")){
            direction = "<";
            MoveLeft(map, iStart, jStart);
        }else{
            Swap(map, iStart, jStart);
            iCurrent++;
        }
    }
    public static void MoveRight (String[][] map, int iStart, int jStart){

        if(jStart == 129){
            end = true;
        }else if(Objects.equals(map[iStart][jStart + 1], "#")){
            direction = "v";
            MoveDown(map, iStart, jStart);
        }else{
            Swap(map, iStart, jStart);
            jCurrent++;
        }
    }
    public static void MoveLeft (String[][] map, int iStart, int jStart){

        if(jStart == 0){
            end = true;
        }else if(Objects.equals(map[iStart][jStart - 1], "#")){
            direction = "^";
            MoveUp(map, iStart, jStart);
        }else{
            Swap(map, iStart, jStart);
            jCurrent--;
        }
    }
    public static void Swap (String[][] map, int iStart, int jStart){

        switch (direction){
            case "^":
                if(Objects.equals(map[iStart-1][jStart], ".") || Objects.equals(map[iStart-1][jStart], "#")){
                    moveCounter++;
                }
                map[iStart][jStart] = "^";
                break;

            case ">":
                if(Objects.equals(map[iStart][jStart+1], ".") || Objects.equals(map[iStart][jStart+1], "#")){
                    moveCounter++;
                }
                map[iStart][jStart] = ">";
                break;

            case "v":
                if(Objects.equals(map[iStart+1][jStart], ".") || Objects.equals(map[iStart+1][jStart], "#")){
                    moveCounter++;
                }
                map[iStart][jStart] = "v";
                break;

            case "<":
                if(Objects.equals(map[iStart][jStart-1], ".") || Objects.equals(map[iStart][jStart-1], "#")){
                    moveCounter++;
                }
                map[iStart][jStart] = "<";
                break;
        }
    }
}

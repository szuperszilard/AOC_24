import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static boolean end = false;
    public static int iCurrent = 41;
    public static int jCurrent = 96;
    public static int moveCounter = 1;
    public static int loopCounter = 0;
    public static boolean inLoop = false;
    public static String direction = "^";

    public static void main(String[] args) {

        File file = new File("D:\\REPO\\AOC_24\\d6\\in.txt");
        String[][] map = new String[130][130];


        for(int whereToChange = 1; whereToChange <= 5067; whereToChange++) {            //iterate the place of change to "#"

            try{

                Scanner sc = new Scanner(file);
                int rowCounter = 0;

                while(sc.hasNextLine()){

                    String line = sc.nextLine();
                    map[rowCounter] = line.split("");
                    rowCounter++;
                }

            }catch(FileNotFoundException e)                 //read file to array
            {
                System.out.println("File not found");
            }
            moveCounter = 1;

            while (!end) {

                if(moveCounter == whereToChange) {

                    switch (map[iCurrent][jCurrent]) {

                        case "^":
                            map[iCurrent - 1][jCurrent] = "#";
                            break;
                        case ">":
                            map[iCurrent][jCurrent + 1] = "#";
                            break;
                        case "v":
                            map[iCurrent + 1][jCurrent] = "#";
                            break;
                        case "<":
                            map[iCurrent][jCurrent - 1] = "#";
                            break;
                    }
                }               //put "#"

                switch (direction) {
                    case "^":
                        MoveUp(map, iCurrent, jCurrent);
                        break;
                    case ">":
                        MoveRight(map, iCurrent, jCurrent);
                        break;
                    case "v":
                        MoveDown(map, iCurrent, jCurrent);
                        break;
                    case "<":
                        MoveLeft(map, iCurrent, jCurrent);
                        break;
                }

            }
        }
        System.out.println(loopCounter);
    }
    public static void MoveUp (String[][] map, int iStart, int jStart){

        if(iStart == 0){
            end = true;
        }else if(Objects.equals(map[iStart - 1][jStart], "#")){
            map[iStart][jStart] = ">";
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
            map[iStart][jStart] = "<";
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
            map[iStart][jStart] = "v";
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
            map[iStart][jStart] = "^";
            MoveUp(map, iStart, jStart);
        }else{
            Swap(map, iStart, jStart);
            jCurrent--;
        }
    }
    public static void Swap (String[][] map, int iStart, int jStart){

        switch (map[iStart][jStart]){
            case "^":
                if(!Objects.equals(map[iStart-1][jStart], "..")){
                    moveCounter++;
                }
                map[iStart-1][jStart] = "^";
                map[iStart][jStart] = "..";
                break;

            case ">":
                if(!Objects.equals(map[iStart][jStart+1], "..")){
                    moveCounter++;
                }
                map[iStart][jStart+1] = ">";
                map[iStart][jStart] = "..";
                break;

            case "v":
                if(!Objects.equals(map[iStart+1][jStart], "..")){
                    moveCounter++;
                }
                map[iStart+1][jStart] = "v";
                map[iStart][jStart] = "..";
                break;

            case "<":
                if(!Objects.equals(map[iStart][jStart-1], "..")){
                    moveCounter++;
                }
                map[iStart][jStart-1] = "<";
                map[iStart][jStart] = "..";
                break;
        }
    }
}

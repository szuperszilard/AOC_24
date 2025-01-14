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
    public static int iWhereToPutX = 0;
    public static int jWhereToPutX = 0;
    public static String[][] pathOfMovement = new String[130][130];



    public static void main(String[] args) {
        long start = System.nanoTime();


        File file = new File("D:\\REPO\\AOC_24\\d6\\in.txt");
        String[][] map = new String[130][130];
        for(int i = 0; i < pathOfMovement.length; i++){
            for(int j = 0; j < pathOfMovement.length; j++){
                pathOfMovement[i][j] = "a";
            }
        }

        for(int whereToChange = 1; whereToChange <= 5632; whereToChange++) {            //iterate the place of change to "#" over the whole trail

            try {

                Scanner sc = new Scanner(file);
                int rowCounter = 0;

                while (sc.hasNextLine()) {

                    String line = sc.nextLine();
                    map[rowCounter] = line.split("");
                    rowCounter++;
                }

            } catch (FileNotFoundException e)                 //read file to array
            {
                System.out.println("File not found");
            }

            moveCounter = 1;
            inLoop = false;
            end = false;
            iCurrent = 41;
            jCurrent = 96;
            direction = "^";

            while (!end) {

                if(moveCounter == whereToChange) {

                    switch (direction) {

                        case "^":
                            PutHashUp(map, iCurrent, jCurrent);
                            break;
                        case ">":
                            PutHashRight(map, iCurrent, jCurrent);
                            break;
                        case "v":
                            PutHashDown(map, iCurrent, jCurrent);
                            break;
                        case "<":
                            PutHashLeft(map, iCurrent, jCurrent);
                            break;
                    }
                }             //put "#"

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

                if (inLoop) {
                    if(pathOfMovement[iWhereToPutX][jWhereToPutX].equals("ax")) {
                        loopCounter++;
                    }
                    break;
                }
            }
        }
        System.out.println(loopCounter);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }
    public static void MoveUp (String[][] map, int iStart, int jStart){

        if(iStart == 0){
            end = true;
        }else if(Objects.equals(map[iStart - 1][jStart], "#")){
            direction = ">";
            MoveRight(map, iStart, jStart);
        }else{
            map[iStart][jStart] = map[iStart][jStart] + "^";
            moveCounter++;
            iCurrent--;
            if(LoopChecker(map, iStart, jStart)){
                inLoop = true;
            }
        }

    }
    public static void MoveDown (String[][] map, int iStart, int jStart){

        if(iStart == 129){
            end = true;
        }else if(Objects.equals(map[iStart + 1][jStart], "#")){
            direction = "<";
            MoveLeft(map, iStart, jStart);
        }else{
            map[iStart][jStart] = map[iStart][jStart] + "v";
            moveCounter++;
            iCurrent++;
            if(LoopChecker(map, iStart, jStart)){
                inLoop = true;
            }
        }
    }
    public static void MoveRight (String[][] map, int iStart, int jStart){

        if(jStart == 129){
            end = true;
        }else if(Objects.equals(map[iStart][jStart + 1], "#")){
            direction = "v";
            MoveDown(map, iStart, jStart);
        }else{
            map[iStart][jStart] = map[iStart][jStart] + ">";
            moveCounter++;
            jCurrent++;
            if(LoopChecker(map, iStart, jStart)){
                inLoop = true;
            }
        }
    }
    public static void MoveLeft (String[][] map, int iStart, int jStart){

        if(jStart == 0){
            end = true;
        }else if(Objects.equals(map[iStart][jStart - 1], "#")){
            direction = "^";
            MoveUp(map, iStart, jStart);
        }else{
            map[iStart][jStart] = map[iStart][jStart] + "<";
            moveCounter++;
            jCurrent--;
            if(LoopChecker(map, iStart, jStart)){
                inLoop = true;
            }
        }
    }
    public static void PutHashUp (String[][] map, int iStart, int jStart){

        if(Objects.equals(map[iStart - 1][jStart], "#")){
            PutHashRight(map, iStart, jStart);
        }else{
            map[iStart - 1][jStart] = "#";
            pathOfMovement[iStart - 1][jStart] = pathOfMovement[iStart - 1][jStart] + "x";
            iWhereToPutX = iStart - 1;
            jWhereToPutX = jStart;
        }
    }
    public static void PutHashRight (String[][] map, int iStart, int jStart){

        if(Objects.equals(map[iStart][jStart + 1], "#")){
            PutHashDown(map, iStart, jStart);
        }else{
            map[iStart][jStart + 1] = "#";
            pathOfMovement[iStart][jStart + 1] = pathOfMovement[iStart][jStart + 1] + "x";
            iWhereToPutX = iStart;
            jWhereToPutX = jStart + 1;

        }
    }
    public static void PutHashDown (String[][] map, int iStart, int jStart){

        if(Objects.equals(map[iStart + 1][jStart], "#")){
            PutHashLeft(map, iStart, jStart);
        }else{
            map[iStart + 1][jStart] = "#";
            pathOfMovement[iStart + 1][jStart] = pathOfMovement[iStart + 1][jStart] + "x";
            iWhereToPutX = iStart + 1;
            jWhereToPutX = jStart;
        }
    }
    public static void PutHashLeft (String[][] map, int iStart, int jStart){

        if(Objects.equals(map[iStart][jStart - 1], "#")){
            PutHashUp(map, iStart, jStart);
        }else{
            map[iStart][jStart - 1] = "#";
            pathOfMovement[iStart][jStart - 1] = pathOfMovement[iStart][jStart - 1] + "x";
            iWhereToPutX = iStart;
            jWhereToPutX = jStart - 1;
        }
    }
    public static boolean LoopChecker (String[][] map, int i, int j) {

        return map[i][j].contains("<<") || map[i][j].contains(">>")
                || map[i][j].contains("vv") || map[i][j].contains("^^")
                || map[i][j].contains("<><") || map[i][j].contains("><>")
                ||map[i][j].contains("^v^") || map[i][j].contains("v^v");
    }
}
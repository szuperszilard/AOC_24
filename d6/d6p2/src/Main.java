import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static boolean end = false;
    public static int iCurrent = 0;
    public static int jCurrent = 0;
    public static int moveCounter = 1;

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
        loop1:
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){
                if(Objects.equals(map[i][j], "^")){
                    iCurrent = i;
                    jCurrent = j;
                    break loop1;
                }
            }
        }
        while(!end){
            switch(map[iCurrent][jCurrent]){

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

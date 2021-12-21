package DotCom.DeluxeDotComGame;

import java.util.Scanner;
import java.util.ArrayList;

public class GameHelper{

    private static final String letters = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.print(prompt + " ");
        String s = input.next();
        input.close();
        return s.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize){
        ArrayList<String> alphaCells = new ArrayList<String>();

        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        this.comCount++;
        int incre = 1;
        if((comCount % 2) == 1){
            incre = gridLength;
        }

        while(!success & attempts++ < 200){
            location = (int) (Math.random() * gridSize);

            int x = 0;
            success = true;
            while(success && x < comSize){
                if (grid[location] == 0){
                    coords[x++] = location;
                    location += incre;
                    if(location >= gridSize){
                        success = false;
                    }
                    if(x > 0 && (location % gridLength == 0)){
                        success = false;
                    }
                }
                else{
                    success = false;
                }
            }
        }

        int x = 0;
        int row = 0;
        int column = 0;
        while (x < comSize){
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(letters.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }

        return alphaCells;
    }
}
package DotCom.SimpleDotCom;

import java.util.Scanner;

public class SimpleDotComGame{

    public static void main(String[] args){
        
        SimpleDotCom dot = new SimpleDotCom();

        int numGuesses = 0;
        
        int startCell = (int) (Math.random() * 5);
        int[] locs = {startCell, startCell + 1, startCell + 2};
        dot.setLocationCells(locs);

        boolean isAlive = true;

        Scanner input = new Scanner(System.in);

        while(isAlive){
            System.out.print("What is your guess ?? ");
            String guess = input.next();
            numGuesses++;

            String result = dot.checkYourself(guess);
            if (result.equals("kill")){
                isAlive = false;
                System.out.println("you took " + numGuesses + " guesses");
            }
        }
        input.close();
    }
}
package DotCom.SimpleDotCom;

public class SimpleDotCom {

    // instance variables
    int[] locationCells;
    int numHits = 0;

    // constructor
    SimpleDotCom(){

    }

    // instance methods
    String checkYourself(String guess){
        int numGuess = Integer.parseInt(guess);
        String result = "miss";
        for (int cell : locationCells){
            if (cell == numGuess){
                result = "hit";
                numHits++;
                break;
            }
        }
        if (numHits == locationCells.length){
            result = "kill";
        }
        System.out.println(result);
        return result;
    }

    void setLocationCells(int[] loc){
        this.locationCells = loc;
    }
}
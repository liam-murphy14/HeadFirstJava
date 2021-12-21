package DotCom.DeluxeDotComGame;

import java.util.ArrayList;

public class DeluxeDotComGame{

    private GameHelper helper = new GameHelper();
    private ArrayList<DeluxeDotCom> dcList = new ArrayList<DeluxeDotCom>();
    private int numGuesses = 0;

    private void setUp(){
        DeluxeDotCom dc1 = new DeluxeDotCom();
        DeluxeDotCom dc2 = new DeluxeDotCom();
        DeluxeDotCom dc3 = new DeluxeDotCom();
        dc1.setName("Google.com");
        dc2.setName("GitHub.com");
        dc3.setName("Facebook.com");
        dcList.add(dc1);
        dcList.add(dc2);
        dcList.add(dc3);
        System.out.println("Try to sink three dot coms. Like battleship");
        System.out.println(dc1.getName() + " " + dc2.getName() + " " + dc3.getName());
        for (DeluxeDotCom dc : dcList){
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dc.setLocs(newLocation);
        }
    }
    private void startGame(){
        while(!(dcList.isEmpty())){
            String guess = helper.getUserInput("Enter a guess");
            checkUserGuess(guess);
        }
        finishGame();
    }
    private void play(){
        setUp();
        startGame();
    }
    private void checkUserGuess(String guess){
        numGuesses++;
        String result = "miss";
        for (DeluxeDotCom dc : dcList){
            result = dc.checkYourself(guess);
            if(result.equals("hit")){
                break;
            }
            if(result.equals("kill")){
                dcList.remove(dc);
                break;
            }
        }
        System.out.println(result);
    }
    private void finishGame(){
        System.out.println("All Dot Coms are dead. Your stock value is $0.00");
        if(numGuesses <= 18){
            System.out.println("It only took you " + numGuesses + " guesses.");
            System.out.println("Your options are not completely worthless");
        }
        else{
            System.out.println("it only took you " + numGuesses + " guesses.");
            System.out.println("You might need a little practice");
        }
    }
    public static void main(String[] args){
        DeluxeDotComGame game = new DeluxeDotComGame();
        game.play();
    }
}

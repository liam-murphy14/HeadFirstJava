package DotCom.DeluxeDotComGame;

import java.util.ArrayList;

public class DeluxeDotCom{

    private ArrayList<String> locationCells;
    private String name;

    public void setLocs(ArrayList<String> locs){
        this.locationCells = locs;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String checkYourself(String input){
        String result = "miss";
        int i = locationCells.indexOf(input);
        if (i >= 0){
            locationCells.remove(i);
            if (locationCells.isEmpty()){
                result = "kill";
                System.out.println("you sunk " + name);
            }
            else{
                result = "hit";
            }
        }
        return result;
    }
}

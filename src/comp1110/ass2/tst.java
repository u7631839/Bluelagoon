package comp1110.ass2;

import comp1110.ass2.D2B.Player;

import java.util.Set;
import comp1110.ass2.D2B.Player;

import static comp1110.ass2.BlueLagoon.generateAllValidMoves;
import static comp1110.ass2.D2B.Util.getNumPlayers;

public class tst {
    public static void main(String[] args) {
        String stateString="a 13 2; c 0 S; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 0,5 1,12 10,6 B 0,0 1,4 2,1 6,6 11,5 W 3,7 3,10 4,2 7,12 8,5 P 1,8 3,12 5,9 7,8 9,9 10,10 S 0,9 3,5 4,0 5,11 6,3 7,0 12,8 12,11; p 0 38 2 0 1 0 0 S 7,3 7,4 8,2 8,4 9,0 9,3 9,4 9,5 10,3 T 9,1 9,2 11,4 12,4; p 1 79 1 1 0 0 0 S 10,0 10,1 10,2 11,0 11,1 11,2 11,3 12,2 12,3 T 12,0 12,1;";
        String resource_string= Resources.getResourcesString(stateString);
        StringBuilder stringBuilder=new StringBuilder(stateString);
        int bool=0;
        int player_num = getNumPlayers(stateString);
        for (int i=0;i<player_num;i++){
            stringBuilder.replace(stringBuilder.indexOf("c")+2, stringBuilder.indexOf("c")+3, String.valueOf(i));
            Set<String> valid_moves= generateAllValidMoves(stateString);
            int player_element= Player.getRemainingSettlers(stateString, i, player_num)+Player.getRemainingVillages(stateString,i);
            if (valid_moves.size()==0 || player_element==0){
                System.out.println("玩家"+i+"还有"+valid_moves.size());
                System.out.println(valid_moves);
                bool++;
            }
        }
        if (bool==player_num){
            System.out.println("玩家可以结束了");
        }

        // FIXME Task 9
    }
}

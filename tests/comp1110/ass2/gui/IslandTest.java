package comp1110.ass2.gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
   /* public static  String DEFAULT_GAME_1 = "i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1;";
    public static  String DEFAULT_GAME_2="i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8;";
    public static  String DEFAULT_GAME_3="i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11;";
    public static  String DEFAULT_GAME_4="i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12;";
    public static  String DEFAULT_GAME_5="i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2;";
    public static  String DEFAULT_GAME_6="i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5;";
    public static  String DEFAULT_GAME_7="i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5;";
    public static  String DEFAULT_GAME_8="i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8;";
    public static  String DEFAULT_GAME_stone="s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11;";
    public static  String WHEELS_GAME_1 = "i 5 0,1 0,2 0,3 0,4 1,1 1,5 2,0 2,5 3,0 3,6 4,0 4,5 5,1 5,5 6,1 6,2 6,3 6,4;";
    public static  String WHEELS_GAME_2="i 5 0,8 0,9 0,10 1,8 1,11 2,7 2,11 3,8 3,11 4,8 4,9 4,10;";
    public static  String WHEELS_GAME_3="i 7 8,8 8,9 8,10 9,8 9,11 10,7 10,11 11,8 11,11 12,8 12,9 12,10;";
    public static  String WHEELS_GAME_4="i 7 10,0 10,1 10,4 10,5 11,0 11,2 11,3 11,4 11,6 12,0 12,1 12,4 12,5;";
    public static  String WHEELS_GAME_5="i 9 2,2 2,3 3,2 3,4 4,2 4,3;";
    public static  String WHEELS_GAME_6="i 9 2,9;";
    public static  String WHEELS_GAME_7="i 9 6,6 6,7 6,8 6,9 6,10 6,11 7,6 8,0 8,1 8,2 8,3 8,4 8,5;";
    public static  String WHEELS_GAME_8="i 9 10,9;";
    public static  String WHEELS_GAME_stone="s 0,1 0,4 0,10 2,2 2,3 2,9 2,11 3,0 3,2 3,4 3,6 4,2 4,3 4,10 6,1 6,4 6,6 6,11 8,0 8,5 8,8 8,10 10,0 10,5 10,7 10,9 10,11 11,3 12,1 12,4 12,8 12,10;";
    public static  String FACE_GAME_1 = "i 6 0,0 0,1 0,2 0,3 0,4 0,5 0,6 0,7 0,8 0,9 0,10 0,11 1,0 1,12 2,0 2,11 3,0 3,12 4,0 4,11 5,0 5,12 6,0 6,11 7,0 7,12 8,0 8,11 9,0 9,12 10,0 10,11 11,0 11,12 12,0 12,1 12,2 12,3 12,4 12,5 12,6 12,7 12,8 12,9 12,10 12,11;";
    public static  String FACE_GAME_2="i 6 2,4 2,5 2,6 2,7;";
    public static  String FACE_GAME_3="i 9 4,4 4,5 4,6 4,7;";
    public static  String FACE_GAME_4="i 9 6,5 6,6 7,5 7,7 8,5 8,6;";
    public static  String FACE_GAME_5="i 12 2,2 3,2 3,3 4,2 5,2 5,3 6,2 7,2 7,3;";
    public static  String FACE_GAME_6="i 12 2,9 3,9 3,10 4,9 5,9 5,10 6,9 7,9 7,10;";
    public static  String FACE_GAME_7="i 12 9,2 9,10 10,2 10,3 10,4 10,5 10,6 10,7 10,8 10,9;";
    public static  String FACE_GAME_stone="s 0,3 0,8 1,0 1,12 2,2 2,4 2,7 2,9 4,2 4,5 4,6 4,9 5,0 5,12 6,2 6,5 6,6 6,9 8,0 8,5 8,6 8,11 9,2 9,10 10,3 10,5 10,6 10,8 11,0 11,12 12,4 12,7;";
    public static  String SIDES_GAME_1 =  "i 4 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 2,0 2,1 2,2 2,3 3,0 3,1 3,2 3,3 4,0 4,1 4,2 4,3 5,0 5,1 5,2 5,3 6,0 6,1 6,2 6,3;";
    public static  String SIDES_GAME_2="i 20 0,5 1,5 1,6 2,5 3,5 3,6 4,5 5,5 5,6 6,5;";
    public static  String SIDES_GAME_stone="s 0,0 0,1 0,2 0,3 1,1 1,2 1,3 1,5 1,6 2,0 2,1 2,2 2,3 3,0 3,1 3,2 3,3 3,5 3,6 4,0 4,1 4,2 4,3 5,1 5,2 5,3 5,5 5,6 6,0 6,1 6,2 6,3;";
    public static  String SPACE_INVADERS_GAME_1 = "i 6 0,2 0,7 1,3 1,7 2,2 2,3 2,4 2,5 2,6 2,7 3,2 3,4 3,5 3,6 3,8 4,0 4,1 4,2 4,3 4,4 4,5 4,6 4,7 4,8 4,9 5,0 5,1 5,3 5,4 5,5 5,6 5,7 5,9 5,10 6,0 6,2 6,7 6,9 7,3 7,4 7,6 7,7;";
    public static  String SPACE_INVADERS_GAME_2="i 6 0,14 0,19 1,15 1,19 2,14 2,15 2,16 2,17 2,18 2,19 3,14 3,16 3,17 3,18 3,20 4,12 4,13 4,14 4,15 4,16 4,17 4,18 4,19 4,20 4,21 5,12 5,13 5,15 5,16 5,17 5,18 5,19 5,21 5,22 6,12 6,14 6,19 6,21 7,15 7,16 7,18 7,19;";
    public static  String SPACE_INVADERS_GAME_3="i 6 17,9 18,8 18,9 19,6 19,7 19,8 19,9 19,10 19,11 19,12 20,5 20,6 20,7 20,8 20,9 20,10 20,11 20,12 21,5 21,6 21,7 21,8 21,9 21,10 21,11 21,12 21,13 22,5 22,6 22,7 22,8 22,9 22,10 22,11 22,12;";
    public static  String SPACE_INVADERS_GAME_4="i 8 12,3 12,5 13,3 13,4 13,5 13,6 14,1 14,2 14,3 14,4 14,5 15,1 15,2 15,3 16,1 16,2;";
    public static  String SPACE_INVADERS_GAME_5="i 8 12,17 12,18 12,19 13,17 13,18 13,19 13,20 14,17 14,18 14,19 14,20 15,19 15,20 15,21 16,19 16,20;";
    public static  String SPACE_INVADERS_GAME_6="i 8 13,14 14,13 14,14 15,13 15,14 15,15 16,13 16,14;";
    public static  String SPACE_INVADERS_GAME_7="i 8 14,7 15,7 15,8 16,7;";
    public static  String SPACE_INVADERS_GAME_8="i 10 8,9 9,9 10,9 11,9;";
    public static  String SPACE_INVADERS_GAME_9="i 10 8,12 9,13 10,12 11,13;";
    public static  String SPACE_INVADERS_GAME_10="i 10 9,1 10,1 11,1 12,1;";
    public static  String SPACE_INVADERS_GAME_11="i 10 9,22 10,21 11,22 12,21;";
    public static  String SPACE_INVADERS_GAME_12="i 10 13,10 14,10 15,10;";
    public static  String SPACE_INVADERS_GAME_13="i 10 17,0 18,0 19,0 20,0;";
    public static  String SPACE_INVADERS_GAME_14="i 10 17,16 18,16 19,16 20,16;";
    public static  String SPACE_INVADERS_GAME_stone="s 0,2 0,7 0,14 0,19 3,5 3,17 6,0 6,9 6,12 6,21 7,4 7,6 7,16 7,18 11,9 11,13 12,1 12,19 12,21 13,10 15,2 15,8 15,14 15,20 17,9 18,8 18,9 20,0 20,16 21,6 21,9 21,12;";
    */

    public static final String[] DEFAULT_GAME = new String[]{"i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1;","i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8;","i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11;","i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12;","i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2;","i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5;","i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5;","i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8;"};
    public static final String[] WHEELS_GAME = new String[]{"i 5 0,1 0,2 0,3 0,4 1,1 1,5 2,0 2,5 3,0 3,6 4,0 4,5 5,1 5,5 6,1 6,2 6,3 6,4;","i 5 0,8 0,9 0,10 1,8 1,11 2,7 2,11 3,8 3,11 4,8 4,9 4,10;","i 7 8,8 8,9 8,10 9,8 9,11 10,7 10,11 11,8 11,11 12,8 12,9 12,10;","i 7 10,0 10,1 10,4 10,5 11,0 11,2 11,3 11,4 11,6 12,0 12,1 12,4 12,5;","i 9 2,2 2,3 3,2 3,4 4,2 4,3;","i 9 2,9;","i 9 6,6 6,7 6,8 6,9 6,10 6,11 7,6 8,0 8,1 8,2 8,3 8,4 8,5;","i 9 10,9;"};
    public static final String[] FACE_GAME = new String[]{"i 6 0,0 0,1 0,2 0,3 0,4 0,5 0,6 0,7 0,8 0,9 0,10 0,11 1,0 1,12 2,0 2,11 3,0 3,12 4,0 4,11 5,0 5,12 6,0 6,11 7,0 7,12 8,0 8,11 9,0 9,12 10,0 10,11 11,0 11,12 12,0 12,1 12,2 12,3 12,4 12,5 12,6 12,7 12,8 12,9 12,10 12,11;", "i 6 2,4 2,5 2,6 2,7;","i 9 4,4 4,5 4,6 4,7;","i 9 6,5 6,6 7,5 7,7 8,5 8,6;","i 12 2,2 3,2 3,3 4,2 5,2 5,3 6,2 7,2 7,3;","i 12 2,9 3,9 3,10 4,9 5,9 5,10 6,9 7,9 7,10;","i 12 9,2 9,10 10,2 10,3 10,4 10,5 10,6 10,7 10,8 10,9;"};
    public static final String[] SIDES_GAME = new String[]{"i 4 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 2,0 2,1 2,2 2,3 3,0 3,1 3,2 3,3 4,0 4,1 4,2 4,3 5,0 5,1 5,2 5,3 6,0 6,1 6,2 6,3;","i 20 0,5 1,5 1,6 2,5 3,5 3,6 4,5 5,5 5,6 6,5;"};
    public static final String[] SPACE_INVADERS_GAME = new String[]{"i 6 0,2 0,7 1,3 1,7 2,2 2,3 2,4 2,5 2,6 2,7 3,2 3,4 3,5 3,6 3,8 4,0 4,1 4,2 4,3 4,4 4,5 4,6 4,7 4,8 4,9 5,0 5,1 5,3 5,4 5,5 5,6 5,7 5,9 5,10 6,0 6,2 6,7 6,9 7,3 7,4 7,6 7,7;","i 6 0,14 0,19 1,15 1,19 2,14 2,15 2,16 2,17 2,18 2,19 3,14 3,16 3,17 3,18 3,20 4,12 4,13 4,14 4,15 4,16 4,17 4,18 4,19 4,20 4,21 5,12 5,13 5,15 5,16 5,17 5,18 5,19 5,21 5,22 6,12 6,14 6,19 6,21 7,15 7,16 7,18 7,19;","i 6 17,9 18,8 18,9 19,6 19,7 19,8 19,9 19,10 19,11 19,12 20,5 20,6 20,7 20,8 20,9 20,10 20,11 20,12 21,5 21,6 21,7 21,8 21,9 21,10 21,11 21,12 21,13 22,5 22,6 22,7 22,8 22,9 22,10 22,11 22,12;","i 8 12,3 12,5 13,3 13,4 13,5 13,6 14,1 14,2 14,3 14,4 14,5 15,1 15,2 15,3 16,1 16,2;","i 8 12,17 12,18 12,19 13,17 13,18 13,19 13,20 14,17 14,18 14,19 14,20 15,19 15,20 15,21 16,19 16,20;","i 8 13,14 14,13 14,14 15,13 15,14 15,15 16,13 16,14;","i 8 14,7 15,7 15,8 16,7;","i 10 8,9 9,9 10,9 11,9;","i 10 8,12 9,13 10,12 11,13;","i 10 9,1 10,1 11,1 12,1;","i 10 9,22 10,21 11,22 12,21;","i 10 13,10 14,10 15,10;","i 10 17,0 18,0 19,0 20,0;","i 10 17,16 18,16 19,16 20,16;"};
    public static  String DEFAULT_GAME_stone="s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11;";
    public static  String WHEELS_GAME_stone="s 0,1 0,4 0,10 2,2 2,3 2,9 2,11 3,0 3,2 3,4 3,6 4,2 4,3 4,10 6,1 6,4 6,6 6,11 8,0 8,5 8,8 8,10 10,0 10,5 10,7 10,9 10,11 11,3 12,1 12,4 12,8 12,10;";
    public static  String FACE_GAME_stone="s 0,3 0,8 1,0 1,12 2,2 2,4 2,7 2,9 4,2 4,5 4,6 4,9 5,0 5,12 6,2 6,5 6,6 6,9 8,0 8,5 8,6 8,11 9,2 9,10 10,3 10,5 10,6 10,8 11,0 11,12 12,4 12,7;";
    public static  String SIDES_GAME_stone="s 0,0 0,1 0,2 0,3 1,1 1,2 1,3 1,5 1,6 2,0 2,1 2,2 2,3 3,0 3,1 3,2 3,3 3,5 3,6 4,0 4,1 4,2 4,3 5,1 5,2 5,3 5,5 5,6 6,0 6,1 6,2 6,3;";
    public static  String SPACE_INVADERS_GAME_stone="s 0,2 0,7 0,14 0,19 3,5 3,17 6,0 6,9 6,12 6,21 7,4 7,6 7,16 7,18 11,9 11,13 12,1 12,19 12,21 13,10 15,2 15,8 15,14 15,20 17,9 18,8 18,9 20,0 20,16 21,6 21,9 21,12;";

    @Test
    void count_area() {
        int[] DEFAULT_GAME_area = new int[]{11,11,11,15,14,14,20,20};
        for (int i=0;i<DEFAULT_GAME.length;i++){
            Assertions.assertEquals(DEFAULT_GAME_area[i], Island.count_area(DEFAULT_GAME[i]),"");
        }
        int[] WHEELS_GAME_area = new int[]{18,12,12,13,6,1,13,1};
        for (int i=0;i<WHEELS_GAME.length;i++){
            Assertions.assertEquals(WHEELS_GAME_area[i], Island.count_area(WHEELS_GAME[i]),"");
        }
        int[] FACE_GAME_area= new int[]{46,4,4,6,9,9,10};
        for (int i=0;i<FACE_GAME.length;i++){
            Assertions.assertEquals(FACE_GAME_area[i], Island.count_area(FACE_GAME[i]),"");
        }
        int[] SIDES_GAME_area= new int[]{28,10};
        for (int i=0;i<SIDES_GAME.length;i++){
            Assertions.assertEquals(SIDES_GAME_area[i], Island.count_area(SIDES_GAME[i]),"");
        }
        int[] SPACE_INVADERS_GAME_area= new int[]{42,42,35,16,16,8,4,4,4,4,4,3,4,4};
        for (int i=0;i<SPACE_INVADERS_GAME.length;i++){
            Assertions.assertEquals(SPACE_INVADERS_GAME_area[i], Island.count_area(SPACE_INVADERS_GAME[i]),"");
        }

    }

    Boolean setPosition(String[] strings,String stone){
        for (int k=0;k<strings.length;k++){
            String[]DEFAULT_GAME_apart=strings[k].substring(0,strings[k].length()-1).split(" ");
            Position[] positions= Island.set_positions(strings[k],stone);
            int count1=0;

            for (int j=0;j<positions.length;j++){
                String pos=positions[j].row+","+positions[j].col;
                for (int i=2;i<DEFAULT_GAME_apart.length;i++){
                    if (pos.equals(DEFAULT_GAME_apart[i])){
                        count1++;
                    }
                }
                if (positions[j].type==1){
                    Assertions.assertTrue(stone.contains(pos),"");
                }
            }
            Assertions.assertTrue(count1==positions.length,"");
        }
        return true;
    }

    @Test
    void set_positions() {
        Assertions.assertTrue(setPosition(DEFAULT_GAME,DEFAULT_GAME_stone),"");
        Assertions.assertTrue(setPosition(WHEELS_GAME,WHEELS_GAME_stone),"");
        Assertions.assertTrue(setPosition(FACE_GAME,FACE_GAME_stone),"");
        Assertions.assertTrue(setPosition(SIDES_GAME,SIDES_GAME_stone),"");
        Assertions.assertTrue(setPosition(SPACE_INVADERS_GAME,SPACE_INVADERS_GAME_stone),"");
    }
}
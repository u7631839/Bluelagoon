package comp1110.ass2;

import comp1110.ass2.D2B.Player;
import comp1110.ass2.D2B.Util;
import comp1110.ass2.gui.Game;
import comp1110.ass2.gui.Island;

import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BlueLagoon {
    // The Game Strings for five maps have been created for you.
    // They have only been encoded for two players. However, they are
    // easily extendable to more by adding additional player statements.
    public static final String DEFAULT_GAME = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C B W P S; p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";
    public static final String WHEELS_GAME = "a 13 2; c 0 E; i 5 0,1 0,2 0,3 0,4 1,1 1,5 2,0 2,5 3,0 3,6 4,0 4,5 5,1 5,5 6,1 6,2 6,3 6,4; i 5 0,8 0,9 0,10 1,8 1,11 2,7 2,11 3,8 3,11 4,8 4,9 4,10; i 7 8,8 8,9 8,10 9,8 9,11 10,7 10,11 11,8 11,11 12,8 12,9 12,10; i 7 10,0 10,1 10,4 10,5 11,0 11,2 11,3 11,4 11,6 12,0 12,1 12,4 12,5; i 9 2,2 2,3 3,2 3,4 4,2 4,3; i 9 2,9; i 9 6,6 6,7 6,8 6,9 6,10 6,11 7,6 8,0 8,1 8,2 8,3 8,4 8,5; i 9 10,9; s 0,1 0,4 0,10 2,2 2,3 2,9 2,11 3,0 3,2 3,4 3,6 4,2 4,3 4,10 6,1 6,4 6,6 6,11 8,0 8,5 8,8 8,10 10,0 10,5 10,7 10,9 10,11 11,3 12,1 12,4 12,8 12,10; r C B W P S; p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";
    public static final String FACE_GAME = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 0,4 0,5 0,6 0,7 0,8 0,9 0,10 0,11 1,0 1,12 2,0 2,11 3,0 3,12 4,0 4,11 5,0 5,12 6,0 6,11 7,0 7,12 8,0 8,11 9,0 9,12 10,0 10,11 11,0 11,12 12,0 12,1 12,2 12,3 12,4 12,5 12,6 12,7 12,8 12,9 12,10 12,11; i 6 2,4 2,5 2,6 2,7; i 9 4,4 4,5 4,6 4,7; i 9 6,5 6,6 7,5 7,7 8,5 8,6; i 12 2,2 3,2 3,3 4,2 5,2 5,3 6,2 7,2 7,3; i 12 2,9 3,9 3,10 4,9 5,9 5,10 6,9 7,9 7,10; i 12 9,2 9,10 10,2 10,3 10,4 10,5 10,6 10,7 10,8 10,9; s 0,3 0,8 1,0 1,12 2,2 2,4 2,7 2,9 4,2 4,5 4,6 4,9 5,0 5,12 6,2 6,5 6,6 6,9 8,0 8,5 8,6 8,11 9,2 9,10 10,3 10,5 10,6 10,8 11,0 11,12 12,4 12,7; r C B W P S; p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";
    public static final String SIDES_GAME =  "a 7 2; c 0 E; i 4 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 2,0 2,1 2,2 2,3 3,0 3,1 3,2 3,3 4,0 4,1 4,2 4,3 5,0 5,1 5,2 5,3 6,0 6,1 6,2 6,3; i 20 0,5 1,5 1,6 2,5 3,5 3,6 4,5 5,5 5,6 6,5; s 0,0 0,1 0,2 0,3 1,1 1,2 1,3 1,5 1,6 2,0 2,1 2,2 2,3 3,0 3,1 3,2 3,3 3,5 3,6 4,0 4,1 4,2 4,3 5,1 5,2 5,3 5,5 5,6 6,0 6,1 6,2 6,3; r C B W P S; p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";
    public static final String SPACE_INVADERS_GAME = "a 23 2; c 0 E; i 6 0,2 0,7 1,3 1,7 2,2 2,3 2,4 2,5 2,6 2,7 3,2 3,4 3,5 3,6 3,8 4,0 4,1 4,2 4,3 4,4 4,5 4,6 4,7 4,8 4,9 5,0 5,1 5,3 5,4 5,5 5,6 5,7 5,9 5,10 6,0 6,2 6,7 6,9 7,3 7,4 7,6 7,7; i 6 0,14 0,19 1,15 1,19 2,14 2,15 2,16 2,17 2,18 2,19 3,14 3,16 3,17 3,18 3,20 4,12 4,13 4,14 4,15 4,16 4,17 4,18 4,19 4,20 4,21 5,12 5,13 5,15 5,16 5,17 5,18 5,19 5,21 5,22 6,12 6,14 6,19 6,21 7,15 7,16 7,18 7,19; i 6 17,9 18,8 18,9 19,6 19,7 19,8 19,9 19,10 19,11 19,12 20,5 20,6 20,7 20,8 20,9 20,10 20,11 20,12 21,5 21,6 21,7 21,8 21,9 21,10 21,11 21,12 21,13 22,5 22,6 22,7 22,8 22,9 22,10 22,11 22,12; i 8 12,3 12,5 13,3 13,4 13,5 13,6 14,1 14,2 14,3 14,4 14,5 15,1 15,2 15,3 16,1 16,2; i 8 12,17 12,18 12,19 13,17 13,18 13,19 13,20 14,17 14,18 14,19 14,20 15,19 15,20 15,21 16,19 16,20; i 8 13,14 14,13 14,14 15,13 15,14 15,15 16,13 16,14; i 8 14,7 15,7 15,8 16,7; i 10 8,9 9,9 10,9 11,9; i 10 8,12 9,13 10,12 11,13; i 10 9,1 10,1 11,1 12,1; i 10 9,22 10,21 11,22 12,21; i 10 13,10 14,10 15,10; i 10 17,0 18,0 19,0 20,0; i 10 17,16 18,16 19,16 20,16; s 0,2 0,7 0,14 0,19 3,5 3,17 6,0 6,9 6,12 6,21 7,4 7,6 7,16 7,18 11,9 11,13 12,1 12,19 12,21 13,10 15,2 15,8 15,14 15,20 17,9 18,8 18,9 20,0 20,16 21,6 21,9 21,12; r C B W P S; p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";


    /**
     * Check if the string encoding of the game state is well-formed.
     * Note that this does not mean checking that the state is valid
     * (represents a state that players could reach in game play),
     * only that the string representation is syntactically well-formed.
     * <p>
     * A description of the state string will be included in README.md
     * in an update of the project after D2B is complete.
     *
     * @param stateString a string representing a game state
     * @return true if stateString is well-formed and false otherwise
     */
    public static boolean isStateStringWellFormed(String stateString){
        String pattern = "[a]\\s[0-9]+\\s[0-9]+\\;\\s[c]\\s[0-9]+\\s[E|S]\\;(\\s[i]\\s\\d+(\\s\\d+\\,\\d+)+\\;)+\\s[s](\\s\\d+\\,\\d+)+\\;\\s[r]\\s[C](\\s\\d+\\,\\d+){0,}\\s[B](\\s\\d+\\,\\d+){0,}\\s[W](\\s\\d+\\,\\d+){0,}\\s[P](\\s\\d+\\,\\d+){0,}\\s[S](\\s\\d+\\,\\d+){0,}\\;(\\s[p]\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s[S](\\s\\d+\\,\\d+){0,}\\s[T](\\s\\d+\\,\\d+){0,}\\;)+";
        if (stateString.matches(pattern)){
            return true;
        }else {
            return false;
        }
        // FIXME Task 3}}}
        }

    /**
     * Check if the string encoding of the move is syntactically well-formed.
     * <p>
     * A description of the move string will be included in README.md
     * in an update of the project after D2B is complete.
     *
     * @param moveString a string representing a player's move
     * @return true if moveString is well-formed and false otherwise
     */
    public static boolean isMoveStringWellFormed(String moveString){
        String pattern = "[ST]\\s\\d{1,2},\\d{1,2}";
        if (!moveString.matches(pattern)) {
            return false;
        }

        return true;// FIXME Task 4
    }

    /**
     * Given a state string which is yet to have resources distributed amongst the stone circles,
     * randomly distribute the resources and statuettes between all the stone circles.
     * <p>
     * There will always be exactly 32 stone circles.
     * <p>
     * The resources and statuettes to be distributed are:
     * - 6 coconuts
     * - 6 bamboo
     * - 6 water
     * - 6 precious stones
     * - 8 statuettes
     * <p>
     * The distribution must be random.
     *
     * @param stateString a string representing a game state without resources distributed
     * @return a string of the game state with resources randomly distributed
     */
    public static String distributeResources(String stateString){
        String pos_string = stateString.substring(stateString.indexOf("s"), stateString.indexOf(";",stateString.indexOf("s"))+2);
        int total = 32;
        int coconuts_num = 6;
        int bamboo_num = 6;
        int water_num = 6;
        int precious_stone_num = 6;
        int statuettes_num = 8;
        Random random=new Random();
        StringBuilder insert_builder = new StringBuilder(stateString);
        StringBuilder stateString_builder= new StringBuilder(pos_string);
        while (total>0){
            int first_space=stateString_builder.indexOf(" ");
            int second_space=stateString_builder.indexOf(" ",first_space+1);
//            int index_s=insert_builder.indexOf("S", )
            String sub=stateString_builder.substring(first_space+1,second_space);
            if (second_space>stateString_builder.indexOf(";")){
                sub=stateString_builder.substring(first_space+1, stateString_builder.indexOf(";"));
            }
            stateString_builder.delete(first_space,second_space);
            int r_type=random.nextInt(total)+1;

            if (r_type<=coconuts_num){
                insert_builder.insert(insert_builder.indexOf("B"), ""+sub+" ");
                coconuts_num--;
            } else if (r_type<=coconuts_num+bamboo_num) {
                insert_builder.insert(insert_builder.indexOf("W"), ""+sub+" ");
                bamboo_num--;
            }else if (r_type<=coconuts_num+bamboo_num+water_num) {
                insert_builder.insert(insert_builder.indexOf("P"), ""+sub+" ");
                water_num--;
            }else if (r_type<=coconuts_num+bamboo_num+water_num+precious_stone_num) {
                insert_builder.insert(insert_builder.indexOf("S", 20), ""+sub+" ");
                precious_stone_num--;
            }else if (r_type<=coconuts_num+bamboo_num+water_num+precious_stone_num+statuettes_num) {
                insert_builder.insert(insert_builder.indexOf(";", insert_builder.indexOf("S", 20)), " "+sub+"");
                statuettes_num--;
            }
            total--;
        }
        return insert_builder.toString(); // FIXME Task 6
    }

    /**
     * Given a state string and a move string, determine if the move is
     * valid for the current player.
     * <p>
     * For a move to be valid, the player must have enough pieces left to
     * play the move. The following conditions for each phase must also
     * be held.
     * <p>
     * In the Exploration Phase, the move must either be:
     * - A settler placed on any unoccupied sea space
     * - A settler or a village placed on any unoccupied land space
     *   adjacent to one of the player's pieces.
     * <p>
     * In the Settlement Phase, the move must be:
     * - Only a settler placed on an unoccupied space adjacent to
     *   one of the player's pieces.
     * Importantly, players can now only play on the sea if it is
     * adjacent to a piece they already own.
     *
     * @param stateString a string representing a game state
     * @param moveString a string representing the current player's move
     * @return true if the current player can make the move and false otherwise
     */
    public static boolean isMoveValid(String stateString, String moveString) {
        Set<String> occupied = Util.getOccupiedCoordinates(stateString);
        Set<String> currentOccupied = Util.getCurrentOccupiedCoordinates(stateString);
        Set<String> land = Util.getLandCoordinates(stateString);
        Set<String> sea = Util.getSeaCoordinates(stateString, land);
        Set<String> map = new HashSet<>();
        map.addAll(land);
        map.addAll(sea);

        int numPlayers = Util.getNumPlayers(stateString);
        String currentPlayerId0 = Util.getCurrentPlayerId(stateString);
        int currentPlayerId = Integer.valueOf(currentPlayerId0).intValue();
        int remainingSettlers = Player.getRemainingSettlers(stateString, currentPlayerId, numPlayers);
        int remainingVillages = Player.getRemainingVillages(stateString, currentPlayerId);

        String[] moveParts = moveString.split(" ");
        if (moveParts.length < 2) {
            return false;
        }

        String pieceType = moveParts[0];
        String targetCoordinate = moveParts[1];
        if(pieceType.equals("S")){
            remainingSettlers = remainingSettlers - 1;
        }
        else if(pieceType.equals("T")){
            remainingVillages = remainingVillages - 1;
        }
        if (remainingSettlers < 0 || remainingVillages < 0) {
            return false;
        }
        if (occupied.contains(targetCoordinate) || !map.contains(targetCoordinate)) {
            return false;
        }

        if(currentOccupied.isEmpty()){
            if(!sea.contains(targetCoordinate) ||!pieceType.equals("S")){
                return false;
            }
        }

        if (sea.contains(targetCoordinate)) {
            if (!pieceType.equals("S")) {
                return false;
            }
        }

        String[] coordinateParts = targetCoordinate.split(",");
        int a = Integer.parseInt(coordinateParts[0]);
        int b = Integer.parseInt(coordinateParts[1]);

        String[] phaseParts = stateString.split(";")[1].split(" ");

        String phase=phaseParts[3];
        if (phase.equals("E")) {
             if (land.contains(targetCoordinate)) {
                List<String> neighbors = Util.getNeighbors(a, b, currentOccupied);
                if (neighbors.isEmpty()) {
                    return false;
                }
            }
        } else if (phase.equals("S")) {
            if (!pieceType.equals("S")) {
                return false;
            }
            List<String> neighbors = Util.getNeighbors(a, b, currentOccupied);
            if (neighbors.isEmpty()) {
                return false;
            }
        }

        return true;
    }


    /**
     * Given a state string, generate a set containing all move strings playable
     * by the current player.
     * <p>
     * A move is playable if it is valid.
     *
     * @param stateString a string representing a game state
     * @return a set of strings representing all moves the current player can play
     */
    // Written by Xuejie Dong
    public static Set<String> generateAllValidMoves(String stateString){
        String[] states = (stateString+" ").split("; ");
        int numplayers = states[0].charAt(states[0].length()-1)-48;
        String[] player = new String[numplayers];
        int currentplayerID = states[1].charAt(2)-48;
        String phase = states[1].substring(4);
        int numcoordinate = 0;
        Map<Integer,String> coordinate_S = new HashMap<>();
        Set<String> allplayer = new HashSet<>();
        String pattern = "[p]\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s\\d+\\s[S](\\s\\d+\\,\\d+){0,}\\s[T](\\s\\d+\\,\\d+){0,}";
        for (int i=0;i<states.length;i++){
            if (states[i].matches(pattern)){
                if (states[i].charAt(2)-48==currentplayerID){
                    player[currentplayerID]=states[i];
                }
            }
        }
        String pattern_coordinate = "\\d+\\,\\d+";
        //String pattern_S ="[S](\\s\\d+\\,\\d+){0,}";
        //String pattern_T ="[T](\\s\\d+\\,\\d+){0,}";
        String pattern_stone = "[s](\\s\\d+\\,\\d+)+";
        Set<String> allstone = new HashSet<>();
        for (int i=0;i<states.length;i++){
            if (states[i].matches(pattern_stone)){
                String[] stone_element = states[i].split(" ");
                for (int j=0;j<stone_element.length;j++){
                    if (stone_element[j].matches(pattern_coordinate)){
                        allstone.add(stone_element[j]);
                    }
                }
            }
        }
        Set<String> villages = new HashSet<>();
        if (player[currentplayerID].substring(player[currentplayerID].indexOf("T")).length()>1) {
            String string_T = player[currentplayerID].substring(player[currentplayerID].indexOf("T") + 2);
            String[] coordinateT = string_T.split(" ");
            for (int i=0;i<coordinateT.length;i++){
                villages.add(coordinateT[i]);
            }
        }
        String[] player_element = player[currentplayerID].split(" ");
        for (int i=0;i<player_element.length;i++){
           // if(player_element[i].matches(pattern_S)){
            if (states[1].substring(4).equals("E")){
                if (player_element[i].matches(pattern_coordinate)){
                    coordinate_S.put(numcoordinate,player_element[i]);
                    numcoordinate++;
                }
            }
            else {
                if (player_element[i].matches(pattern_coordinate)){
                   for (String j:villages){
                       if(!allstone.contains(j)){
                           coordinate_S.put(numcoordinate,j);
                           numcoordinate++ ;
                       }
                       if (!player_element[i].equals(j)){
                           coordinate_S.put(numcoordinate,player_element[i]);
                           numcoordinate++;
                       }
                   }
                }
            }
        }

            //}
        //S+T
        //String string_T = player[currentplayerID].substring(player[currentplayerID].indexOf("T")+2);
        //String[] coordinateT = string_T.split(" ");
        //int numvillage = coordinateT.length;
        for (int i=0;i<states.length;i++){
            if (states[i].matches(pattern)){
                String []allplayer_element = states[i].split(" ");
                for (int j=0;j<allplayer_element.length;j++){
                    if (allplayer_element[j].matches(pattern_coordinate)){
                        allplayer.add("S "+allplayer_element[j]);
                    }
                }
            }
        } //allplayer coordinate_S
        String pattern_island = "[i]\\s\\d+(\\s\\d+\\,\\d+){0,}";
        Set<String> allisland = new HashSet<>();
        for (int i=0;i<states.length;i++){
            if (states[i].matches(pattern_island)){
                String [] allisland_element = states[i].split(" ");
                for (int j=0;j<allisland_element.length;j++){
                    if (allisland_element[j].matches(pattern_coordinate)){
                        allisland.add("S "+allisland_element[j]);
                    }
                }
            }
        }

        Set<String> sea = new HashSet<>();
        for(int row=0;row<=12;row++){
            if (row%2==0){
                for (int col=0;col<=11;col++){
                    sea.add("S "+row+","+col);
                }
            }
            else {
                for (int col=0;col<=12;col++){
                    sea.add("S "+row+","+col);
                }
            }
        }
        sea.removeAll(allisland);
        Set<String> settler_coordinate = new HashSet<>();
        for (int i = 0; i< numcoordinate; i++){
            int row=Integer.parseInt(coordinate_S.get(i).substring(0, coordinate_S.get(i).indexOf(",")));
            int col=Integer.parseInt(coordinate_S.get(i).substring(coordinate_S.get(i).indexOf(",")+1));
            sea.remove("S "+row+","+col);
            if (row%2==1){
                if (row-1>=0){
                    if (col<=11){
                        if (!allplayer.contains("S "+(row-1)+","+col)){
                        settler_coordinate.add("S "+(row-1)+","+col);
                        }
                    }
                 if (col-1>=0){
                    if (!allplayer.contains("S "+(row-1)+","+(col-1))){
                            settler_coordinate.add("S "+(row-1)+","+(col-1));
                    }
                }
            }
             if (col-1>=0){
                if (!allplayer.contains("S "+row+","+(col-1))){
                        settler_coordinate.add("S "+row+","+(col-1));
                }
                if (row+1<=12){
                    if (!allplayer.contains("S "+(row+1)+","+(col-1))){
                            settler_coordinate.add("S "+(row+1)+","+(col-1));
                    }
                }
            }
           if (row+1<=12){
               if (col<=11){
                   if (!allplayer.contains("S "+(row+1)+","+col)){
                        settler_coordinate.add("S "+(row+1)+","+col);
                }
               }
           }
           if (col+1<=12){
               if (!allplayer.contains("S "+row+","+(col+1))){
                   settler_coordinate.add("S "+row+","+(col+1));
                    }
                }
            }
            else {
                if (row-1>=0){
                    if (!allplayer.contains("S "+(row-1)+","+col)){
                        settler_coordinate.add("S "+(row-1)+","+col);
                    }
                    if (col+1<=12){
                        if (!allplayer.contains("S "+(row-1)+","+(col+1))){
                            settler_coordinate.add("S "+(row-1)+","+(col+1));
                        }
                    }
                }
                 if (col-1>=0){
                    if (!allplayer.contains("S "+row+","+(col-1))){
                        settler_coordinate.add("S "+row+","+(col-1));
                    }
                }
                 if (row+1<=12){
                    if (!allplayer.contains("S "+(row+1)+","+col)){
                        settler_coordinate.add("S "+(row+1)+","+col);
                    }
                    if (col+1<=12){
                        if (!allplayer.contains("S "+(row+1)+","+(col+1))){
                            settler_coordinate.add("S "+(row+1)+","+(col+1));
                        }
                    }
                }
                 if (col+1<=11){
                    if (!allplayer.contains("S "+row+","+(col+1))){
                        settler_coordinate.add("S "+row+","+(col+1));
                    }
                }
            }

        }
        //String string_T = player[currentplayerID].substring(player[currentplayerID].indexOf("T")+2);
        //String[] coordinateT = string_T.split(" ");
        //int numvillage = coordinateT.length;
        //sea.removeAll(allplayer);
        //settler_coordinate.removeAll(allplayer);
        for (String i:allplayer){
            if (settler_coordinate.contains(i)){
                settler_coordinate.remove(i);
            }
        }

      // for (String i:settler_coordinate){
            //if (!sea.contains(i)){
               // settler_coordinate.remove(i);
           // }
      // }
       //sea.removeAll(allisland);
            //}
       // }
        Set<String> allvillage = new HashSet<>();

        if (player[currentplayerID].substring(player[currentplayerID].indexOf("T")).length()>1) {
            String string_T = player[currentplayerID].substring(player[currentplayerID].indexOf("T") + 2);
            String[] coordinateT = string_T.split(" ");
            //for (int i=0;i<coordinateT.length;i++){
                //if (allstone.contains(coordinateT[i])){
                //
                //}
           // }
            int numvillage = coordinateT.length;
            if (numvillage<5) {

                for (String i : settler_coordinate) {
                    if (allisland.contains(i)) {
                        String a = i.substring(2);
                        allvillage.add("T " + a);

                    }
                }
            }
        }
        else {
            for (String i : settler_coordinate) {
                if (allisland.contains(i)) {
                    String a = i.substring(2);
                    allvillage.add("T " + a);

                }
            }
        }
        //sea.removeAll(allplayer);
        for (String i:allplayer){
            if (sea.contains(i)){
                sea.remove(i);
            }
        }
        if(states[1].substring(4).equals("E")){
            settler_coordinate.addAll(sea);
            settler_coordinate.addAll(allvillage);
        }
        /*else {
        if (player[currentplayerID].substring(player[currentplayerID].indexOf("S")).length()>1) {
            String string_S = player[currentplayerID].substring(player[currentplayerID].indexOf("S") + 2,player[currentplayerID].indexOf("T")-1);
            String[] coordinateS = string_S.split(" ");
            int numS = coordinateS.length;
            if (numplayers==2){
                if (numS==30){
                    settler_coordinate.clear();
                }
            }else if (numplayers==3){
                if (numS==25){
                    settler_coordinate.clear();
                }
            }else if (numplayers==4){
                if (numS==20){
                    settler_coordinate.clear();
                }
            }

        }}*/
        else {
            if (player[currentplayerID].substring(player[currentplayerID].indexOf("S"),player[currentplayerID].indexOf("T")).length()>2) {
                String string_S = player[currentplayerID].substring(player[currentplayerID].indexOf("S") + 2,player[currentplayerID].indexOf("T")-1);
                String[] coordinateS = string_S.split(" ");
                int numS = coordinateS.length;
                //System.out.println("1");
                //System.out.println(numS);
                if (numplayers==2){
                    if (numS==30){
                        settler_coordinate.clear();
                        //System.out.println("1");
                    }
                }else if (numplayers==3){
                    if (numS==25){
                        settler_coordinate.clear();
                       // System.out.println("1");
                    }
                }else if (numplayers==4){
                    if (numS==20){
                        settler_coordinate.clear();
                    }
                }

            }
        }
        Set<String>settler=new HashSet<>();
        for (String i:settler_coordinate){
            if (i.charAt(0)=='S'){
                settler.add(i);
            }
        }
        if (player[currentplayerID].substring(player[currentplayerID].indexOf("S"),player[currentplayerID].indexOf("T")).length()>2) {
            String string_S = player[currentplayerID].substring(player[currentplayerID].indexOf("S") + 2, player[currentplayerID].indexOf("T") - 1);
            String[] coordinateS = string_S.split(" ");
            int numS = coordinateS.length;
            if (numplayers == 2) {
                if (numS == 30) {
                    settler_coordinate.removeAll(settler);
                }
            } else if (numplayers == 3) {
                if (numS == 25) {
                    settler_coordinate.removeAll(settler);
                }
            } else if (numplayers == 4) {
                if (numS == 20) {
                    settler_coordinate.removeAll(settler);
                }
            }
        }


         return settler_coordinate; // FIXME Task 8
    }

    /**
     * Given a state string, determine whether it represents an end of phase state.
     * <p>
     * A phase is over when either of the following conditions hold:
     * - All resources (not including statuettes) have been collected.
     * - No player has any remaining valid moves.
     *
     * @param stateString a string representing a game state
     * @return true if the state is at the end of either phase and false otherwise
     */
    public static boolean isPhaseOver(String stateString){
        String resource_string= Resources.getResourcesString(stateString);
        StringBuilder stringBuilder=new StringBuilder(stateString);
        int bool=0;
        int player_num = Util.getNumPlayers(stateString);
//        if (Util.getPhase(stateString).equals("E")){
//            for (int i=0;i<player_num;i++){
//                stringBuilder.replace(stringBuilder.indexOf("c")+2, stringBuilder.indexOf("c")+3, String.valueOf(i));
//                Set<String> valid_moves= generateAllValidMoves(stringBuilder.toString());
//                int player_element= Player.getRemainingSettlers(stringBuilder.toString(), i, player_num)+Player.getRemainingVillages(stringBuilder.toString(),i);
//                if (player_element==0 || valid_moves.size()==0){
//                    bool++;
//                }
//            }
//            if (bool==player_num){
//                return true;
//            }
//        }else if (Util.getPhase(stateString).equals("S")){
//
//        }
        for (int i=0;i<player_num;i++){
            stringBuilder.replace(stringBuilder.indexOf("c")+2, stringBuilder.indexOf("c")+3, String.valueOf(i));
            Set<String> valid_moves= generateAllValidMoves(stringBuilder.toString());
            int player_element= Player.getRemainingSettlers(stringBuilder.toString(), i, player_num)+Player.getRemainingVillages(stringBuilder.toString(),i);
            if (valid_moves.size()==0 || player_element==0){
                bool++;
            }
        }
        if (bool==player_num){
            return true;
        }


        return Resources.overPhase(resource_string);// FIXME Task 9
    }

    /**
     * Given a state string and a move string, place the piece associated with the
     * move on the board. Ensure the player collects any corresponding resource or
     * statuettes.
     * <p>
     * Do not handle switching to the next player here.
     *
     * @param stateString a string representing a game state
     * @param moveString a string representing the current player's move
     * @return a new state string achieved by placing the move on the board
     */
    public static String placePiece(String stateString, String moveString){
        String playerId = Util.getCurrentPlayerId(stateString);
        String playerStatement = Util.getPlayerStatement(stateString, playerId);

        String moveType = Util.getMoveType(moveString);
        String moveCoordinate = Util.getTargetCoodinate(moveString);

        String unclaimedresource =Util.getUnclaimedResource(stateString);
        Map<String, List<String>> resourceCoordinates = Util.getResourceCoordinates(unclaimedresource);
        List<String> coconutCoordinates = resourceCoordinates.get("C");
        List<String> bambooCoordinates = resourceCoordinates.get("B");
        List<String> waterCoordinates = resourceCoordinates.get("W");
        List<String> preciousStoneCoordinates = resourceCoordinates.get("P");
        List<String> statuetteCoordinates = resourceCoordinates.get("S");
        String temp1 =unclaimedresource;
        String[] parts = playerStatement.split(" ");

        StringBuilder modifiedCoordinates = new StringBuilder();
        unclaimedresource = unclaimedresource.substring(0, unclaimedresource.length() - 1);
        String[] unclaimed = unclaimedresource.split(" ");
        for (String coordinate : unclaimed) {
            if (!coordinate.equals(moveCoordinate)) {
                modifiedCoordinates.append(coordinate).append(" ");
            }
        }
        modifiedCoordinates.deleteCharAt(modifiedCoordinates.length() - 1);
        unclaimedresource = modifiedCoordinates.toString() + ";";
        stateString = stateString.replace(temp1, unclaimedresource);


        if(coconutCoordinates!=null && coconutCoordinates.contains(moveCoordinate)){
            String temp2 = playerStatement;
            int coconut = Integer.parseInt(parts[3]);
            coconut=coconut+1;
            parts[3] = String.valueOf(coconut);
            String modifiedPlayerStatement = String.join(" ", parts);
            stateString = stateString.replace(temp2,modifiedPlayerStatement);
            playerStatement=modifiedPlayerStatement;
        }else if(bambooCoordinates!=null && bambooCoordinates.contains(moveCoordinate)) {
            String temp2 = playerStatement;
            int bamboo = Integer.parseInt(parts[4]);
            bamboo=bamboo+1;
            parts[4] = String.valueOf(bamboo);
            String modifiedPlayerStatement = String.join(" ", parts);
            stateString = stateString.replace(temp2,modifiedPlayerStatement);
            playerStatement=modifiedPlayerStatement;
        }else if(waterCoordinates!=null && waterCoordinates.contains(moveCoordinate)) {
            String temp2 = playerStatement;
            int water = Integer.parseInt(parts[5]);
            water++;
            parts[5] = String.valueOf(water);
            String modifiedPlayerStatement = String.join(" ", parts);
            stateString =stateString.replace(temp2, modifiedPlayerStatement);
            playerStatement=modifiedPlayerStatement;
        }else if(preciousStoneCoordinates!=null && preciousStoneCoordinates.contains(moveCoordinate)) {
            String temp2 = playerStatement;
            int preciousStone = Integer.parseInt(parts[6]);
            preciousStone++;
            parts[6] = String.valueOf(preciousStone);
            String modifiedPlayerStatement = String.join(" ", parts);
            stateString =stateString.replace(temp2, modifiedPlayerStatement);
            playerStatement=modifiedPlayerStatement;
        }else if(statuetteCoordinates!=null && statuetteCoordinates.contains(moveCoordinate)) {
            String temp2 = playerStatement;
            int statuette = Integer.parseInt(parts[7]);
            statuette++;
            parts[7] = String.valueOf(statuette);
            String modifiedPlayerStatement = String.join(" ", parts);
            stateString =stateString.replace(temp2, modifiedPlayerStatement);
            playerStatement=modifiedPlayerStatement;
        }


        StringBuilder updatedStatement = new StringBuilder(playerStatement);

        if (moveType.equals("S")) {
            int startIndex = updatedStatement.indexOf("S") + 2;
            int endIndex = updatedStatement.indexOf("T") - 1;

            if (endIndex < startIndex) {

                String newPlayerStatement = playerStatement.replace("S",  "S "+moveCoordinate);
                return stateString.replace(Util.getPlayerStatement(stateString, playerId), newPlayerStatement);
            }
            String currentCoordinates = updatedStatement.substring(startIndex, endIndex);

            String updatedCoordinates = insertCoordinate(moveCoordinate, currentCoordinates);

            updatedStatement.replace(startIndex, endIndex, updatedCoordinates);
        } else if (moveType.equals("T")) {
            int startIndex = updatedStatement.indexOf("T") + 2;
            int endIndex = updatedStatement.indexOf(";", startIndex);
            if (endIndex < startIndex) {
                String newPlayerStatement = playerStatement.replace(";", " " + moveCoordinate + ";");
                return stateString.replace(Util.getPlayerStatement(stateString, playerId), newPlayerStatement);
            }
            String currentCoordinates = updatedStatement.substring(startIndex, endIndex);

            String updatedCoordinates = insertCoordinate(moveCoordinate, currentCoordinates);

            updatedStatement.replace(startIndex, endIndex, updatedCoordinates);
        }
        return stateString.replace(Util.getPlayerStatement(stateString, playerId), updatedStatement.toString());

    }


    private static String insertCoordinate(String newCoordinate, String currentCoordinates) {
        StringBuilder sb = new StringBuilder(currentCoordinates);
        String[] coordinateArray = currentCoordinates.split(" ");
        boolean inserted = false;

        for (int i = 0; i < coordinateArray.length; i++) {
            String coordinate = coordinateArray[i];
            if (compareCoordinates(newCoordinate, coordinate) < 0) {
                sb.insert(sb.indexOf(coordinate), newCoordinate + " ");
                inserted = true;
                break;
            }
        }

        if (!inserted) {
            sb.append(" ").append(newCoordinate);
        }

        return sb.toString().trim();
    }



    private static int compareCoordinates(String coordinate1, String coordinate2) {
        String[] parts1 = coordinate1.split(",");
        String[] parts2 = coordinate2.split(",");
        int row1 = Integer.parseInt(parts1[0].trim());
        int col1 = Integer.parseInt(parts1[1].trim());
        int row2 = Integer.parseInt(parts2[0].trim());
        int col2 = Integer.parseInt(parts2[1].trim());

        if (row1 != row2) {
            return Integer.compare(row1, row2);
        } else {
            return Integer.compare(col1, col2);
        }
    }

// FIXME Task 10

    /**
     * Given a state string, calculate the "Islands" portion of the score for
     * each player as if it were the end of a phase. The return value is an
     * integer array sorted by player number containing the calculated score
     * for the respective player.
     * <p>
     * The "Islands" portion is calculated for each player as follows:
     * - If the player has pieces on 8 or more islands, they score 20 points.
     * - If the player has pieces on 7 islands, they score 10 points.
     * - No points are scored otherwise.
     *
     * @param stateString a string representing a game state
     * @return an integer array containing the calculated "Islands" portion of
     * the score for each player
     */
    public static int[] calculateTotalIslandsScore(String stateString){
        int playerNum= Integer.valueOf(Util.getNumPlayers(stateString));
        int[] totalIslandsScore = new int[playerNum];
        int count=0;
        Map<Integer,Set<String>>islandcoordinates= tools.islandCoordinates(stateString);
        for (int i=0;i<playerNum;i++){
            Set<String> playerOccupied = Util.getplayerOccupiedCoordinates(stateString,String.valueOf(i));
            for (int j=0;j<tools.island_num(stateString);j++){
                if (playerOccupied.size()==0){
                    totalIslandsScore[i]=0;
                }
                else if (!Collections.disjoint(playerOccupied,islandcoordinates.get(j))){
                    count++;
                }
            }
            if (count>=8){
                totalIslandsScore[i]=20;
            }else if (count==7){
                totalIslandsScore[i]=10;
            }else {
                totalIslandsScore[i]=0;
            }
            count=0;

        }

        return totalIslandsScore; // FIXME Task 11
    }

    /**
     * Given a state string, calculate the "Links" portion of the score for
     * each player as if it were the end of a phase. The return value is an
     * integer array sorted by player number containing the calculated score
     * for the respective player.
     * <p>
     * Players earn points for their chain of pieces that links the most
     * islands. For each island linked by this chain, they score 5 points.
     * <p>
     * Note the chain needn't be a single path. For instance, if the chain
     * splits into three or more sections, all of those sections are counted
     * towards the total.
     *
     * @param stateString a string representing a game state
     * @return an integer array containing the calculated "Links" portion of
     * the score for each player
     */
    public static int[] calculateIslandLinksScore(String stateString){
        int playerNum = Util.getNumPlayers(stateString);
        int[] score = new int[playerNum];
        int islandnum= tools.island_num(stateString);
        Set<String>land=Util.getLandCoordinates(stateString);
        Map<Integer,Set<String>>islandCoordinates=tools.islandCoordinates(stateString);
        Set<String> intersection = new HashSet<>();
        for (int i = 0; i < playerNum; i++) {
            Set<String> playerCoordinates = Util.getplayerOccupiedCoordinates(stateString, String.valueOf(i));
            if (playerCoordinates.isEmpty()) {
                score[i] = 0;
            } else {
                int[] counts = new int[playerCoordinates.size()];
                int num = 0;

                for (String j : playerCoordinates) {
                    int count = 0;
                    List<String> result = new ArrayList<>();
                    int a = Integer.valueOf(j.substring(0, j.indexOf(",")));
                    int b = Integer.valueOf(j.substring(j.indexOf(",") + 1));
                    List<String> neighbors = Util.getNeighbors(a, b, playerCoordinates);
                    result.addAll(links(neighbors, playerCoordinates, new ArrayList<>()));


                    for (int k = 0; k < islandnum; k++) {
                        intersection.clear();
                        intersection.addAll(islandCoordinates.get(k));
                        intersection.retainAll(result);
                        //System.out.println(intersection);
                        if (!intersection.isEmpty()) {
                            count++;
                        }

                    }

                    //System.out.println(count);
                    counts[num] = count;
                    num = num + 1;
                }

                int max = counts[0];
                for (int j = 0; j < counts.length; j++) {
                    if (counts[j] > max) {
                        max = counts[j];
                    }
                }
                //System.out.println(max);
                if (max==0){
                    //
                    intersection.clear();
                    intersection.addAll(playerCoordinates);
                    intersection.retainAll(land);
                    if (!intersection.isEmpty()){
                        score[i]=5;
                    }

                }else score[i] = max * 5;
                //
            }
        }


        return score;
    }


    public static List<String> links(List<String> link, Set<String> playerCoordinates, List<String> result) {
        for (String i : link) {
            if (!result.contains(i)) {
                result.add(i);
                List<String> neighbors = Util.getNeighbors(
                        Integer.valueOf(i.substring(0, i.indexOf(","))),
                        Integer.valueOf(i.substring(i.indexOf(",") + 1)),
                        playerCoordinates
                );
                links(neighbors, playerCoordinates, result);
            }
        }
        return result;
    }

    /**
     * Given a state string, calculate the "Majorities" portion of the score for
     * each player as if it were the end of a phase. The return value is an
     * integer array sorted by player number containing the calculated score
     * for the respective player.
     * <p>
     * The "Majorities" portion is calculated for each island as follows:
     * - The player with the most pieces on the island scores the number
     *   of points that island is worth.
     * - In the event of a tie for pieces on an island, those points are
     *   divided evenly between those players rounding down. For example,
     *   if two players tied for an island worth 7 points, they would
     *   receive 3 points each.
     * - No points are awarded for islands without any pieces.
     *
     * @param stateString a string representing a game state
     * @return an integer array containing the calculated "Majorities" portion
     * of the score for each player
     */
    public static int[] calculateIslandMajoritiesScore(String stateString){
        int playerNum= Integer.valueOf(Util.getNumPlayers(stateString));
        int[] islandMajoritiesScore = new int[playerNum];
        int[] num=new int[playerNum];
        int playerId_getScore=0;
        int count=0;
        Set<String> intersection = new HashSet<>();
        Map<Integer, Set<String>> islandCoordinatesMap = tools.islandCoordinates(stateString);
        int islandnum = islandCoordinatesMap.size();
        int[] islandscore = tools.islandscore(stateString);
        for (int j=0;j<islandnum;j++){
            for (int i=0;i<playerNum;i++){
                Set<String> playercoordinates = Util.getplayerOccupiedCoordinates(stateString,String.valueOf(i));
                intersection.clear();
                intersection.addAll(islandCoordinatesMap.get(j));
                intersection.retainAll(playercoordinates);
                num[i]=intersection.size();
            }
            int max=num[0];


            for (int i=1;i<num.length;i++){
                if (num[i]>max){
                    max=num[i];
                    if (num[i]!=0){
                        playerId_getScore=i;
                    }
                }
            }
            if (max!=0){
                for (int k=0;k<num.length;k++){
                    if (num[k]==max){
                        count++;
                    }
                }
                if (count==1){
                    islandMajoritiesScore[playerId_getScore]=islandMajoritiesScore[playerId_getScore]+islandscore[j];
                }
                else {
                    for (int k=0;k<num.length;k++){
                        if (num[k]==max){
                            islandMajoritiesScore[k]=islandMajoritiesScore[k]+(islandscore[j]/count);
                        }
                    }
                }
            }
            count=0;
            playerId_getScore=0;
        }

         return islandMajoritiesScore; // FIXME Task 11
    }

    /**
     * Given a state string, calculate the "Resources" and "Statuettes" portions
     * of the score for each player as if it were the end of a phase. The return
     * value is an integer array sorted by player number containing the calculated
     * score for the respective player.
     * <p>
     * Note that statuettes are not resources.
     * <p>
     * In the below "matching" means a set of the same resources.
     * <p>
     * The "Resources" portion is calculated for each player as follows:
     * - For each set of 4+ matching resources, 20 points are scored.
     * - For each set of exactly 3 matching resources, 10 points are scored.
     * - For each set of exactly 2 matching resources, 5 points are scored.
     * - If they have all four resource types, 10 points are scored.
     * <p>
     * The "Statuettes" portion is calculated for each player as follows:
     * - A player is awarded 4 points per statuette in their possession.
     *
     * @param stateString a string representing a game state
     * @return an integer array containing the calculated "Resources" and "Statuettes"
     * portions of the score for each player
     */
    public static int[] calculateResourcesAndStatuettesScore(String stateString){
        String resourcesString= Resources.getResourcesString(stateString);
        int playerNum = Integer.valueOf(Util.getNumPlayers(stateString));
        int[] resourcesAndStatuettesScore = new int[playerNum];
        for (int i=0;i<playerNum;i++){
            String playerString = Resources.getPlayerString(stateString,i);
            HashMap<Resources.resources,Integer> resourceplayer=Resources.getplayerResource(playerString);
            resourcesAndStatuettesScore[i]=Resources.addresourcePoints(resourceplayer);

        }

         return resourcesAndStatuettesScore; // FIXME Task 11
    }

    /**
     * Given a state string, calculate the scores for each player as if it were
     * the end of a phase. The return value is an integer array sorted by player
     * number containing the calculated score for the respective player.
     * <p>
     * It is recommended to use the other scoring functions to assist with this
     * task.
     *
     * @param stateString a string representing a game state
     * @return an integer array containing the calculated scores for each player
     */
    public static int[] calculateScores(String stateString){
        int playernum = Util.getNumPlayers(stateString);
        int[] score= new int[playernum];
        int[] totalIslandsScore = calculateTotalIslandsScore(stateString);
        int[] islandLinksScore = calculateIslandLinksScore(stateString);
        int[] islandMajoritiesScore = calculateIslandMajoritiesScore(stateString);
        int[] resourcesAndStatuettesScore = calculateResourcesAndStatuettesScore(stateString);
        for (int i=0;i<score.length;i++){
            score[i]=totalIslandsScore[i]+islandLinksScore[i]+islandMajoritiesScore[i]+resourcesAndStatuettesScore[i];
        }

         return score; // FIXME Task 11
    }

    /**
     * Given a state string representing an end of phase state, return a new state
     * achieved by following the end of phase rules. Do not move to the next player
     * here.
     * <p>
     * In the Exploration Phase, this means:
     * - The score is tallied for each player.
     * - All pieces are removed from the board excluding villages not on stone circles.
     * - All resources and statuettes remaining on the board are removed. All resources are then
     *   randomly redistributed between the stone circles.
     * <p>
     * In the Settlement Phase, this means:
     * - Only the score is tallied and added on for each player.
     *
     * @param stateString a string representing a game state at the end of a phase
     * @return a string representing the new state achieved by following the end of phase rules
     */
    public static String endPhase(String stateString){
        int[] scores=calculateScores(stateString);
        Set<String> stone=Util.getStoneCoordinates(stateString);



        int playernum=Util.getNumPlayers(stateString);


        String phase =Util.getPhase(stateString);
        if(phase.equals("E")){
            String[] playerstrings = new String[playernum];
            for(int i=0;i<playernum;i++){
                String playerid=String.valueOf(i);
                playerstrings[i]=Util.getPlayerStatement(stateString,playerid);
                int sIndex = playerstrings[i].indexOf("S");
                int tIndex = playerstrings[i].indexOf("T");
                String part1 = playerstrings[i].substring(0, sIndex).trim();
                String part2 = playerstrings[i].substring(tIndex).trim();
                String[] parts = part1.split(" ");
                parts[2]=String.valueOf(Integer.parseInt(parts[2])+scores[i]);
                parts[3]="0";
                parts[4]="0";
                parts[5]="0";
                parts[6]="0";
                parts[7]="0 ";
                part1=String.join(" ",parts);
                String[] Tcoordinate=part2.substring(0,part2.length()-1).split(" ");
                StringBuilder modifiedCoordinates = new StringBuilder();
                for (String coordinate : Tcoordinate) {
                    if (!stone.contains(coordinate)) {
                        modifiedCoordinates.append(coordinate).append(" ");
                    }
                }

                playerstrings[i]=part1+"S "+modifiedCoordinates;
                //playerstrings[i].replaceAll("p \\d+ \\d+", "p " + i + " " + scores[i]);
                playerstrings[i]=playerstrings[i].substring(0,playerstrings[i].length()-1);
            }
            String playerstatement=String.join("; ",playerstrings);

            String[] part = stateString.substring(0,stateString.indexOf("r")).split(";");
            String[] phases= part[1].split(" ");
            phases[3]="S";
            part[1]=String.join(" ",phases);
            stateString= String.join(";",part)+"r C B W P S; p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";
            stateString=distributeResources(stateString);


            return stateString.substring(0,stateString.indexOf("p"))+playerstatement+";";
        }else if(phase.equals("S")){
            String[] playerstrings = new String[playernum];
            for(int i=0;i<playernum;i++){
                String playerid=String.valueOf(i);
                playerstrings[i]=Util.getPlayerStatement(stateString,playerid);
                int sIndex = playerstrings[i].indexOf("S");
                String part1 = playerstrings[i].substring(0, sIndex).trim();
                String part2 = playerstrings[i].substring(sIndex).trim();
                String[] parts = part1.split(" ");
                parts[2]=String.valueOf(Integer.parseInt(parts[2])+scores[i]);
                part1=String.join(" ",parts);


                playerstrings[i]=part1+part2;
                //playerstrings[i].replaceAll("p \\d+ \\d+", "p " + i + " " + scores[i]);
                playerstrings[i]=playerstrings[i].substring(0,playerstrings[i].length()-1);
            }
            String playerstatement=String.join("; ",playerstrings);
            return stateString.substring(0,stateString.indexOf("p"))+playerstatement+";";
        }else{
            return "";
        }
      // FIXME Task 12
    }

    /**
     * Given a state string and a move string, apply the move to the board.
     * <p>
     * If the move ends the phase, apply the end of phase rules.
     * <p>
     * Advance current player to the next player in turn order that has a valid
     * move they can make.
     *
     * @param stateString a string representing a game state
     * @param moveString a string representing the current player's move
     * @return a string representing the new state after the move is applied to the board
     */
    public static String applyMove(String stateString, String moveString) {
        int current_p = Integer.parseInt(Util.getCurrentPlayerId(stateString));
        if (!isMoveValid(stateString, moveString)){
            return stateString;
        }
        stateString = placePiece(stateString, moveString);
        String changedString=tools.change_currentplayer(stateString, current_p);
        String phase = Util.getPhase(stateString);
        if (phase.equals("E")) {
            if (isPhaseOver(stateString)||generateAllValidMoves(changedString).isEmpty()) {
                Game.end=1;
                stateString = endPhase(stateString);
                if(!generateAllValidMoves(stateString).isEmpty() && !generateAllValidMoves(tools.change_currentplayer(stateString, current_p)).isEmpty()) {
                    stateString = tools.change_currentplayer(stateString, current_p);
                }
            } else {
                stateString = tools.change_currentplayer(stateString, current_p);
            }
        } else{
            if (isPhaseOver(stateString)) {
                Game.end=2;
                stateString = endPhase(stateString);
            } else if(!generateAllValidMoves(changedString).isEmpty()){
                stateString = tools.change_currentplayer(stateString, current_p);
            }else{
                stateString = tools.change_currentplayer(stateString, current_p);
                String a=stateString.substring(0,stateString.indexOf(";")+1);
                int player_num=Integer.parseInt(a.substring(a.lastIndexOf(" ")+1,a.indexOf(";")));
                if (current_p<player_num-1){
                    current_p++;
                }else if (current_p==player_num-1){
                    current_p=0;
                }
                stateString=tools.change_currentplayer(stateString, current_p);
            }
        }
        return stateString;
    }




        /* if (!isMoveValid(stateString, moveString)){
            return stateString;
        }
        int current_p = Integer.parseInt(getCurrentPlayerId(stateString));
        stateString=placePiece(stateString,moveString);
        String phase =Util.getPhase(stateString);
        if (isPhaseOver(stateString) && phase.equals("E")){
            //stateString=tools.change_currentplayer(stateString, current_p);
            stateString=endPhase(stateString);
            stateString=tools.change_currentplayer(stateString, current_p);
            return stateString;
        }else if (isPhaseOver(stateString) && phase.equals("S")){
            return endPhase(stateString);
        }
        String new_stateString=tools.change_currentplayer(stateString, current_p);
        return new_stateString;
//        int current_p = Integer.parseInt(getCurrentPlayerId(stateString));
//        int current_p_at = tools.current_p_at(current_p, stateString);
//        String move_pos=moveString.substring(2);
//        String p_string=stateString.substring(current_p_at, stateString.indexOf(";", current_p_at)+1);
//        if (isMoveValid(stateString, moveString)){
//            if (moveString.contains("S")){
//                String S_string=p_string.substring(p_string.indexOf("S"), p_string.indexOf("T"))+";";
//                String new_S_string=tools.ordered_insert_position(S_string, move_pos);
//                S_string=S_string.substring(0,S_string.length()-1);
//                new_S_string=new_S_string.substring(0, new_S_string.length()-1);
//                if (new_S_string.charAt(new_S_string.length()-1)!=' '){
//                    new_S_string=new_S_string+" ";
//                }
//                String new_p_string=p_string.replace(S_string, new_S_string);
//                stateString=stateString.replace(p_string, new_p_string);
//            }
//            else {
//                int T_at= p_string.indexOf("T");
//                String T_string=p_string.substring(T_at,p_string.indexOf(";",T_at)+1);
//                String new_T_string=tools.ordered_insert_position(T_string, move_pos);
//                String new_p_string=p_string.replace(T_string,new_T_string);
//                stateString=stateString.replace(p_string, new_p_string);
//            }
//        }
//        if (isPhaseOver(stateString)){
//            stateString=endPhase(stateString);
//        }
//        String sub_r=stateString.substring(stateString.indexOf("r"),stateString.indexOf("p"));
//        if (sub_r.contains(" "+move_pos+" " )){
//            String new_sub_r=sub_r.replaceFirst(" "+move_pos+" ", " ");
//            stateString=stateString.replaceFirst(sub_r,new_sub_r);
//        } else if (sub_r.contains(" "+move_pos+";")) {
//            String new_sub_r=sub_r.replaceFirst(" "+move_pos+" ", ";");
//            stateString=stateString.replaceFirst(sub_r,new_sub_r);
//        }
//        String new_state=tools.change_currentplayer(stateString, current_p);
//        return new_state; // FIXME Task 13
    }

    /**
     * Given a state string, returns a valid move generated by your AI.
     * <p>
     * As a hint, generateAllValidMoves() may prove a useful starting point,
     * maybe if you could use some form of heuristic to see which of these
     * moves is best?
     * <p>
     * Your AI should perform better than randomly generating moves,
     * see how good you can make it!
     *
     * @param stateString a string representing a game state
     * @return a move string generated by an AI
     */
    public static String generateAIMove(String stateString){
         Set<String> vailmove=generateAllValidMoves(stateString);
         int playerId=Integer.parseInt(Util.getCurrentPlayerId(stateString));
         String movecoordinates="";
         int max=-1;
         Set<String>resource = Resources.resourcesCoordinates(stateString);
         Set<String>sea=Util.sea(stateString);
         Set<String>move_nosea = new HashSet<>();
         move_nosea.addAll(vailmove);
         move_nosea.removeAll(sea);


        Set<String> playercoordinates = Util.getCurrentOccupiedCoordinates(stateString);
         if (!move_nosea.isEmpty()||playercoordinates.size()>5){
         for (String i:move_nosea){

             String coordinates=i.substring(2);
             if (resource.contains(coordinates)){
                 movecoordinates=i;
                 break;
             }

             int landscore=0;
             String ifmove = placePiece(stateString,i);
             landscore=calculateTotalIslandsScore(ifmove)[playerId];
             //int score=calculateIslandMajoritiesScore(stateString)[playerId]+calculateResourcesAndStatuettesScore(stateString)[playerId]+calculateTotalIslandsScore(stateString)[playerId];
             if (landscore>=10){
                 movecoordinates=i;
                 break;
             }

             int majorscore= calculateIslandMajoritiesScore(ifmove)[playerId];
             if (majorscore>max){
                 max=majorscore;
                 movecoordinates=i;
             }


         }
            /* Random random = new Random();
            int randomIndex = random.nextInt(move_nosea.size());
             String[] strings = move_nosea.toArray(move_nosea.toArray(new String[0]));
             movecoordinates=strings[randomIndex];
        */ }
         else {
             Random random = new Random();
             int randomIndex = random.nextInt(vailmove.size());
             String[] strings = vailmove.toArray(vailmove.toArray(new String[0]));
             movecoordinates=strings[randomIndex];
         }




        return movecoordinates; // FIXME Task 16
    }

    public static String AI_notIntelligent(String stateString){
        Set<String> vailmove=generateAllValidMoves(stateString);
        Random random = new Random();
        int randomIndex = random.nextInt(vailmove.size());
        String[] strings = vailmove.toArray(vailmove.toArray(new String[0]));
        return strings[randomIndex];
    }


}

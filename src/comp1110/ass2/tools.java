package comp1110.ass2;

import comp1110.ass2.D2B.Island;
import comp1110.ass2.D2B.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class tools {
    public static String generate_state(Resources resources, Player[] players, HashMap<Integer, Island> island_map){
        return "";
    }

    public static int island_num(String stateString){
        int count=0;
        while (stateString.contains("i")){
            count++;
            stateString=stateString.substring(stateString.indexOf("i")+1);
        }
        return count;
    }

    public static Map<Integer, comp1110.ass2.gui.Island> create_island_map(String stateString){
        int island_num=island_num(stateString);
        String stone_string=stateString.substring(stateString.indexOf("s"), stateString.indexOf(";", stateString.indexOf("s"))+1);
        Map<Integer, comp1110.ass2.gui.Island> island_map=new HashMap<Integer, comp1110.ass2.gui.Island>();
        int single_island_begin=stateString.indexOf("i");
        for (int i=0;i<island_num;i++){
            int single_island_end=stateString.indexOf(";",single_island_begin);
            String single_island=stateString.substring(single_island_begin, single_island_end+1);
            comp1110.ass2.gui.Island island=new comp1110.ass2.gui.Island(i, single_island, stone_string);
            island_map.put(i, island);
            single_island_begin=stateString.indexOf("i", single_island_end);
        }
        return island_map;
    }

    public static Set<String>singleIsland(String stateString,int k){
        String[]stateApart=(stateString+" ").split("; ");
        String[]singleisland=stateApart[2+k].split(" ");
        Set<String>islandcoordinates = new HashSet<>();
        for (int i=2;i<singleisland.length;i++){
            islandcoordinates.add(singleisland[i]);
        }
        return islandcoordinates;
    }
    public static Map<Integer, Set<String>>islandCoordinates(String stateString){
        Map<Integer,Set<String>>Allislandcoordinates = new HashMap<>();
        for (int i=0;i<island_num(stateString);i++){
            Allislandcoordinates.put(i,singleIsland(stateString,i));
        }
        return Allislandcoordinates;
    }
    public static int[] islandscore(String stateString){
        String[]stateApart=(stateString+" ").split("; ");
        int[] islandscore = new int[tools.island_num(stateString)];
        for (int i=0;i<stateApart.length;i++){
            if (stateApart[i].charAt(0)=='i'){
                String[] islandApart = stateApart[i].split(" ");
                islandscore[i-2]=Integer.valueOf(islandApart[1]);
            }

        }
        return islandscore;
    }

    public static int current_p_at(int current_p, String statestring){
        int current_p_at=0;
        for (int i=0;i<=current_p;i++){
            current_p_at=statestring.indexOf("p", current_p_at+1);
        }
        return current_p_at;
    }

    // order_piece= X x,x x,x x,x ... x,x;
    public static String ordered_insert_position(String order_piece, String insert_position){
        StringBuilder piece_builder=new StringBuilder(order_piece);
        int col=Integer.parseInt(insert_position.substring(0, insert_position.indexOf(",")));
        int row=Integer.parseInt(insert_position.substring(insert_position.indexOf(",")+1));
        int begin_space = piece_builder.indexOf(" ");
        int end_space = piece_builder.indexOf(" ", begin_space+1);
        if (piece_builder.charAt(1)==';'){
            piece_builder.insert(1," ");
        }
        if (piece_builder.charAt(2)==';'){
            return piece_builder.insert(2,insert_position).toString();
        }else if (end_space==-1){
            end_space=piece_builder.indexOf(";");
            String pos_sub=piece_builder.substring(begin_space+1, end_space);
            if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))<col){
                return piece_builder.insert(piece_builder.indexOf(";"), " "+insert_position).toString();
            } else if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))==col &&
                    Integer.parseInt(pos_sub.substring(pos_sub.indexOf(",")+1))<row) {
                return piece_builder.insert(piece_builder.indexOf(";"), " "+insert_position).toString();
            }
            return piece_builder.insert(2,insert_position+" ").toString();
        }

        while (end_space!=-1){
            String pos_sub=piece_builder.substring(begin_space+1, end_space);
            if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))<col){
                begin_space=piece_builder.indexOf(" ", end_space);
                end_space=piece_builder.indexOf(" ",begin_space+1);
                continue;
            } else if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))>col) {
                return piece_builder.insert(begin_space+1, insert_position+" ").toString();
            } else if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))==col &&
                    Integer.parseInt(pos_sub.substring(pos_sub.indexOf(",")+1))>row) {
                return piece_builder.insert(begin_space+1, insert_position+" ").toString();
            }
            begin_space=piece_builder.indexOf(" ", end_space);
            end_space=piece_builder.indexOf(" ",begin_space+1);
        }
        if (end_space==-1){
            begin_space = piece_builder.indexOf(" ");
            end_space=piece_builder.indexOf(";");
            String pos_sub=piece_builder.substring(begin_space+1, end_space);
            if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))<col){
                return piece_builder.insert(piece_builder.indexOf(";"), insert_position).toString();
            } else if (Integer.parseInt(pos_sub.substring(0, pos_sub.indexOf(",")))==col &&
                    Integer.parseInt(pos_sub.substring(pos_sub.indexOf(",")+1))<row) {
                return piece_builder.insert(piece_builder.indexOf(";"), insert_position).toString();
            }
//            return piece_builder.insert(begin_space,insert_position+" ").toString();
        }
        return piece_builder.insert(begin_space," "+insert_position).toString();
    }
    public static String change_currentplayer(String statestring, int current_p){
        String a=statestring.substring(0,statestring.indexOf(";")+1);
        int player_num=Integer.parseInt(a.substring(a.lastIndexOf(" ")+1,a.indexOf(";")));
        if (current_p<player_num-1){
            current_p++;
        }else if (current_p==player_num-1){
            current_p=0;
        }
        statestring=statestring.substring(0,statestring.indexOf("c")+2)+current_p+statestring.substring(statestring.indexOf("c")+3);
        return statestring;
    }
    public static int get_winner(String statestring){
        int p_at=statestring.indexOf("p");
        int winner = 0;
        int max_score=0;
        while (p_at!=-1){
            int first_space=statestring.indexOf(" ", p_at+2);
            int end_space=statestring.indexOf(" ",first_space+1);
            int c_score=Integer.parseInt(statestring.substring(first_space+1,end_space));
            if (c_score>max_score){
                max_score=c_score;
                winner=Integer.parseInt(String.valueOf(statestring.charAt(p_at+2)));
            }
            p_at=statestring.indexOf("p", p_at+1);
        }
        return winner;
    }
}
package comp1110.ass2.gui;

import java.util.Arrays;

public class Island {
       int num;
       int score;
       Position[] positions;

    public Island(int num, String single_string, String stone_string){
        this.num=num;
        this.score=Integer.parseInt(String.valueOf(single_string.charAt(single_string.indexOf("i")+2)));
        this.positions=set_positions(single_string, stone_string);
    }

    public static int count_area(String single_string){
        int count=0;
        String count_string=single_string;
        while (count_string.contains(",")){
            count++;
            count_string=count_string.substring(count_string.indexOf(",")+1);
        }
        return count;
    }

    //Writen by Shiyu Pan
    public static Position[] set_positions(String single_string, String stone_string){
        int count=count_area(single_string);
        Position[] positions=new Position[count];
        int begin=single_string.indexOf(" ",single_string.indexOf(" ")+1);
        for (int i=0;i<count;i++){
            int end=single_string.indexOf(" ",begin+1);
            if (end==-1){
                end=single_string.indexOf(";");
            }

            String sub_string=single_string.substring(begin+1, end);
            int type=0;
            if (stone_string.contains(" "+sub_string+" ")){
                type=1;
            }else if (stone_string.contains(" "+sub_string+";")){
                type=1;
            }
            int row=Integer.parseInt(sub_string.substring(0,sub_string.indexOf(",")));
            int col=Integer.parseInt(sub_string.substring(sub_string.indexOf(",")+1));
            Position position=new Position(row,col,type);
            positions[i]=position;
            begin=single_string.indexOf(" ",begin+1);
        }
        return positions;
    }
    public int getScore(){
        return this.score;
    }


}

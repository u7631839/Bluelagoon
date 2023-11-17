package comp1110.ass2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Resources {
    public enum resources{
        Coconut,
        Bamboo,
        Water,
        Precious,
        Statiette;
    }
    static HashMap<resources,Integer> kindAndnum;
    static String ResourcesString;
    static String PlayerString;




    public static String getResourcesString(String stateString){
        String[] stateApart = (stateString+" ").split("; ");
        for (int i=0;i<stateApart.length;i++){
            if (stateApart[i].charAt(0)=='r'){
                ResourcesString=stateApart[i];
            }
        }
        return ResourcesString;
    }
    public static String getPlayerString(String stateString ,int playerID){
        String[] stateApart = (stateString+" ").split("; ");
        for (int i=0;i<stateApart.length;i++){
            if (stateApart[i].charAt(0)=='p'){
                int player = stateApart[i].charAt(2)-48;
                if (player==playerID){
                    PlayerString=stateApart[i];
                }
            }
        }
        return PlayerString;
    }

    public static HashMap<resources,Integer> getplayerResource(String PlayerString) {
        String[] playerApart = PlayerString.split(" ");
        HashMap<resources,Integer> kindAndnum = new HashMap<>();
        int Coconutnum = Integer.parseInt(playerApart[3]);
        int Bamboonum = Integer.parseInt(playerApart[4]);
        int Waternum = Integer.parseInt(playerApart[5]);
        int PreciousStonenum = Integer.parseInt(playerApart[6]);
        int Statuettenum = Integer.parseInt(playerApart[7]);
        kindAndnum.put(resources.Precious,PreciousStonenum);
        kindAndnum.put(resources.Coconut,Coconutnum);
        kindAndnum.put(resources.Bamboo,Bamboonum);
        kindAndnum.put(resources.Water,Waternum);
        kindAndnum.put(resources.Statiette,Statuettenum);
        return kindAndnum;
    }

    public static boolean addPoints(HashMap<resources,Integer> kindAndnum){
        return kindAndnum.get(resources.Water)!=0&&kindAndnum.get(resources.Bamboo)!=0&&kindAndnum.get(resources.Precious)!=0&&kindAndnum.get(resources.Coconut)!=0;
    }

    public static int addresourcePoints(HashMap<resources,Integer> kindAndnum){
        int point=0;
        for (resources i:kindAndnum.keySet()){
            if (i.equals(resources.Statiette)){
                point=point+4*kindAndnum.get(i);
            }else {
                if (kindAndnum.get(i)==2){
                    point=point+5;
                } else if (kindAndnum.get(i)==3) {
                    point=point+10;
                } else if (kindAndnum.get(i)>=4) {
                    point=point+20;
                }
            }
        }
        if (addPoints(kindAndnum)){
            point=point+10;
        }
        return point;
    }

    public static boolean overPhase(String ResourcesString){
        return ResourcesString.substring(ResourcesString.indexOf("C"),ResourcesString.indexOf("S")).length()<=9;
    }//return true,change the phase or over game
    //return false,continue；

    public static Set<String> resourcesCoordinates(String stateString){
        Set<String> resource = new HashSet<>();
        String resourcestring=getResourcesString(stateString);
        String c= resourcestring.substring(resourcestring.indexOf("C")+1,resourcestring.indexOf("B"));
        String b= resourcestring.substring(resourcestring.indexOf("B")+1,resourcestring.indexOf("W"));
        String w=resourcestring.substring(resourcestring.indexOf("W")+1,resourcestring.indexOf("P"));
        String p=resourcestring.substring(resourcestring.indexOf("P")+1,resourcestring.indexOf("S"));
        String s=resourcestring.substring(resourcestring.indexOf("S")+1);
        String r=c+b+w+p+s;
        String[]coor = r.split(" ");
        for (String i:coor){
            resource.add(i);
        }
        return resource;
    }



    public static void main(String[] args) {
        String stateString="a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1;" +
                " i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; " +
                "i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11;" +
                " i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12;" +
                " i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 " +
                "11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 " +
                "4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; " +
                "i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 " +
                "11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 " +
                "6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11;" +
                " r C 1,1 B W P S; p 0 0 2 1 1 1 1 S T; p 1 0 0 0 0 0 0 S T;";
        String a = Resources.getResourcesString(stateString);
        String b = Resources.getPlayerString(stateString,0);
        //System.out.println(b);
        HashMap<resources,Integer>c=Resources.getplayerResource(b);
       Set<String>n=new HashSet<>();
       n=resourcesCoordinates(stateString);
        System.out.println(n);


 }

}

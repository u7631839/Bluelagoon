package comp1110.ass2.D2B;

public class Pieces {
    int type;
    public void setSettler(){}// put the settler on the board and return position
    public void setVillage(){}// put the village on the board and return position
    public void removeSettler(){}//remove the settler
    public void removeVillage(){}// remove the village
    boolean isPlaced(){
        return true;
    }//judging whether it can be placed.
    boolean hasResource(){
        return true;
    }  //whether there are resource
    boolean hasStatuettes(){
        return true;
    } //whether there are statuettes
    public void collectResource(){} //collect the resource
    public void collectStatuettes(){}//collect the resource
}

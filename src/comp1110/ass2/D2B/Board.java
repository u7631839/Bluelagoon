package comp1110.ass2.D2B;

public class Board {
    Tile island;//The plate where the island is located in the game. There are eight islands in the map.
    Tile sea; //The plate where the sea is located in the game
    Tile stone_circle;//The plate where the stone_circle is located in the game
    int islandID;//includes 6\8\10 points
    usless_Resource[] resource;//position about where the resource is placed
    usless_Resource[] statuettes;//position about where the statuettes is placed
    boolean isOccupied(){
        return true;
    } //whether the plate is occupied

}

package comp1110.ass2.D2B;

public class Tile {
    private int type; // 1 for land, 2 for sea, 3 for stone circle
    usless_Resource resource;
    private boolean isOccupied; // whether the tile is occupied
    //private ArrayList<Resource> resources; // resources available on the tile
    private int[] coordinate; // coordinate of the tile on the game board

    // Get the type of the tile
    public int getType(){return 1;}

    // Set the type of the tile
    //public void setType(int type)

    // Check if the tile is occupied
   public boolean isOccupied(){return false;}

    // Set whether the tile is occupied
    public void setIsOccupied(boolean isOccupied){}

    // Get the resources available on the tile
    public usless_Resource getResources(){return resource;}

    // Set the resources available on the tile
    public void setResources(){}

    // Get the coordinate of the tile on the game board
    public int getCoordinate(){return 1;}

    // Set the coordinate of the tile on the game board
    public void setCoordinate(int[] coordinate){};
}

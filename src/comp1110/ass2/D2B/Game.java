package comp1110.ass2.D2B;

import java.util.ArrayList;

public class Game {
    // The width and height of the window
    private static final int WINDOW_WIDTH=2000;
    private static final int WINDOW_HEIGHT=2000;

    // The distance between two neighbouring spots on the apple
    private static final double SPOT_DISTANCE_Y = 75;
    private static final double SPOT_DISTANCE_X = 80;


    // The margins used for all visual assets
    private static final int MARGIN_X=20;
    private static final int MARGIN_Y=20;

    // The start of the map in the y-direction
    private static final double START_Y=10;

    // The start of the map in the x-direction
    private static final double START_X=10;

    Board board = new Board();
    public void make_board(){};
    //import all the players
    private ArrayList<Player> players;
    private ArrayList<usless_Resource> resources;
//choose the number of plays by GUI and then create the players
    public void make_players(int number){};
    private int current_player;
    public void Play_turn(){};

//create basic button for the game
    public void make_control(){};

    //display the suitable image of resource

    public void make_resource(){}

//set location of all the elements of the game
    public void set_location(){}

//define all the elements of the game
    public void new_game(){};
    public void start(){};
    public void restart(){};
    public void reset(){};
    public void set_position(){};  //this one is different from remove i think, although all of them

    public void Display_player_points(){};
    public void get_history(){};
}

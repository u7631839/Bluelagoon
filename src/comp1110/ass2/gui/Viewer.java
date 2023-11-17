package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField stateTextField;


    /**
     * Given a state string, draw a representation of the state
     * on the screen.
     * <p>
     * This may prove useful for debugging complex states.
     *
     * @param stateString a string representing a game state
     */


    public static int island_num(String stateString){
        int count=0;
        while (stateString.contains("i")){
            count++;
            stateString=stateString.substring(stateString.indexOf("i")+1);
        }
        return count;
    }

    public static Map<Integer, Island> create_island_map(String stateString){
        int island_num=island_num(stateString);
        String stone_string=stateString.substring(stateString.indexOf("s"), stateString.indexOf(";", stateString.indexOf("s"))+1);
        Map<Integer, Island> island_map=new HashMap<Integer, Island>();
//        System.out.println(island_num);
        int single_island_begin=stateString.indexOf("i");
        for (int i=0;i<island_num;i++){
            int single_island_end=stateString.indexOf(";",single_island_begin);
            String single_island=stateString.substring(single_island_begin, single_island_end+1);
            Island island=new Island(i, single_island, stone_string);
            island_map.put(i, island);
            single_island_begin=stateString.indexOf("i", single_island_end);
        }
        return island_map;
    }


    void displayState(String stateString) {
        if (root.getChildren().size()>1){
            root.getChildren().remove(1);
        }
        Map<Integer, Island> island_map=create_island_map(stateString);
        Group tile_group=new Group();
        if (root.getChildren().contains(tile_group)){
            root.getChildren().remove(tile_group);
        }
        double initial_x=400;
        double side=30;
        int col=0;
        double x=0;
        double initial_y=100;
        double y=initial_y;

        for (int row=0;row<13;row++){
            if ((row) % 2 ==0){    //row is y
                x=initial_x;
                col=12;
            }else {
                x=initial_x - side * 1.73 / 2;
                col=13;
            }
            for (int i=0;i<col;i++){  //col is x

                String pos=row+","+i;

                if (!stateString.contains(" "+pos+" ") && !stateString.contains(" "+pos+";")){
                    Tile tile=new Tile(0, 0, side);
                    URL url = getClass().getResource("sea_pic.png");
                    Image image = new Image(url.toString());
                    tile.setFill(new ImagePattern(image));
                    HBox hBox= new HBox();
                    hBox.getChildren().add(tile);
                    hBox.setLayoutX(x);
                    hBox.setLayoutY(y);
                    tile_group.getChildren().add(hBox);
                }
                x=x+side*1.73;
            }

            y=y+side*3/2;
        }


        for (int i=0;i<island_map.size();i++){
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Color randomColor = Color.rgb(r, g, b);

            Text score_text=new Text("island"+island_map.get(i).num+" Score:"+String.valueOf(island_map.get(i).score));
            score_text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            score_text.setFill(Color.WHITE);
            double text_x=0;
            double text_y=0;

            String c_string=stateString.substring(stateString.indexOf("C"), stateString.indexOf("B",stateString.indexOf("C"))+1);
            String b_string=stateString.substring(stateString.indexOf("B"), stateString.indexOf("W",stateString.indexOf("B"))+1);
            String w_string=stateString.substring(stateString.indexOf("W"), stateString.indexOf("P",stateString.indexOf("W"))+1);
            String p_string=stateString.substring(stateString.indexOf("P"), stateString.indexOf("S",stateString.indexOf("P"))+1);
            String s_string=stateString.substring(stateString.indexOf("S"), stateString.indexOf(";",stateString.indexOf("S"))+1);
            for (int j=0;j<island_map.get(i).positions.length;j++){

                if ((island_map.get(i).positions[j].row) % 2==0){
                    x=initial_x+(island_map.get(i).positions[j].col)*1.73*side;
                    text_x=x-50;

                }else {
                    x=initial_x - side * 1.73 / 2 +(island_map.get(i).positions[j].col)*1.73*side;
                    text_x=x-50;
                }
                y = initial_y+island_map.get(i).positions[j].row*side*3/2;
                text_y=y;
                Tile tile=new Tile(0, 0, side);
                HBox hBox= new HBox();
                if (island_map.get(i).positions[j].type==0){
                    URL url = getClass().getResource("island_pic_2.png");
                    Image image = new Image(url.toString());
                    tile.setFill(new ImagePattern(image));
                    tile.setStrokeWidth(2);

                    tile.setStroke(randomColor);


                    hBox.getChildren().add(tile);
                } else if (island_map.get(i).positions[j].type==1){

                    URL url = getClass().getResource("stone_pic.png");
                    if (c_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+" ")
                            ||c_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+";")){
                        url = getClass().getResource("c_pic.png");
                    } else if (b_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+" ")
                            ||b_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+";")) {
                        url = getClass().getResource("b_pic.png");
                    } else if (w_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+" ")
                            ||w_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+";")) {
                        url = getClass().getResource("w_pic.png");
                    } else if (p_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+" ")
                            ||p_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+";")) {
                        url = getClass().getResource("p_pic.png");
                    } else if (s_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+" ")
                            ||s_string.contains(" "+island_map.get(i).positions[j].row+","+island_map.get(i).positions[j].col+";")) {
                        url = getClass().getResource("s_pic.png");
                    }


                    Image image = new Image(url.toString());
                    tile.setFill(new ImagePattern(image));

                    tile.setStrokeWidth(2);

                    tile.setStroke(randomColor);

                    hBox.getChildren().add(tile);

                }
                hBox.setLayoutX(x);
                hBox.setLayoutY(y);
                tile_group.getChildren().add(hBox);

            }
            HBox hBox_text= new HBox();
            hBox_text.setLayoutY(text_y);
            hBox_text.setLayoutX(text_x);
            hBox_text.getChildren().add(score_text);
            tile_group.getChildren().add(hBox_text);

        }

        int player= stateString.indexOf("p");
        while (player!=-1){
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Color randomColor = Color.rgb(r, g, b);
            String player_sub=stateString.substring(player, stateString.indexOf(";", player+1)+1);

            int[] info=new int[7];

            int first_space=player_sub.indexOf(" ");
            int end_space=player_sub.indexOf(" ", first_space+1);
            for (int i=0;i<7;i++){
                String pos_string=player_sub.substring(first_space+1, end_space);
                info[i]=Integer.parseInt(pos_string);
                first_space=end_space;
                end_space=player_sub.indexOf(" ", first_space+1);
            }


            String player_info="player"+info[0]+"score:"+info[1]+"coconuts:"+info[2]+" bamboo:"+info[3]+" water:"+info[4]+"precious stone:"+info[5]+" statuettes:"+info[6];
            Text player_display=new Text(player_info);
            player_display.setLayoutX(10);
            player_display.setLayoutY((info[1]+1)*10);
            root.getChildren().add(player_display);
            player=stateString.indexOf("p", player+1);

            String player_S= player_sub.substring(player_sub.indexOf("S"),player_sub.indexOf("T"));
            first_space=player_S.indexOf(" ");
            end_space=player_S.indexOf(" ", first_space+1);
            while (end_space!=-1){
                String pos_string=player_S.substring(first_space+1, end_space);
                int row_pos=Integer.parseInt(pos_string.substring(0,pos_string.indexOf(",")));
                int col_pos=Integer.parseInt(pos_string.substring(pos_string.indexOf(",")+1));

                if (row_pos % 2 ==0){
                    x=initial_x+col_pos*side*1.73;
                }else {
                    x=initial_x-side*1.73/2+col_pos*side*1.73;
                }
                y=initial_y+row_pos*side*3/2;
                Tile tile=new Tile(0,0, side);
                String substring = stateString.substring(stateString.indexOf("i"), stateString.indexOf("s"));
                if (substring.contains(" "+pos_string+" ") ||
                        substring.contains(" "+pos_string+";")){
                    String name_s="sforplayer"+info[0]+"_pic.png";
                    URL s_url = getClass().getResource(name_s);
                    Image s_image = new Image(s_url.toString());
                    tile.setFill(new ImagePattern(s_image));
                }else {
                    String name_s="boat4player"+info[0]+"_pic.png";
                    URL s_url = getClass().getResource(name_s);
                    Image s_image = new Image(s_url.toString());
                    tile.setFill(new ImagePattern(s_image));
                }

                HBox hBox=new HBox();
                hBox.setLayoutY(y);
                hBox.setLayoutX(x);
                hBox.getChildren().add(tile);
                tile.setStrokeWidth(2);
                tile.setStroke(randomColor);
                tile_group.getChildren().add(hBox);
                first_space=end_space;
                end_space=player_S.indexOf(" ", first_space+1);
            }

            String player_T=player_sub.substring(player_sub.indexOf("T"));
            first_space=player_T.indexOf(" ");
            while (first_space!=-1){
                end_space=player_T.indexOf(" ", first_space+1);
                if (end_space==-1){
                    end_space=player_T.indexOf(";");
                }
                String pos_string=player_T.substring(first_space+1, end_space);
                int row_pos=Integer.parseInt(pos_string.substring(0,pos_string.indexOf(",")));
                int col_pos=Integer.parseInt(pos_string.substring(pos_string.indexOf(",")+1));
                if (row_pos % 2 ==0){
                    x=initial_x+col_pos*side*1.73;
                }else {
                    x=initial_x-side*1.73/2+col_pos*side*1.73;
                }
                y=initial_y+row_pos*side*3/2;
                Tile tile=new Tile(0,0, side);
                String name_s="village4player"+info[0]+"_pic.png";
                URL s_url = getClass().getResource(name_s);
                Image s_image = new Image(s_url.toString());
                tile.setFill(new ImagePattern(s_image));
                HBox hBox=new HBox();
                hBox.setLayoutY(y);
                hBox.setLayoutX(x);
                hBox.getChildren().add(tile);
                tile.setStrokeWidth(2);
                tile.setStroke(randomColor);
                tile_group.getChildren().add(hBox);
                first_space=player_T.indexOf(" ", first_space+1);
            }


        }

        root.getChildren().add(tile_group);
        // FIXME Task 5
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label playerLabel = new Label("Game State:");
        stateTextField = new TextField();
        stateTextField.setPrefWidth(200);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(stateTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, stateTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 30);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Blue Lagoon Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

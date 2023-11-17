package comp1110.ass2.gui;
import comp1110.ass2.BlueLagoon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import comp1110.ass2.tools;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

// FIXME Task 14
// FIXME Task 15
public class Game extends Application {
    private final int initial_x=300;
    private final int initial_y=30;
    private final int side=30;
    private final Group root = new Group();
    private final Group controls = new Group();
    private final VBox buttons=new VBox();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    private int player_num;
    private int AI_num;
    private int human_num;
    private String stateString;
    int is_AI_turn=0;
    public static String moveString;
    ComboBox<Integer> comboBox_player = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4));
    ComboBox<Integer> comboBox_AI = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3));
    ComboBox<Integer> comboBox_AI_difficult = new ComboBox<>(FXCollections.observableArrayList( 1, 2));
    ComboBox<String> comboBox_map = new ComboBox<>(FXCollections.observableArrayList(
            "DEFAULT_GAME", "WHEELS_GAME", "FACE_GAME"));
    Button confirmButton_start = new Button("START");
    Alert alert = new Alert(AlertType.INFORMATION);
    Label player_display = new Label();
    public static int end=0;
    int difficulty=0;

    Color[] color=new Color[10];

    public void makeControls(){
        int method=comboBox_map.getSelectionModel().getSelectedIndex();
        if (method==0){
            stateString=initial_stateString(BlueLagoon.DEFAULT_GAME);
        }else if (method==1){
            stateString=initial_stateString(BlueLagoon.WHEELS_GAME);
        } else if (method==2){
            stateString=initial_stateString(BlueLagoon.FACE_GAME);
        } else if (method==3) {
            stateString=initial_stateString(BlueLagoon.SPACE_INVADERS_GAME);
        }
        stateString=BlueLagoon.distributeResources(stateString);
        displayState(stateString);

        root.setOnMouseClicked(event -> {
            String new_state=stateString;
           stateString=BlueLagoon.applyMove(stateString, moveString);
           if (new_state.equals(stateString)){
               alert.setContentText("The move is invalid");
               alert.showAndWait();
           }else {
               is_AI_turn++;
               System.out.println(is_AI_turn);
               System.out.println(human_num);
           }
           displayState(stateString);
           if (is_AI_turn==human_num){
               for (int i=0;i<AI_num;i++){
                   if (difficulty==1){
                       moveString=BlueLagoon.AI_notIntelligent(stateString);
                   }else if (difficulty==2){
                       moveString=BlueLagoon.generateAIMove(stateString);
                   }
                   stateString=BlueLagoon.applyMove(stateString, moveString);
                   displayState(stateString);
               }
               is_AI_turn=0;
           }
           if (end==1){
               alert.setContentText("Exploration Phase end"+"\n"+" Now go to Settlement Phase");
               end=0;
               alert.showAndWait();
           }else if (end==2){
               alert.setContentText("Game over, winner is "+tools.get_winner(stateString));
               alert.showAndWait();
           }


        });

    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url=getClass().getResource("start_b.png");
        player_display.setStyle("-fx-font-family: 'Impact'; -fx-font-size: 15px; -fx-text-fill: white;");
        Image backgroundImage = new Image(url.toString());

        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        stage.setTitle("Blue Lagoon");
        comboBox_AI.setPrefWidth(200);
        comboBox_AI_difficult.setPrefWidth(200);
        comboBox_map.setPrefWidth(200);
        comboBox_player.setPrefWidth(200);
        comboBox_map.setPromptText("map method");
        comboBox_AI.setPromptText("AI number");
        comboBox_player.setPromptText("player number");
        comboBox_AI_difficult.setPromptText("difficulty of AI");
        //set the buttons x and y
        buttons.setSpacing(10);
        //combobox_player use to choose the num of players, map use to choose the map.
        buttons.getChildren().addAll(comboBox_player, comboBox_map, comboBox_AI, comboBox_AI_difficult, confirmButton_start);

        buttons.setAlignment(Pos.CENTER);
        VBox startPage = new VBox();

        confirmButton_start.setOnAction(event -> {
            URL gb_url=getClass().getResource("g_b.png");
            Image game_backgroundImage = new Image(gb_url.toString());

            // 创建背景图像视图
            BackgroundImage game_backgroundImg = new BackgroundImage(game_backgroundImage,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Pane game_pane=new StackPane();
            game_pane.setBackground(new Background(game_backgroundImg));
            game_pane.getChildren().add(root);

            Scene scene = new Scene(game_pane, WINDOW_WIDTH, WINDOW_HEIGHT);
            set_color();

            root.getChildren().add(controls);
            root.getChildren().add(player_display);

            makeControls();
            stage.setScene(scene);
            });

        startPage.getChildren().add(buttons);
        startPage.setBackground(new Background(backgroundImg));
        Scene scene = new Scene(startPage, 400, 400);

        stage.setScene(scene);
        stage.show();

//        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
//        set_color();
//        comboBox_map.setPromptText("map method");
//        comboBox_AI.setPromptText("AI number");
//        comboBox_player.setPromptText("player number");
//
//        root.getChildren().add(controls);
//
//        //set the buttons x and y
//        buttons.setSpacing(10);
//        //combobox_player use to choose the num of players, map use to choose the map.
//        buttons.getChildren().addAll(comboBox_player, comboBox_map, comboBox_AI, confirmButton_start);
//        buttons.setLayoutX(10);
//        buttons.setLayoutY(10);
//        root.getChildren().add(buttons);
//        makeControls();
//
//        stage.setScene(scene);
//        stage.show();
    }

    //public void start(Stage stage)
    String initial_stateString(String stateString){
        player_num=comboBox_player.getSelectionModel().getSelectedItem();
        AI_num=comboBox_AI.getValue();
        human_num=player_num-AI_num;
        if (AI_num>0){
            difficulty=comboBox_AI_difficult.getValue();
        }
        String gameArrange=stateString.substring(0,stateString.indexOf(";")-1)+player_num;
        stateString=stateString.replaceAll("[a]\\s\\d+\\s+\\d+",gameArrange);
        stateString= stateString.substring(0,stateString.indexOf("p"));
        if (player_num==2){
            stateString=stateString+"p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T;";
        }else if (player_num==3){
            stateString=stateString+"p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T; p 2 0 0 0 0 0 0 S T;";
        }else if (player_num==4){
            stateString=stateString+"p 0 0 0 0 0 0 0 S T; p 1 0 0 0 0 0 0 S T; p 2 0 0 0 0 0 0 S T; p 3 0 0 0 0 0 0 S T;";
        }
        return stateString;
    }

    String current_moveString(String monveString){
        Game_Tile game_tile = new Game_Tile(initial_x,initial_y,side);
        //game_tile.mouseClicked(;
        return moveString;
    }
    public String current_statString(String stateString,String moveString){
        stateString=BlueLagoon.placePiece(stateString,moveString);
        stateString=BlueLagoon.applyMove(stateString,moveString);

        return stateString;
    }
    public String scorePad(String stateString){
        String scorepad="";
        //String[] allscorepad = new String[player_num];
        String allplayer= stateString.substring(stateString.indexOf("p"))+" ";
        String[] player = allplayer.split("; ");
        for (int i=0;i<player.length;i++){
            String[] singplayer=player[i].split(" ");
            scorepad=scorepad+"\n"+"\n"+"PlayerId:"+singplayer[1]+"\n"
                    +" Score:"+singplayer[2]+"\n"
                    +" Coconut:"+singplayer[3]+"\n"
                    +" Bamboo:"+singplayer[4]+"\n"
                    +" Water:"+singplayer[5]+"\n"
                    +" PreciousStone:"+singplayer[6]+"\n"
                    +" statuette:"+singplayer[7];
        }
        return scorepad;

    }

    void displayState(String stateString) {
        if (root.getChildren().size()>2){
            root.getChildren().remove(2);
        }
        player_display.setText(scorePad(stateString));
        Map<Integer, Island> island_map=tools.create_island_map(stateString);
        Group tile_group=new Group();
        int col=0;
        double x=0;
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
                    Tile tile=new Tile(row, i, side);
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
            Text score_text=new Text("island"+island_map.get(i).num+" Score:"+String.valueOf(island_map.get(i).score));
            score_text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            score_text.setFill(Color.WHITE);
            double text_x=0;
            double text_y=0;

            String c_string=stateString.substring(stateString.indexOf("C"), stateString.indexOf("B",stateString.indexOf("C"))+1);
            String b_string=stateString.substring(stateString.indexOf("B"), stateString.indexOf("W",stateString.indexOf("B"))+1);
            String w_string=stateString.substring(stateString.indexOf("W"), stateString.indexOf("P",stateString.indexOf("W"))+1);
            String p_string=stateString.substring(stateString.indexOf("P"), stateString.indexOf("S",stateString.indexOf("P"))+1);
            String s_string=stateString.substring(stateString.indexOf("S", 10), stateString.indexOf(";",stateString.indexOf("S"))+1);
            String player_string=stateString.substring(stateString.indexOf("p"));
            for (int j=0;j<island_map.get(i).positions.length;j++){
                int i_pos_j_row=island_map.get(i).positions[j].row;
                int i_pos_j_col=island_map.get(i).positions[j].col;
                if ((i_pos_j_row) % 2==0){
                    x=initial_x+(i_pos_j_col)*1.73*side;
                    text_x=x-50;
                }else {
                    x=initial_x - side * 1.73 / 2 +(i_pos_j_col)*1.73*side;
                    text_x=x-50;
                }
                y = initial_y+i_pos_j_row*side*3/2;
                text_y=y;
                Tile tile=new Tile(i_pos_j_row, i_pos_j_col, side);
                HBox hBox= new HBox();
                //&& (!player_string.contains(" "+i_pos_j_row+","+i_pos_j_col+";") && !player_string.contains(" "+i_pos_j_row+","+i_pos_j_col+" "))
                if (island_map.get(i).positions[j].type==0){
                    URL url = getClass().getResource("island_pic_2.png");
                    Image image = new Image(url.toString());
                    tile.setFill(new ImagePattern(image));
                    tile.setStrokeWidth(2);
                    tile.setStroke(color[i]);
                    hBox.getChildren().add(tile);
                } else if (island_map.get(i).positions[j].type==1){
                    URL url = getClass().getResource("stone_pic.png");
                    if (c_string.contains(" "+i_pos_j_row+","+i_pos_j_col+" ")
                            ||c_string.contains(" "+i_pos_j_row+","+i_pos_j_col+";")){
                        url = getClass().getResource("c_pic.png");
                    } else if (b_string.contains(" "+i_pos_j_row+","+i_pos_j_col+" ")
                            ||b_string.contains(" "+i_pos_j_row+","+i_pos_j_col+";")) {
                        url = getClass().getResource("b_pic.png");
                    } else if (w_string.contains(" "+i_pos_j_row+","+i_pos_j_col+" ")
                            ||w_string.contains(" "+i_pos_j_row+","+i_pos_j_col+";")) {
                        url = getClass().getResource("w_pic.png");
                    } else if (p_string.contains(" "+i_pos_j_row+","+i_pos_j_col+" ")
                            ||p_string.contains(" "+i_pos_j_row+","+i_pos_j_col+";")) {
                        url = getClass().getResource("p_pic.png");
                    } else if (s_string.contains(" "+i_pos_j_row+","+i_pos_j_col+" ")
                            ||s_string.contains(" "+i_pos_j_row+","+i_pos_j_col+";")) {
                        url = getClass().getResource("s_pic.png");
                    }

                    Image image = new Image(url.toString());
                    tile.setFill(new ImagePattern(image));

                    tile.setStrokeWidth(2);

                    tile.setStroke(color[i]);

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
        String stone_string=stateString.substring(stateString.indexOf("s"), stateString.indexOf(";",stateString.indexOf("s"))+1);
        VBox score=new VBox();
        score.setSpacing(10);
        while (player!=-1){

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

            //String player_info="player"+info[0]+"score:"+info[1]+"coconuts:"+info[2]+" bamboo:"+info[3]+" water:"+info[4]+"precious stone:"+info[5]+" statuettes:"+info[6];
//            Label player_display = new Label(scorePad(stateString));
//            player_display.setText(scorePad(stateString));
//            player_display.setLayoutX(10);
//            player_display.setLayoutY((info[1]+1)*20);
//            score.getChildren().add(player_display);
//            root.getChildren().add(score);
//            root.getChildren().add(player_display);



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
                Tile tile=new Tile(col_pos,row_pos, side);
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
//                tile.setStroke(randomColor);
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
                Tile tile=new Tile(col_pos,row_pos, side);
                String name_s="";
                if (stone_string.contains(" "+row_pos+","+col_pos+" ")||
                        stone_string.contains(" "+row_pos+","+col_pos+";")){
                    name_s="village4player"+info[0]+"_pic.png";
                }else {
                    name_s="landvillage4player"+info[0]+"_pic.png";
                }

                URL s_url = getClass().getResource(name_s);
                Image s_image = new Image(s_url.toString());
                tile.setFill(new ImagePattern(s_image));
                HBox hBox=new HBox();
                hBox.setLayoutY(y);
                hBox.setLayoutX(x);
                hBox.getChildren().add(tile);
                tile.setStrokeWidth(2);
//                tile.setStroke(randomColor);
                tile_group.getChildren().add(hBox);
                first_space=player_T.indexOf(" ", first_space+1);
            }


        }



        root.getChildren().add(tile_group);
        // FIXME Task 5
    }

    public void set_color(){
        Random random = new Random();
        for (int i=0;i<10;i++){
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Color randomColor = Color.rgb(r, g, b);
            color[i]=randomColor;
        }

    }



}

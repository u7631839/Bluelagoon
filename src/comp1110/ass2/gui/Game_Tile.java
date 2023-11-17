package comp1110.ass2.gui;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.awt.event.MouseEvent;


public class Game_Tile extends Polygon {
    private int col;
    private int row;
    private final DropShadow shadow = new DropShadow(20, Color.YELLOW);
    Game_Tile(double x, double y, double side){
        this.col= (int) x;
        this.row= (int) y;
        getPoints().addAll(
                x, y + side,
                x + side * 1.73 / 2, y + side * 1 / 2,
                x + side * 1.73 / 2, y - side * 1 / 2,
                x, y-side,
                x - side * 1.73 / 2, y - side * 1 / 2,
                x - side * 1.73 / 2, y + side * 1 / 2
        );
        this.setOnMouseEntered(event -> {
            this.setEffect(shadow);
        });
        this.setOnMouseExited(event -> {
            this.setEffect(null);
        });

    }

    private String afterClick(MouseEvent event){
        return this.col+","+this.row;
    }

    public String mouseClicked(MouseEvent event){
        String moveString="";
        if (event.getButton()==MouseEvent.BUTTON1) {
            moveString = "S " + this.col + "," + this.row;
        }else if (event.getButton()==MouseEvent.BUTTON2){
            moveString = "T " +this.col+","+this.row;
        }
        return moveString;
    }
}

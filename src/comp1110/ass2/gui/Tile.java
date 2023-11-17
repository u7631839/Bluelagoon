package comp1110.ass2.gui;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

import java.awt.event.MouseEvent;


public class Tile extends Polygon {
    private int row;
    private int col;
    Tile(int row, int col, double side){
        this.row=row;
        this.col=col;
        getPoints().addAll(
                0.0,  side,
                0 + side * 1.73 / 2, 0 + side * 1 / 2,
                0 + side * 1.73 / 2, 0 - side * 1 / 2,
                0.0, 0 - side,
                0 - side * 1.73 / 2, 0 - side * 1 / 2,
                0 - side * 1.73 / 2, 0 + side * 1 / 2
        );
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.YELLOW);
        shadow.setRadius(20);
        this.setOnMouseEntered(event -> {
            this.setEffect(shadow);

        });
        this.setOnMouseExited(event -> {
            this.setEffect(null);
        });
        this.setOnMouseClicked(event -> {
            if (event.getButton()==MouseButton.PRIMARY) {
                Game.moveString = "S " + this.row + "," + this.col;
            }else if (event.getButton()==MouseButton.SECONDARY){
                Game.moveString = "T " +this.row+","+this.col;
            }
        });
    }

}

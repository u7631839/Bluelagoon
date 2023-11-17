package comp1110.ass2.D2B;
import comp1110.ass2.Resources;

import java.util.Objects;

public class NPosition {
    int row;
    int col;
    String type;
    int player_num;
    Resources.resources resource;
    String URL;
    public NPosition(int row,int col,String type){
        this.row=row;
        this.col=col;
        this.type=type;
        this.player_num=-1;
    }

    public void set_type(String new_type){
        this.type=new_type;
    }
    public void setPlayer_num(int player_num){
        this.player_num=player_num;
    }
    public void set_resource(String resource){
        if (Objects.equals(resource, "C")){
            this.resource=Resources.resources.Coconut;
        } else if (Objects.equals(resource, "B")) {
            this.resource=Resources.resources.Bamboo;
        }else if (Objects.equals(resource, "W")) {
            this.resource=Resources.resources.Water;
        }else if (Objects.equals(resource, "P")) {
            this.resource=Resources.resources.Precious;
        }else if (Objects.equals(resource, "S")) {
            this.resource=Resources.resources.Statiette;
        }
    }
}
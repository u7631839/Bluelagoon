package comp1110.ass2.D2B;

import java.util.ArrayList;


public class usless_Resource {

    public static ArrayList<NPosition> c_positions=new ArrayList<>();
    public static ArrayList<NPosition> w_positions=new ArrayList<>();
    public static ArrayList<NPosition> s_positions=new ArrayList<>();
    public static ArrayList<NPosition> p_positions=new ArrayList<>();
    public static ArrayList<NPosition> b_positions=new ArrayList<>();

    public usless_Resource(){}

    public static void add_resource(String type, String pos_sub){
        int row=Integer.parseInt(pos_sub.substring(0,pos_sub.indexOf(",")));
        int col=Integer.parseInt(pos_sub.substring(pos_sub.indexOf(",")+1));
        if (type.equals("C")){
            NPosition position=new NPosition(row, col, type);
            c_positions.add(position);
        } else if (type.equals("W")) {
            NPosition position=new NPosition(row, col, type);
            w_positions.add(position);
        } else if (type.equals("S")) {
            NPosition position=new NPosition(row, col, type);
            s_positions.add(position);
        }else if (type.equals("P")) {
            NPosition position=new NPosition(row, col, type);
            p_positions.add(position);
        }else if (type.equals("B")) {
            NPosition position=new NPosition(row, col, type);
            b_positions.add(position);
        }
    }
}

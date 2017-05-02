import java.util.ArrayList;
import java.awt.Color;

public class SquareColors {

//Dmitry
    //ArrayList that'll contain the colors
    ArrayList<Color> ColorList =new ArrayList<Color>();  //ArrayList to store colors
    int color;
    ColorPanel square;
    public SquareColors(int c){

        //Colors being added to ArrayList for easier access
        ColorList.add(Color.darkGray);                                              //0 index
        ColorList.add(Color.darkGray);                                              //1 index     //Todo: Clean up repeated colors
        ColorList.add(Color.white);                                                 //2 index
        ColorList.add(Color.red);                                                   //3 index
        ColorList.add(Color.red);                                                   //4 index
        color=c;                                                                    // index is set to external int variable
        square = new ColorPanel(ColorList.get(color));
    }
    public void changeColor(int c){

        square.ChangeColor(ColorList.get(c));

    }
}

//used to paint game tiles
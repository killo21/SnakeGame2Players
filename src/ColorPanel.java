import java.awt.Color;
import javax.swing.JPanel;
//written by Matt
public class ColorPanel extends JPanel{


    public ColorPanel(Color color){
        this.setBackground(color); //sets background panel color
    }

    public void ChangeColor(Color color){
        this.setBackground(color);
        this.repaint();  //changes background panel color
    }

}

//used to create game tiles

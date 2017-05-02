//added by Mateusz
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InitiateProgram extends JFrame
{
    static JFrame f2;
    static void displayJFrame() {
        f2 = new JFrame("Welcome to the Snake game!");
        JButton Start = new JButton("Start Game!");

        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(false);

                GameWindow f1 = new GameWindow();
                f1.setTitle("Snake Game");
                f1.setSize(600, 600);
                f1.setVisible(true);
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setLocationRelativeTo(null); } });



        f2.getContentPane().setLayout(new FlowLayout());
                f2.add(Start);
                f2.setPreferredSize(new Dimension(600, 600));
                f2.pack();
                f2.setLocationRelativeTo(null);
                f2.setVisible(true);
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
    }
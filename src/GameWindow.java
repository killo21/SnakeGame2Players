import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;


class GameWindow extends JFrame{

    public static ArrayList<ArrayList<SquareColors>> Grid;                    //ArrayList inside an ArrayList makes a matrix
    public static int width = 20;                                             //Todo: Make the amount of tiles editable
    public static int height = 20;
    public GameWindow(){


        Grid = new ArrayList<ArrayList<SquareColors>>();                      //Matrix of game tiles
        ArrayList<SquareColors> allData;

        // Creates Threads and its data and adds it to the arrayList
        for(int i=0;i<width;i++){
            allData= new ArrayList<SquareColors>();
            for(int j=0;j<height;j++){
                SquareColors c = new SquareColors(2);
                allData.add(c);
            }
            Grid.add(allData);                                                    //fills ArrayLists inside of an ArrayList with white tiles
        }

        // Setting up the layout of the panel
        getContentPane().setLayout(new GridLayout(20,20,0,0));  //sets up a 20x20 grid

        // Start & pauses all threads, then adds every square of each thread to the panel
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                getContentPane().add(Grid.get(i).get(j).square);             //fills the created grid with elements (white tiles) from previously made matrix
            }
        }

        /*was unsure of how to display my matrix. Used https://gamedevelopment.tutsplus.com/tutorials/an-introduction-to-creating-a-tile-map-engine--gamedev-10900
        to understand how it is done. Resource was in another language, so I found the equivalent
        in Java in https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html*/

        // initial position of the snake
        TupleMaker position1 = new TupleMaker(10,10);                //snake1 starts at (10,10)
        TupleMaker position2 = new TupleMaker(10, 13);               //snake2 starts 3 tiles down

        //positions of each snake passed to the spawner class
        SnakeFoodSpawn spawnStart = new SnakeFoodSpawn(position1, position2);


        //starts food spawning
        spawnStart.start();

        //adds
        this.addKeyListener((KeyListener) new InputTrackerSnake1());
        this.addKeyListener((KeyListener)new InputTrackerSnake2());



    }
}

//Dmitry
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputTrackerSnake1 extends KeyAdapter{

    public void keyPressed(KeyEvent e1){
        if(e1.getKeyCode() == 39){                       // right arrow
            if(SnakeFoodSpawn.snake1Direction !=2) {     //cannot turn back into its own tail. Breaks game
                SnakeFoodSpawn.snake1Direction =1;
            }
        } else if(e1.getKeyCode() == 37){                // left arrow
            if(SnakeFoodSpawn.snake1Direction !=1) {     //cannot turn back into its own tail. Breaks game
                SnakeFoodSpawn.snake1Direction = 2;
            }
        } else if(e1.getKeyCode() == 38){                // up arrow
            if(SnakeFoodSpawn.snake1Direction !=4) {     //cannot turn back into its own tail. Breaks game
                SnakeFoodSpawn.snake1Direction = 3;
            }
        } else if(e1.getKeyCode() == 40){                //down arrow
            if(SnakeFoodSpawn.snake1Direction !=3) {     //cannot turn back into its own tail. Breaks game
                SnakeFoodSpawn.snake1Direction = 4;
            }
        }
    }

}

/*Was unsure of how to track key presses in Java so I found this thread
http://stackoverflow.com/questions/18037576/how-do-i-check-if-the-user-is-pressing-a-key
which lead me to the Java doc for KeyAdapter
https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyAdapter.html*/
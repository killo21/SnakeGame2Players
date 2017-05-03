import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//implemented by Mateuz
public class InputTrackerSnake2 extends KeyAdapter{
    public void keyPressed(KeyEvent e2){
        switch(e2.getKeyCode()){
            case 68:	// d key
                if(SnakeFoodSpawn.snake2Direction !=2)
                    SnakeFoodSpawn.snake2Direction =1;
                break;
            case 87:	// w key
                if(SnakeFoodSpawn.snake2Direction !=4)
                    SnakeFoodSpawn.snake2Direction =3;
                break;

            case 65: 	// a key
                if(SnakeFoodSpawn.snake2Direction !=1)
                    SnakeFoodSpawn.snake2Direction =2;
                break;

            case 83:	// s key
                if(SnakeFoodSpawn.snake2Direction !=3)
                    SnakeFoodSpawn.snake2Direction =4;
                break;

            default: 	break;
        }
    }


}

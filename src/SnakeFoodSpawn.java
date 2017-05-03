import java.util.ArrayList;
//most parts implemented by Dmitry Shlyapnikov

//Controls most of the game logic in this program.
public class SnakeFoodSpawn extends Thread {
    ArrayList<ArrayList<SquareColors>> Squares= new ArrayList<ArrayList<SquareColors>>();
    Tuple headSnake1Pos;
    Tuple headSnake2Pos;
    int sizeSnake1 = 3;
    int sizeSnake2 = 3;
    long speed = 50;                    // used for "sleep". smaller = les delay between moves
    public static int snake1Direction;  //1, 2, 3, or 4
    public static int snake2Direction;  //1, 2, 3, or 4


    ArrayList<Tuple> positions1 = new ArrayList<Tuple>();
    ArrayList<Tuple> positions2 = new ArrayList<Tuple>();
    Tuple food1Position;
    Tuple food2Position;

    //implemented by Dmitry Shlyapnikov

    //Constructor of ControllerThread
    SnakeFoodSpawn(Tuple positionDepart1, Tuple positionDepart2){
        //Get all the threads
        Squares= GameWindow.Grid;

        //starts both snakes at their respective starting points and points them to the right
        headSnake1Pos =new Tuple(positionDepart1.x,positionDepart1.y);
        snake1Direction = 1;
        headSnake2Pos =new Tuple(positionDepart2.x,positionDepart2.y);
        snake2Direction = 1;

        //makes a tuple of each snake's head position
        Tuple headPos1 = new Tuple(headSnake1Pos.getX(), headSnake1Pos.getY());
        Tuple headPos2 = new Tuple(headSnake2Pos.getX(), headSnake2Pos.getY());

        //adds tuples of head positions to respective position ArrayLists
        positions1.add(headPos1);
        positions2.add(headPos2);

        //sets initial spawn locations for food1 and food2 in the bottom right hand corner of the screen
        food1Position = new Tuple(GameWindow.height-4, GameWindow.width-1);
        spawnFood1(food1Position);
        food2Position = new Tuple(GameWindow.height-1, GameWindow.width-1);
        spawnFood2(food2Position);




    }

    //what happens when program is run
    public void run() {
        while(true){
            moveHeadSnake1(snake1Direction);
            moveHeadSnake2(snake2Direction);
            checkForCollisions();
            refreshSnake1();
            refreshSnake2();
            deleteSnakeTail1();
            deleteSnakeTail2();
            pause();
        }
    }

    //delay in milliseconds between snake movements. relies on speed int
    private void pause(){
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*Code for pause function found at "https://www.java-forums.org/new-java/29771-move-pause-method.html"
     */

    //implemented by Dmitry Shlyapnikov

    //checks for collisions between a snake and food or a snake and itself
    private void checkForCollisions() {
        Tuple evalPosition1 = positions1.get(positions1.size()-1);         //final tuple in positions1 is the head of snake1
        for(int i = 0; i<= positions1.size()-2; i++){                      //head cannot collide with itself, so positions1.size()-2 is used
            boolean biteItself = evalPosition1.getX()== positions1.get(i).getX() && evalPosition1.getY()== positions1.get(i).getY();
            //boolean biteOther = posCritique1.getX()== positions2.get(i).getX() && posCritique1.getY()== positions2.get(i).getY();   Todo: Add collisions between two snakes
            if(biteItself){
                stopTheGame();     //if snake1 head occupies same tile as one of its tail pieces, stop game
            }
        }

        Tuple evalPosition2 = positions2.get(positions2.size()-1);     //final tuple in positions2 is the head of snake2
        for(int i = 0; i<= positions2.size()-2; i++){                       //head cannot collide with itself, so positions2.size() -2 is used
            boolean biteItself = evalPosition2.getX()== positions2.get(i).getX() && evalPosition2.getY()== positions2.get(i).getY();
            //boolean biteOther = evalPosition.getX()== positions1.get(i).getX() && evalPosition.getY()== positions1.get(i).getY();   Todo: Add collisions between two snakes
            if(biteItself){
                stopTheGame();
            }
        }

        boolean eatingFood1 = evalPosition1.getX()== food1Position.y && evalPosition1.getY()== food1Position.x; //snake1 eats food1
        boolean eatingFood2 = evalPosition2.getX()== food2Position.y && evalPosition2.getY()== food2Position.x; //snake2 eats food2
        boolean eatingFood3 = evalPosition2.getX()== food1Position.y && evalPosition2.getY()== food1Position.x; //snake2 eats food1
        boolean eatingFood4 = evalPosition1.getX()== food2Position.y && evalPosition1.getY()== food2Position.x; //snake1 eats food2
        if(eatingFood1){
            System.out.println("Yummy!");
            sizeSnake1 = sizeSnake1 +1; //snake1 grows
            food1Position = getValNotInSnake();

            spawnFood1(food1Position);//food1 spawns
        } else if(eatingFood2){
            System.out.println("Yummy!");
            sizeSnake2 = sizeSnake2 +1; //snake2 grows
            food2Position = getValNotInSnake();

            spawnFood2(food2Position); //food2 spawns
        } else if(eatingFood3){
            System.out.println("Yummy!");
            sizeSnake2 = sizeSnake2 +1; //snake2 grows
            food1Position = getValNotInSnake();

            spawnFood1(food1Position); //food1 spawns
        } else if(eatingFood4){
            System.out.println("Yummy!");
            sizeSnake1 = sizeSnake1 +1; //snake1 grows
            food2Position = getValNotInSnake();

            spawnFood2(food2Position); //food2 spawns
        }
    }

    //Stops The Game
    private void stopTheGame(){
        System.out.println("COLISION! \n");
        while(true){
            pause(); //Todo: "Restart Game" button
        }
    }

    //implemented by Dmitry Shlyapnikov

    //Put food in a position and displays it
    private void spawnFood1(Tuple foodPositionIn){
        Squares.get(foodPositionIn.x).get(foodPositionIn.y).changeColor(1);
    }
    private void spawnFood2(Tuple foodPositionIn){
        Squares.get(foodPositionIn.x).get(foodPositionIn.y).changeColor(3);
    }

    //return a position not occupied by the snake
    private Tuple getValNotInSnake(){
        Tuple p ;
        int ranX= 0 + (int)(Math.random()*19);
        int ranY= 0 + (int)(Math.random()*19);
        p=new Tuple(ranX,ranY);
        for(int i = 0; i<= positions1.size()-1; i++){
            if(p.getY()== positions1.get(i).getX() && p.getX()== positions1.get(i).getY()){
                ranX= 0 + (int)(Math.random()*19);
                ranY= 0 + (int)(Math.random()*19);
                p=new Tuple(ranX,ranY);
                i=0;
            }
        }
        return p;
    }

    //implemented by Dmitry Shlyapnikov

    //Moves the head of the snake and refreshes the positions1 in the arraylist
    //1:right 2:left 3:top 4:bottom 0:nothing
    private void moveHeadSnake1(int dir){
        switch(dir){
            case 4:
                headSnake1Pos.ChangeData(headSnake1Pos.x,(headSnake1Pos.y+1)%20);
                positions1.add(new Tuple(headSnake1Pos.x, headSnake1Pos.y));
                break;
            case 3:
                if(headSnake1Pos.y-1<0){
                    headSnake1Pos.ChangeData(headSnake1Pos.x,19);
                }
                else{
                    headSnake1Pos.ChangeData(headSnake1Pos.x,Math.abs(headSnake1Pos.y-1)%20);
                }
                positions1.add(new Tuple(headSnake1Pos.x, headSnake1Pos.y));
                break;
            case 2:
                if(headSnake1Pos.x-1<0){
                    headSnake1Pos.ChangeData(19, headSnake1Pos.y);
                }
                else{
                    headSnake1Pos.ChangeData(Math.abs(headSnake1Pos.x-1)%20, headSnake1Pos.y);
                }
                positions1.add(new Tuple(headSnake1Pos.x, headSnake1Pos.y));

                break;
            case 1:
                headSnake1Pos.ChangeData(Math.abs(headSnake1Pos.x+1)%20, headSnake1Pos.y);
                positions1.add(new Tuple(headSnake1Pos.x, headSnake1Pos.y));
                break;
        }
    }

    private void moveHeadSnake2(int dir){
        switch(dir){
            case 4:
                headSnake2Pos.ChangeData(headSnake2Pos.x,(headSnake2Pos.y+1)%20);
                positions2.add(new Tuple(headSnake2Pos.x, headSnake2Pos.y));
                break;
            case 3:
                if(headSnake2Pos.y-1<0){
                    headSnake2Pos.ChangeData(headSnake2Pos.x,19);
                }
                else{
                    headSnake2Pos.ChangeData(headSnake2Pos.x,Math.abs(headSnake2Pos.y-1)%20);
                }
                positions2.add(new Tuple(headSnake2Pos.x, headSnake2Pos.y));
                break;
            case 2:
                if(headSnake2Pos.x-1<0){
                    headSnake2Pos.ChangeData(19, headSnake2Pos.y);
                }
                else{
                    headSnake2Pos.ChangeData(Math.abs(headSnake2Pos.x-1)%20, headSnake2Pos.y);
                }
                positions2.add(new Tuple(headSnake2Pos.x, headSnake2Pos.y));

                break;
            case 1:
                headSnake2Pos.ChangeData(Math.abs(headSnake2Pos.x+1)%20, headSnake2Pos.y);
                positions2.add(new Tuple(headSnake2Pos.x, headSnake2Pos.y));
                break;
        }
    }

    //implemented by Dmitry Shlyapnikov

    //refreshes all of the game's squares based on snake1 movement
    private void refreshSnake1(){
        for(Tuple t : positions1){
            int y = t.getX();
            int x = t.getY();
            Squares.get(x).get(y).changeColor(0);

        }
    }

    // refreshes all of the game's squares based on snake2 movement
    private void refreshSnake2(){
        for(Tuple t : positions2){
            int y = t.getX();
            int x = t.getY();
            Squares.get(x).get(y).changeColor(3);

        }
    }

    //Refreshes the tail of each snake. Removes unneeded tiles by coloring them white.
    //and refreshing the display of the things that is removed
    //implemented by Mateuz
    private void deleteSnakeTail1(){
        int len = sizeSnake1;
        for(int i = positions1.size()-1; i>=0; i--){
            if(len==0){
                Tuple t = positions1.get(i);
                Squares.get(t.y).get(t.x).changeColor(2);
            }
            else{
                len--;
            }
        }
        len = sizeSnake1;
        for(int i = positions1.size()-1; i>=0; i--){
            if(len==0){
                positions1.remove(i);
            }
            else{
                len--;
            }
        }
    }
    //implemented by Mateuz
    private void deleteSnakeTail2(){
        int len = sizeSnake2;
        for(int i = positions2.size()-1; i>=0; i--){
            if(len==0){
                Tuple t = positions2.get(i);
                Squares.get(t.y).get(t.x).changeColor(2);
            }
            else{
                len--;
            }
        }
        len = sizeSnake2;
        for(int i = positions2.size()-1; i>=0; i--){
            if(len==0){
                positions2.remove(i);
            }
            else{
                len--;
            }
        }
    }
}

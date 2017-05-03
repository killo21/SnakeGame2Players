//implemented by Dmitry Shlyapnikov
public class Tuple {
    public  int x;
    public  int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void ChangeData(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }     //getters
    public int getY(){
        return y;
    }

}

//Tuple class based very closely off of "http://stackoverflow.com/questions/2670982/using-pairs-or-2-tuples-in-java"

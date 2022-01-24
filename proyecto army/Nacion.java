import java.util.ArrayList;
/**
 * Write a description of class Nacion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nacion
{
    // instance variables - replace the example below with your own
    private String color;
    private int x;
    private int y;
    private int ejercitos;
    private Rectangle forma;
    private int conquer;
    /**
     * Constructor de la clase
     */
    public Nacion(String ncolor, int  Posx, int Posy, int armies)
    {
        forma = new Rectangle();
        color=ncolor;
        x=Posx;
        y=Posy;
        conquer=0;
        ejercitos=armies;
        forma.changePosition(x,y);
        forma.changeColor(color);
    }

    /**
     * Este método hará visible las naciones
     */
    public void makeVisible()
    {
        forma.makeVisible();  
    }
    /**
     * Este método hará invisible la nación
     */
    public void makeInvisible()
    {
        forma.makeInvisible();  
    }
    /**
     * Este método retornará el color que tiene la nación
     * @Return retornará un String con el color que tiene la nación
     */
    public String getColor(){
        return color;
    }
    /**
     * Este método retornará la posición de la nación
     * @Return retornará una lista de enteros los cuales dirán cuál es la posición x y y de la nación
     */
    public int[] getPos(){
        int[] pos={x,y};
        return pos;
    }
    public int getArmies(){
        return ejercitos;
    }
    public void conquers(){
        conquer+=1;
    }
    public void conquersnt(){
        conquer-=1;
    }
    public int getConquer(){
        return conquer;
    }
    //public String getColor(Nacion n){
    //    for (int i=0;i<n.size();i++){
    //        n.get(i).getColor()
    //    }
}



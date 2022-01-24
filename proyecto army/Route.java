import java.util.ArrayList;
/**
 * Write a description of class Route here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Route
{
    // instance variables - replace the example below with your own
    private Nacion na;
    private Nacion nb;
    private int valor;
    private Line p;
    /**
     * Constructor for objects of class Route
     */
    public Route(Nacion na, Nacion nb, int cost)
    {
        // initialise instance variables
        this.na=na;
        this.nb=nb;
        valor=cost;
        int [] a = na.getPos();
        int [] b = nb.getPos();
        p = new Line(a[0]+20,a[1]+20,b[0]+20,b[1]+20,"green");
    }
    public void makeVisible(){
        p.makeVisible();
    }   
    public void makeInvisible(){
        p.makeVisible();
    }
    public String [] getLocation(){
        String [] location={na.getColor(),nb.getColor()};
        return location;
    }
}


/**
 * Write a description of class Army here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Army
{
    // instance variables - replace the example below with your own
    private Triangle forma;
    private String color;
    private int[] pos;
    /**
     * Constructor for objects of class Army
     */
    public Army(int[] pos, String ncolor)
    {
        // initialise instance variables
        forma=new Triangle();
        color=ncolor;
        forma.changePosition(pos[0]+20,pos[1]+20);
    }
    public void makeVisible()
    {
        forma.makeVisible();  
    }
    public void makeInvisible()
    {
        forma.makeInvisible();  
    }
    public String getColor(){
        return color;
    }
}

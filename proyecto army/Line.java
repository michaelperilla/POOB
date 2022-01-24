import java.awt.*;
import java.awt.geom.*;
/**
 * Write a description of class Line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line
{
    // instance variables - replace the example below with your own
    private int xa;
    private int ya;
    private int xb;
    private int yb;
    private String color;
    private boolean isVisible;
    /**
     * El constructor de la clase Line
     * @Param xa recibe la posición x de la primer nacion
     * @Param ya recibe la posición y de la primer nacion
     * @Param xb recibe la posición x de la segunda nacion
     * @Param yb recibe la posición y de la segunda nacion
     * @Param ncolor recibe el color que tendrá la ruta
     */
    public Line(int xa, int ya, int xb, int yb, String ncolor)
    {
        // initialise instance variables
        this.xa = xa;
        this.ya = ya;
        this.xb = xb;
        this.yb = yb;
        color = ncolor;
        isVisible = false;
    }
    /**
     * El metodo que hará visible la línea
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    /**
     * El metodo que hará invisible la línea
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    /**
     * Este metodo dibujará la línea dependiendo de los parametros recibidos
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int [] xpoints = {xa,xb}; 
            int [] ypoints = {ya,yb}; 
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 2));
            canvas.wait(10);
        }
    }
    /**
     * Este metodo borrará este objeto
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

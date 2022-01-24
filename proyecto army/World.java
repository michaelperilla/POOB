import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class World here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World
{
    // instance variables - replace the example below with your own
    private Rectangle tablero;
    private Nacion nation;
    private ArrayList <Nacion> naciones;
    private Army ejercito;
    private ArrayList <Army> ejercitos;
    private Route ruta;
    private ArrayList <Route> rutas;
    private ArrayList <String> conq;
    private boolean ok=false;
    private boolean visible=false;
    /**
     * Constructor for objects of class World
     */
    public World(int length, int width)
    {
        // initialise instance variables
        naciones= new ArrayList();
        rutas=new ArrayList();
        ejercitos=new ArrayList();
        tablero = new Rectangle();
        tablero.changeSize(length,width);
        tablero.changeColor("black");
    }
    public World(int nations, int [][] routes, int [][] armies){
        naciones= new ArrayList();
        rutas=new ArrayList();
        ejercitos=new ArrayList();
        tablero = new Rectangle();
        tablero.changeSize(1000,1000);
        tablero.changeColor("black");
        String[] colores={"red","blue","green","yellow","magenta","white"};
        int contx=0;
        for (int i=0;i<nations;i++){
             addNation(colores[i],contx,contx,armies[i][1]);
             contx+=20;
        } 
        for (int v=0;v<nations-1;v++){
            addRoute(colores[routes[v][0]],colores[routes[v][1]],routes[v][2]);
        }
    }
    /**
     * Un metodo que añade naciones con un color determinado, en una posicion determinada y con un numero de ejercitos
     * @param color La nación se añadirá con el color que el usuario quiera
     * @param x Posición en x en la cual estará la nación
     * @param y Posición en y en la cual estará la nación
     * @param armies Cantidad de ejercitos necesaria para que una nación sea conquistada
     */
    public void addNation(String color, int x, int y, int armies)
    {
        if (posNac(color)==-1){
            nation = new Nacion(color, x,y,armies);
            naciones.add(nation);
            ok=true;
        }
        else{
            ok=false;
            mensaje("Esta nación ya ha sido creada");
        }
        
    }
    /**
     * Un metodo que hará visible todos los elementos en el tablero, ya sean ejercitos, naciones, etc...
     */
    public void makeVisible(){
        tablero.makeVisible();
        for (int i=0;i<naciones.size();i++){
            naciones.get(i).makeVisible();
        }
        for (int j=0;j<ejercitos.size();j++){
            ejercitos.get(j).makeVisible();
        }
        for (int k=0;k<rutas.size();k++){
            rutas.get(k).makeVisible();
        }
        visible = true;
    }
    /**
     * Un metodo que hará invisible todos los elementos incluido el tablero
     */
    public void makeInvisible(){
        tablero.makeInvisible();
        for (int i=0;i<naciones.size();i++){
            naciones.get(i).makeInvisible();
        }
        for (int j=0;j<ejercitos.size();j++){
            ejercitos.get(j).makeInvisible();
        }
        for (int k=0;k<rutas.size();k++){
            rutas.get(k).makeInvisible();
        }
        ok=true;
    }
    /**
     * Borrará la nación que tenga el color especificado por el usuario
     * @Param color Es un String con el color de la nación que se borrará
     */
    public void delNation(String color)
    {
        if (naciones.size()>0){
            for (int i=0;i<naciones.size();i++){
                if (color==naciones.get(i).getColor()){
                    naciones.remove(i);
                    delArmy(color);
                    for (int j=0; j<rutas.size();j++){
                        if (rutas.get(j).getLocation()[0]==color || rutas.get(j).getLocation()[1]==color){
                            rutas.remove(j);
                            ok=true;
                        }
                        
                    }
                }
            } 
        }
        else{
            ok=false;
        }

    }   
    /**
     * Pondrá un ejercito en una nación especificada por el usuario
     * @Param color Es un String con el color de la nación en la cual se agregará el ejercito
     */
    public void putArmy(String color){
        String ncolor=color;
        if (naciones.size()>0){
            for (int i=0;i<naciones.size();i++){
                if (ncolor==naciones.get(i).getColor()){
                    int[] pos=naciones.get(i).getPos();
                    ejercito = new Army(pos,ncolor);
                    ejercitos.add(ejercito);
                    naciones.get(i).conquers();
                    ok=true;
                }
            }     
        }
        else{
            ok=false;
        }

    }
    public void delArmy(String color){
        if (ejercitos.size()>0){
            for (int i=0;i<ejercitos.size();i++){
                if (color==ejercitos.get(i).getColor()){
                    ejercitos.remove(i);
                }
            }
            if (posNac(color)!=-1){
                int a=posNac(color);
                if (naciones.get(a).getConquer()>0){
                     naciones.get(a).conquersnt();
                }
            }
            ok=true;
        }
        else{
            ok=false;
            mensaje("No hay ejercitos para eliminar");
        }

    }
    public void addRoute(String locationA, String locationB, int cost){
        for (int i=0;i<naciones.size();i++){
            if (locationA==naciones.get(i).getColor()){
                for (int j=0;j<naciones.size();j++){
                    if (locationB==naciones.get(j).getColor()){
                        ruta = new Route(naciones.get(i),naciones.get(j), cost);
                        rutas.add(ruta);
                        ok=true;
                        break;
                    }
                }
            }
        }
    }
    public void delStreet(String locationA, String locationB){
        if(rutas.size()>0){
            for (int i=0;i<naciones.size();i++){
                if (locationA==naciones.get(i).getColor()){
                    for (int j=0;j<naciones.size();j++){
                        if (locationB==naciones.get(j).getColor()){
                            for (int k=0;k<rutas.size();k++){
                                if (rutas.get(k).getLocation()[0]==locationA && rutas.get(k).getLocation()[1]==locationB || rutas.get(k).getLocation()[1]==locationA && rutas.get(k).getLocation()[0]==locationB ){
                                    rutas.remove(k);
                                    ok=true;
                                }
                            }
                        }
                    }
                }
            }            
        }
        else{
            ok=false;
            mensaje("No hay rutas para eliminar");
        }

    }
    public void moveArmyOneRoute(String locationA, String locationB){
        for (int i=0;i<rutas.size();i++){
            if(rutas.get(i).getLocation()[0]==locationA && rutas.get(i).getLocation()[1]==locationB || rutas.get(i).getLocation()[1]==locationA && rutas.get(i).getLocation()[0]==locationB ){
                {
                    for (int j=0;j<ejercitos.size();j++){
                        if (ejercitos.get(j).getColor()==locationA){
                            putArmy(locationB);
                            delArmy(locationA);
                            ok=true;
                        }
                    }
                }
        }   
    }
    }
    private int posNac(String color){
        int flag=-1;
        for (int i=0;i<naciones.size();i++){
            if (naciones.get(i).getColor()==color){
                flag=i;
            }
        }
        return flag;
    }
    public String[] conqueredNations(){
        conq = new ArrayList();
        for (int i=0;i<naciones.size();i++){
            if (naciones.get(i).getConquer()==naciones.get(i).getArmies()){
                conq.add(naciones.get(i).getColor());
            } 
        }
        String [] cn = new String[conq.size()];
        for (int j=0; j<conq.size();j++){
            cn[j]=conq.get(j);
        }
        return cn;
    }
    public boolean conquer(){
        String[] c=conqueredNations();
        if(naciones.size()==conq.size()){
            tablero.changeColor("Magenta");
        }
        return naciones.size()==conq.size();
    }
    public void finish(){
        System.exit(0);
    }
    public boolean ok(){
        return ok;
    }
    private void mensaje(String msg){
        if (visible){
            Canvas.mostrarMensaje(msg);
        }
    }
}

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * muestra las troncales
 */
public class Troncal implements Serializable {
    //muestra el nombre
    private String nombre;

    //el tiempo que se demora en recorrer del tramo
    private double promedioRecorrido;

    //todas las estaciones que tiene la troncal
    private LinkedList<Estacion> estaciones;

    //guarda el tramo en un ArrayList del tramo seleccionado
    private ArrayList<Tramo> tramos;

}

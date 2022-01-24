/**
 * @author michael Perilla en conjunto de Ochoa Daniel
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * crea y muestra las rutas
 */
public class Ruta implements Serializable {
    //llama el nombre de la ruta
    private String nombre;

    //son todas las rutas y se guardan ArrayList
    private ArrayList<Estacion> paraEn = new ArrayList<>();

    //es el constructo de las rutas, diciendo el nombre y las paradas
    public Ruta(String nombreRuta, ArrayList<Estacion> paradas) {
        this.nombre = nombreRuta;
        this.paraEn = paradas;
    }

    //cuantas paradas hay desde la estacion de origen y la final
    public int paradas(Estacion origen, Estacion destino){
        int noOrigen = paraEn.indexOf(origen);
        int noDestino = paraEn.indexOf(destino);
        return new ArrayList<>(paraEn.subList(noOrigen, noDestino)).size();
    }

    //muestra las rutas que solo tienen la parada inicial con la final
    public boolean rutaSinParadas(Estacion origen, Estacion destino){
        return paraEn.contains(origen) && paraEn.contains(destino);
    }
}

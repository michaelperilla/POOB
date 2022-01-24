import java.io.Serializable;

/**
 * muestra el tramo que se pide
 */
public class Tramo implements Serializable {
    //muestra el nombre de la estacion de origen
    private Estacion origen;

    //muestra el nombre de la estacion de destino
    private Estacion destino;

    //muestra la distancia entre las dos estaciones
    private double distancia;
}

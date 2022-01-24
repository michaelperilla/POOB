import java.io.Serializable;

/**
 * crea la estacion y llama el nombre, si esta ocupada y el tiempo que falta
 */
public class Estacion implements Serializable {
    private String nombre;

    private String ocupacion;

    private float tiempoEspera;

    public float getTiempoEspera(){
        return tiempoEspera;
    }
}

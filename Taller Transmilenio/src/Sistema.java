/**
 * @author michael Perilla en conjunto de Ochoa Daniel
 */
import java.io.*;
import java.util.*;

/**
 * es todo el sistema completo ya usando las demas clases
 * esta seria la clase principal
 */
public class Sistema implements Serializable{

    private TreeMap<String, Estacion> estaciones = new TreeMap<>();

    private HashMap<String, Troncal> troncales = new HashMap<>();

    private HashMap<String, Ruta> rutas = new HashMap<>();

    // llama el tiempo de espera entre las estaciones
    public float tiempoEsperaEstacion(String nombre){
        return estaciones.get(nombre).getTiempoEspera();
    }

    // guarda el nombre de las estaciones en un ArrayList
    public ArrayList<String> nombresEstaciones(){
        return new ArrayList<>(estaciones.keySet());
    }

    // carga las rutas, origen y el destino para calcular si no tiene paradas
    public int noParadas(String ruta, String origen, String destino){
        Ruta rutaTomada = rutas.get(ruta);
        Estacion estacionOrigen = estaciones.get(origen);
        Estacion estacionDestino = estaciones.get(destino);
        return rutaTomada.paradas(estacionOrigen, estacionDestino);
    }

    // creal ArrayList donde se almacena las rutas sin transbordos
    public ArrayList<String> rutasSinTrasbordo(String origen, String destino){
        ArrayList<String> rutasPosibles = new ArrayList<>();
        HashMap<Ruta, Integer> paradasRutas = new HashMap<>();
        int masParadas = 0;
        Estacion estacionOrigen = estaciones.get(origen);
        Estacion estacionDestino = estaciones.get(destino);
        for(String nombreRuta : rutas.keySet()){
            Ruta ruta = rutas.get(nombreRuta);
            if(ruta.rutaSinParadas(estacionOrigen, estacionDestino)){
                int paradas = ruta.paradas(estacionOrigen, estacionDestino);
                masParadas = Math.max(paradas, masParadas);
                paradasRutas.put(ruta, paradas);
            }
        }
        int i = 0;
        while (i < masParadas){
            ArrayList<String> rutasMenores = new ArrayList<>();
            for(String nombreRuta: rutas.keySet()){
                for(Ruta ruta : paradasRutas.keySet()){
                    if(i == paradasRutas.get(ruta) && rutas.get(nombreRuta) == ruta){
                        rutasMenores.add(nombreRuta);
                    }
                }
            }
            Collections.sort(rutasMenores);
            rutasPosibles.addAll(rutasMenores);
            i++;
        }
        return rutasPosibles;
    }

    //calcula el tiempo de rutas
    public int tiempoRuta(String[][] planRuta){
        int tiempoTotal = 0;
        int estacion = 0;
        while (planRuta[estacion][1] != null){
            Estacion parada = estaciones.get(planRuta[estacion][0]);
            tiempoTotal += parada.getTiempoEspera();
            estacion++;
        }
        return tiempoTotal;
    }

    //llama origen y destino y muestra el mejor plan y llama un null
    public String[][] mejorPlan(String origen, String destino){
        return null;
    }



    /**
     * carga las rutas que estan en el archivo de texto Rutas.txt
     */
    public void cargarRuta(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Rutas.txt"));
            String nombreRuta = (String)in.readObject();
            ArrayList<Estacion> paradas = new ArrayList<>();
            String line = (String)in.readObject();
            while (line != null){
                line = line.trim();
                paradas.add(estaciones.get(line));
                line = (String) in.readObject();
            }
            rutas.put(nombreRuta, new Ruta(nombreRuta, paradas));
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    /**
     * guarda todo lo del sistema en el archivo Rutas.txt
     */
    public void guardarSistema(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Rutas.txt", true));
            // Configuracion Actual: es para saber donde esta uno
            out.writeObject("Configuracion Actual:\n");
            out.writeObject(estaciones);
            out.writeObject(troncales);
            out.writeObject(rutas);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  mira la mejor ruta para el usuario y la gurada
     * @param origen
     * @param destino
     * @throws IOException
     */
    public void guardarMejorRuta(String origen, String destino) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Rutas.txt", true));
        String[][] mejorPlan = mejorPlan(origen, destino);
        for(String[] ruta : mejorPlan){
            String step = String.join(" ", ruta);
            out.writeObject(step);
        }
        out.close();
    }

}

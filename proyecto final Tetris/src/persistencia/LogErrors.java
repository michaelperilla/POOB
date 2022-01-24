package persistencia;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase usada para escribir en el archivo TetrisPOOB.log los errores acurridos
 */
public class LogErrors {
    public static String name = "TetrisPOOB";

    /**
     * Metodo que registra el error en el log
     * @param e Excepcion que deseamos imprimir en el log
     */
    public static void regiterError(Exception e){
        try{
            Logger logger=Logger.getLogger(name);
            logger.setUseParentHandlers(false);
            FileHandler file = new FileHandler("./ErrorsLog/" + name + ".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE, e.toString(), e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}

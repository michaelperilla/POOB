package persistencia;

import dominio.TetrisMatch;
import dominio.TetrisPlayer;
import presentacion.GameBackground;
import presentacion.TetrisGUI;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase usada para leer los archivos que tenemos guardados de cada perfil
 */
public class InfoReader {

    /**
     * Metodo que retorna el historial de los mejores juegos del Tetris
     * @return String[][] en donde el primer elemento es el usuario y el segundo su maximo puntaje
     */
    public static ArrayList<String[]> getLeaderBoard(){
        ArrayList<String[]> leaderboard = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./GameData/Leaderboard.txt"));
            String userPoints = reader.readLine();
            while (userPoints != null){
                userPoints = userPoints.trim();
                String[] userPointsList = userPoints.split(" ");
                leaderboard.add(userPointsList);
                userPoints = reader.readLine();
            }
        } catch (IOException ignored){}
        return leaderboard;
    }

    /**
     * Metodo que valida que los datos ingresados por el usuario sean correctos y le permita hacer el login
     * @param user Nombre ingresado por el usuario
     * @param password Constraseña ingresada por el usuario
     * @return true si los datos son correctos, false de lo contrario
     */
    public static boolean validateLogin(String user, String password){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./GameData/UsersPasswords.txt"));
            String line = reader.readLine();
            while (line != null){
                line = line.trim();
                String[] userPasswords = line.split(" ");
                if(userPasswords[0].equalsIgnoreCase(user) && userPasswords[1].equals(password)){
                    return true;
                }
                line = reader.readLine();
            }
        } catch (IOException ignored){}
        return false;
    }

    /**
     * Metodo que obtiene los datos importantes de los usuarios guardados en archivos de texto
     * @param user Nombre del usuario del que queremos retomar sus datos
     * @param path Camino al archivo del que tomaremos los datos
     * @return String[] en el que estan listados los datos del usuario actualmente ingresado, retornara una lista vacia
     * si lo no hay ningun usuario ingresado
     */
    public static String[] getUserInfo(String user, String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./GameData/" + path));
            String line = reader.readLine();
            while (line != null){
                line = line.trim();
                String[] userInfo = line.split(" ");
                if(userInfo[0].equalsIgnoreCase(user)){
                    return userInfo;
                }
                line = reader.readLine();
            }
        } catch (IOException ignored){}
        return null;
    }

    /**
     * Metodo que abre juegos guardados anteriormente del usuario que ingreso al juego
     * @param match Directorio que contiene los datos necesarios para abrir la partida
     * @return GameBackground Objeto que se usa para representar graficamente la partida
     */
    public static GameBackground openMatch(File match){
        GameBackground game = null;

        try{
            BufferedReader reader = new BufferedReader(new FileReader("./GameData/" + TetrisGUI.User.toLowerCase() + "/" + match.getName() + "/" + match.getName() + ".txt"));
            String line = reader.readLine();
            line = line.trim();
            String[] matchInfo = line.split(" ");
            game = new GameBackground(Integer.parseInt(matchInfo[0]), Integer.parseInt(matchInfo[1]), matchInfo[2], matchInfo[3]);
            reader.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("./GameData/" + TetrisGUI.User.toLowerCase() + "/" + match.getName() + "/" + match.getName() + ".dat"));
            TetrisMatch newMatch = (TetrisMatch) in.readObject();
            newMatch.resume();
            game.setMatch(newMatch);
            in.close();
        } catch (IOException | ClassNotFoundException ignored){
            ignored.printStackTrace();
        }
        return game;
    }
}

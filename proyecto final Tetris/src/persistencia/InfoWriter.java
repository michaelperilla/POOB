package persistencia;

import dominio.TetrisMatch;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Clase que se usa para guardar los datos importantes de la aplicacion
 */
public class InfoWriter {

    /**
     * Metodo que guarda el color de un jugador en un archivo de texto UsersColors.txt
     * @param nickname Nombre del usuario que ingreso a la aplicacion
     * @param newColor Color que se le guardara al usuario
     */
    public static void saveColor(String nickname, Color newColor){
        try{
            ArrayList<String> userColorContent = new ArrayList<>(Files.readAllLines(Path.of("./GameData/UsersColors.txt"), StandardCharsets.UTF_8));
            for(int i = 0; i < userColorContent.size(); i++){
                if (userColorContent.get(i).trim().split(" ")[0].equalsIgnoreCase(nickname)){
                    int r = newColor.getRed();
                    int g = newColor.getGreen();
                    int b = newColor.getBlue();
                    userColorContent.set(i, nickname + " " + r + " " + g + " " + b);
                }
            }
            Files.write(Path.of("./GameData/UsersColors.txt"), userColorContent, StandardCharsets.UTF_8);
        } catch (IOException ignored){}
    }

    /**
     * Metodo que guarda en los archivos de texto el nuevo record de un jugador
     * @param nickname Nombre del usuario que ingreso en la aplicacion
     * @param points Puntos de la mejor partida del jugador
     */
    public static void newRecord(String nickname, int points){
        try{
            ArrayList<String> usersPoints = new ArrayList<>(Files.readAllLines(Path.of("./GameData/UsersTopScores.txt"), StandardCharsets.UTF_8));
            ArrayList<String> leaderboard = new ArrayList<>(Files.readAllLines(Path.of("./GameData/Leaderboard.txt"), StandardCharsets.UTF_8));
            for(int i = 0; i < usersPoints.size(); i++){
                if(usersPoints.get(i).trim().split(" ")[0].equalsIgnoreCase(nickname)){
                    usersPoints.set(i, nickname + " " + points);
                }
            }
            for(int i = 0; i < leaderboard.size(); i++){
                if(leaderboard.get(i).trim().split(" ")[0].equalsIgnoreCase(nickname)){
                    leaderboard.set(i, nickname + " " + points);
                }
            }
            Files.write(Path.of("./GameData/UsersTopScores.txt"), usersPoints, StandardCharsets.UTF_8);
            Files.write(Path.of("./GameData/Leaderboard.txt"), leaderboard, StandardCharsets.UTF_8);
        } catch (IOException ignored){}
    }

    /**
     * Metodo que guarda el objeto de la partida en un archivo .dat
     * @param match Objeto TetrisMatch que queremos guardar en el archivo
     * @param nickname Nombre del jugador que ingreso para guardar la partida
     * @param matchParams Nombre del directorio en el que se guardara la partda
     */
    public static void saveMatch(TetrisMatch match, String nickname, File matchParams){
        try{
            Files.createDirectories(Path.of("./GameData/" + nickname.toLowerCase() + "/" + matchParams.getName() + "/"));
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("./GameData/" + nickname.toLowerCase() + "/" + matchParams.getName() + "/" + matchParams.getName() + ".txt"));
            printWriter.println(match.getTetrisGames() + " " + match.getPcPlayers() + " " + match.getType() + " " + match.getPcExperience());
            printWriter.close();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./GameData/" + nickname.toLowerCase() + "/" + matchParams.getName() + "/" + matchParams.getName() + ".dat"));
            objectOutputStream.writeObject(match);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ignored){}

    }
}

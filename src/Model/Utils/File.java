package Model.Utils;

import Model.Entities.Player;

import java.io.*;
import java.util.ArrayList;

public class File {
    public static void addNewGame(Player winner, Player looser){
        try{
            FileWriter fw = new FileWriter("src/Model/Data/Game.data", true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(winner.getData() + ", " + looser.getData() + '\n');
            writer.close();

        } catch (IOException e) {
            System.out.println("Game.data not founded");
        }
    }
    public static ArrayList<String[]> getLastGames(){
        ArrayList<String[]> fin = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/Model/Data/Game.data"))) {
            String line;
            String[] spl = new String[6];
            while ((line = br.readLine()) != null)
                fin.add(line.split(", "));

        } catch (Exception e) {
            System.out.println("Game.data not founded");
        }
        return fin;
    }


    public static void SaveSetting(String player1Name, String player2Name, int CueStyle, int volume){
        try{
            FileWriter fw = new FileWriter("src/Model/Data/Setting.data", false);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(player1Name + ", " + player2Name + ", " +
                    String.valueOf(CueStyle) + ", " + String.valueOf(volume));
            writer.close();

        } catch (IOException e) {
            System.out.println("Setting.data not founded");
        }
    }

    public static String[] readSetting(){
        String[] line = new String[4];
        try (BufferedReader br = new BufferedReader(new FileReader("src/Model/Data/Setting.data"))) {
            line = br.readLine().split(", ");

        } catch (Exception e) {
            System.out.println("Setting.data not founded");
        }
        return line;
    }
}

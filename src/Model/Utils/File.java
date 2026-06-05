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
}

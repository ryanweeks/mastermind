package mastermind.backend;

import javafx.util.Pair;
import mastermind.gui.GUI;
import mastermind.gui.GameScreen;

import java.io.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private String filename;
    private int gamesPlayed;
    private int gamesWon;
    private int highScore;

    public Player(String name) {
        File f = new File(name);
        if(f.exists()){
            this.filename = name;
            loadStats();
        } else{
            this.name = name;
            this.filename = "./" + name + "_stats.txt";
            this.gamesPlayed = 0;
            this.gamesWon = 0;
            this.highScore = 100;
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void saveStats(){
        /* The files for players are going to be stored as such exmp
        filename: "$name_stats.txt"
        content:
        $name
        $gamesPlayed
        $gamesWon
        $highScore
         */
        try {
            FileOutputStream fs = new FileOutputStream(this.filename);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fs));

            writer.write(name + "\n");
            writer.write(Integer.toString(gamesPlayed) + "\n");
            writer.write(Integer.toString(gamesWon) + "\n");
            writer.write(Integer.toString(highScore) + "\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStats(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            int n = lines.size();
            if (n == 4) { // should only be 4 lines in a file
                for (int i = 0; i < n; i++) {
                    String val = lines.get(i);
                    switch (i) {
                        case 0:
                            this.name = val;
                            break;
                        case 1:
                            this.gamesPlayed = Integer.parseInt(val);
                            break;
                        case 2:
                            this.gamesWon = Integer.parseInt(val);
                            break;
                        case 3:
                            this.highScore = Integer.parseInt(val);
                            break;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void refreshStats(Pair<Boolean, Integer> results){
        if(results.getKey()){
            gamesWon ++;

            if (results.getValue() < highScore){
                highScore = results.getValue();
            }

        }
        gamesPlayed ++;
    }

    public Boolean compare(Player player2){
        double winRatio1 = (double)(this.gamesWon/this.gamesPlayed);
        double winRatio2 = (double)(player2.gamesWon/player2.gamesPlayed);

        double winWeight = 0.7;
        double scoreWeight = 0.3;

        // Because high score is actually a low score, - 10 is max amt of turns
        double highScore1 = 10 - this.highScore;
        double highScore2 = 10 - this.highScore;

        double totalScore1 = (winRatio1 * winWeight) + (highScore1 * scoreWeight);
        double totalScore2 = (winRatio2 * winWeight) + (highScore2 * scoreWeight);

        return totalScore1 > totalScore2;
    }

    public void playGui(){
        new GameScreen(this);
    }

    public void playGame(){
        Pair<Boolean, Integer> results = new Game().play(); // Will play a new game
        refreshStats(results);
    }

    public void simulateGame(){
        Pair<Boolean, Integer> results = new Game().simulate();
        refreshStats(results);
    }
}

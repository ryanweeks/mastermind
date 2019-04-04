package mastermind.backend;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javafx.util.Pair;

import static java.awt.Color.*;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;

public class Game {
    public final static Color[] colors = {RED, BLUE, YELLOW, GREEN, ORANGE, MAGENTA};
    public final static String[] colorStrings = {"Red", "Blue", "Yellow", "Green", "Orange", "Purple"};

    public Color[] pegs;
    public Color[] tempPegs;

    public Game() {
        this.pegs = getRandomArrangement();
    }

    public void printColor(Color c){
        String toPrint = "";
        for (int i = 0; i < 6; i ++) {
            if (c == colors[i]){
                toPrint = colorStrings[i];
                break;
            } else if (c == BLACK) {
                toPrint = "Black";
            } else if (c == WHITE) {
                toPrint = "White";
            }
        }
        System.out.println(toPrint);
    }


    public Color[] getRandomArrangement(){
        Color[] randomColors = new Color[4];

        //creates pegs
        for (int i = 0; i < 4; i++){
            randomColors[i] = colors[new Random().nextInt(6)];
        }

        return randomColors;
    }

    public Color toColor(String color) {
        for (int i = 0; i < 6; i++){
            if(color.equals(colorStrings[i])){
                return colors[i];
            }
        }
        return BLACK;
    }

    public Boolean checkWin(ArrayList<Color> markers){
        int n = markers.size();

        if (n == 4) {
            for (int i = 0; i < 4; i++) {
                if (markers.get(i) != BLACK) { // If 1 marker was not black then player did not win
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public ArrayList<Color> checkGuesses(Color[] userGuesses) {
        tempPegs = Arrays.copyOf(this.pegs, 4); // Does before every turn checked
        ArrayList<Color> guesses = new ArrayList<>();

        for (int i = 0; i < 4; i ++) {
            if (userGuesses[i] == this.tempPegs[i]) { // first check black
                guesses.add(BLACK);
                this.tempPegs[i] = BLACK; // unused color
            }
        }

        for (int i = 0; i < 4; i ++) {
            if (Arrays.asList(this.tempPegs).contains(userGuesses[i])) { // then check white
                guesses.add(WHITE);
                int index = -1;
                for (int j = 0; j < 4; j ++){
                    if(this.tempPegs[j] == userGuesses[i]){
                        index = j;
                    }
                }
                this.tempPegs[index] = BLACK; // unused color
            }
        }

        // Sorts arraylist by black first - BubbleSort
        int n = guesses.size();
        for (int i = 0; i < n - 1; i ++){
            for (int j = 0; j < n - i - 1; j ++){
                if(guesses.get(j) == WHITE && guesses.get(j+1) == BLACK){ // Moves black pegs to left
                    Color temp = guesses.get(j);
                    guesses.set(j, guesses.get(j+1));
                    guesses.set(j+1, temp);
                }
            }
        }

        return guesses;
    }

    public Pair<Boolean, Integer> play(){ // True for win, false for loss
        this.pegs = getRandomArrangement();
        System.out.println("Welcome to Mastermind");
        Scanner userInput = new Scanner(System.in);
        ArrayList<Color> markers = new ArrayList<Color>();
        Boolean won = false;
        int score = 0;

        for(int i = 0; i < 10; i++){
            System.out.println("Turn: " + i);
            Color[] guesses = new Color[4];
            for(int j = 0; j < 4; j++) {
                System.out.println("Guess: " + j);
                System.out.println("Choose a color:\n(0) Red\n(1) Blue\n(2) Yellow\n(3) Green\n(4) Orange\n(5) Purple\n(6) Exit game");


                int option = userInput.nextInt();
                switch(option) {
                    case 0:
                        guesses[j] = Color.RED;
                        break;
                    case 1:
                        guesses[j] = Color.BLUE;
                        break;
                    case 2:
                        guesses[j] = Color.YELLOW;
                        break;
                    case 3:
                        guesses[j] = Color.GREEN;
                        break;
                    case 4:
                        guesses[j] = Color.ORANGE;
                        break;
                    case 5:
                        guesses[j] = Color.MAGENTA;
                        break;
                    case 6:
                        return new Pair<Boolean, Integer>(false, score);
                    default:
                        System.out.println("Bad input. Try Again");
                        j--;
                        break;
                }
            }
            markers = checkGuesses(guesses);
            if(checkWin(markers)){
                won = true;
                score = i+1;
                break;
            }
            int n = markers.size();

            System.out.println("These were your guesses: ");
            for (int j = 0; j < 4; j ++){
                printColor(guesses[j]);
            }

            System.out.println("Your guesses resulted in the following markers:");
            for (int j = 0; j < n; j ++){
                printColor(markers.get(j));
            }
        }

        userInput.close();

        if(won){
            System.out.println("You Won!");
        } else {
            System.out.println("You Lost!");
            System.out.println("The Correct Color Sequence was: ");
            for(int i = 0; i < 4; i ++){
                printColor(this.pegs[i]);
            }
        }

        Pair<Boolean, Integer> results = new Pair<Boolean, Integer>(won, score);
        return results;
    }

    public Pair<Boolean, Integer> simulate(){
        this.pegs = getRandomArrangement();
        ArrayList<Color> markers = new ArrayList<Color>();
        Boolean won = false;
        int score = 0;

        for(int i = 0; i < 10; i++){
            Color[] guesses = new Color[4];
            for(int j = 0; j < 4; j++) {
                guesses[j] = colors[new Random().nextInt(6)];
            }
            markers = checkGuesses(guesses);
            if(checkWin(markers)){
                won = true;
                score = i+1;
                break;
            }
        }

        Pair<Boolean, Integer> results = new Pair<Boolean, Integer>(won, score);
        return results;
    }
}

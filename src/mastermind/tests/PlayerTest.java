package mastermind.tests;

import static org.junit.jupiter.api.Assertions.*;

import mastermind.backend.Player;
import mastermind.gui.TitleScreen;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    static Player player1 = new Player("Ryan");
    static Player player2 = new Player("Tester");

    @Test
    void TestGetPlayerGamesPlayed() {
        player1.simulateGame();
        assertEquals(1, player1.getGamesPlayed());
    }

    @Test
    void TestPlayerStatSavingLoading() {
        String filename = "ryan_stats.txt";
        player1.setFilename(filename);
        player1.setGamesPlayed(20);
        player1.setGamesWon(15);
        player1.setHighScore(3);
        player1.saveStats();
        player2.setFilename(filename); // need to set filename first
        player2.loadStats();
        assertEquals("Ryan", player2.getName());
        assertEquals(20, player2.getGamesPlayed());
        assertEquals(15, player2.getGamesWon());
        assertEquals(3, player2.getHighScore());
    }

    @Test
    void TestPlayerCompare(){
        // Algorithm for comparing players
        // - Player that wins a lot is better than player with the highest score
        // - Player could have just gotten lucky on one turn
        // winRatioWeight = 0.7
        // highScoreWeight = 0.3
        // totalScore = (winRatio * winRatioWeight) + (highScore * highScoreWeight)

        player1.setName("Tester1");
        player1.setGamesPlayed(15);
        player1.setGamesWon(7);
        player1.setHighScore(3);

        player2.setName("Tester2");
        player2.setGamesPlayed(15);
        player2.setGamesWon(12);
        player2.setHighScore(7);

        assertEquals(false, player1.compare(player2));
    }

    @Test
    void TestGUICreatePlayer() throws InterruptedException {
        /*TitleScreen t = new TitleScreen(); // Runs the GUI
        t.newGame.doClick();
        Thread.sleep(500);
        t.dispose();*/

        TitleScreen t = new TitleScreen();
        t.userName.setText("Evan");
        t.createUser.doClick();
        assertEquals("Evan", t.userTitle.getText());
        t.dispose();
    }

    @Test
    void TestGUILoadPlayer() throws InterruptedException {
        /*TitleScreen t = new TitleScreen(); // Runs the GUI
        t.instructions.doClick();
        Thread.sleep(500);
        t.dispose();*/

        TitleScreen t = new TitleScreen();
        t.userName.setText("Evan");
        t.loginUser.doClick();
        assertEquals("Evan", t.userTitle.getText());
        t.dispose();
    }
}

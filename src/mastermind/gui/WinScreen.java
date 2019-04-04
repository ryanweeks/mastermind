package mastermind.gui;
import javafx.util.Pair;
import mastermind.backend.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class WinScreen extends JFrame {
	private JPanel main; //main screen
	private JPanel buttons; //panel to hold buttons
	private JPanel titleFrame; //frame for win Jlabel
	private JPanel scoreBoard; //panel to hold score
	
	private JLabel win; //winning text
	private JLabel scoreL; //shows the score of the game played
	private JLabel blank1; //series of blank labels to be used as spacing
	private JLabel blank2;
	private JLabel blank3;
	private JButton menu; //button to go back to menu
	private JButton newGame; //button to start a new game
	private Player p;
	
	
	public WinScreen(Player p){
		this.p = p;
		setTitle("Mastermind");
		setDefaultCloseOperation(EXIT_ON_CLOSE); //sets default close operation
		buildPanel(); //calls buildPanel method to build panel
		add(main); //adds main panel onto screen
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	public void buildPanel(){
		main = new JPanel(); //makes the main panel
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS)); //makes panel have a box layout in y axis
		main.setBackground(Color.BLACK); //makes black background
		buttons = new JPanel(); //makes the panel to hold buttons
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); //makes panel have a box layout in x axis
		buttons.setBackground(Color.BLACK); //makes black background
		titleFrame = new JPanel(); //makes the title Frame panel to hold win label
		titleFrame.setBackground(Color.BLACK); //makes black background
		scoreBoard = new JPanel(); //makes the score board panel to hold the score label
		scoreBoard.setBackground(Color.BLACK); //makes black background
		
		//makes three blank labels for Spacing purposes
		blank1 = new JLabel("1"); //used for space above the title
		blank1.setFont(new Font("Arial", Font.PLAIN, 180)); //makes huge
		blank1.setForeground(Color.BLACK); // makes it blend in with background
		blank2 = new JLabel("111111111111111111"); //used for space between buttons
		blank2.setForeground(Color.BLACK); //makes it blend in with background
		blank3 = new JLabel("1"); //used for space below buttons
		blank3.setFont(new Font("Arial", Font.PLAIN, 180)); //makes huge
		blank3.setForeground(Color.BLACK); // makes it blend in with background
		
		//main label
		win = new JLabel("You Win!"); //makes label to say You Win!
		win.setFont(new Font("Felix Titling", Font.BOLD, 76)); //changes font of winning phrase
		win.setForeground(Color.LIGHT_GRAY); //makes the text light gray
		
		//score label	
		scoreL = new JLabel("Your score is: " + GameScreen.score);
		scoreL.setFont(new Font("Felix Titling", Font.PLAIN, 32)); //changes font for score label
		scoreL.setForeground(Color.LIGHT_GRAY); //makes the text light gray
		
		titleFrame.add(win); //adds label to title frame
		scoreBoard.add(scoreL); //adds label to score board
	
		//makes buttons for the buttons panel
		newGame = new JButton("New Game"); //new game button
		newGame.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		newGame.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		menu = new JButton("Menu"); //menu button
		menu.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		menu.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		
		newGame.addActionListener(new newGameL()); //adds action listener to new game button
		menu.addActionListener(new menuL()); //adds action listener to menu button
		
		//adds objects into buttons panel
		buttons.add(newGame); //new game button
		buttons.add(blank2); //space between button
		buttons.add(menu); //menu button
		
		//adds objects into main panel
		main.add(blank1); //space above heading
		main.add(titleFrame); //heading
		main.add(scoreBoard); //score
		main.add(buttons); //buttons
		main.add(blank3); //space below buttons

        Pair<Boolean, Integer> results = new Pair<Boolean, Integer>(true, GameScreen.score);
        p.refreshStats(results);
        p.saveStats();
	}
	public class newGameL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		Player p1 = p;
		dispose(); //exits screen
		new GameScreen(p1); //starts up game window
		}
	}
	
	public class menuL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose(); //exits screen
			new TitleScreen(); //goes back to title screen
		}
	}
}

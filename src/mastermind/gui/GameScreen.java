package mastermind.gui;

import mastermind.backend.Game;
import mastermind.backend.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/*
Note: This was a 3000 line document I had originally written in High School
Edited: By Ryan Weeks
	Date: 3/27/2019
 */

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	public static int score = 0; //score of the game
	//makes string array of what will be stored in each combo box
	
	//makes all features for the game window
	private JPanel main; //main panel
	private JPanel turns; //panel to hold turns
	private JLabel blank1; //blank label used for spacing

	private JPanel[] turnPanels; //panels to hold combo boxes
	private JLabel[] turnLabels; //labels for the titles of each panel
	private JComboBox[][] turnBoxes; //makes indvidual boxes for each turn
	private JPanel[] markerHolderPanels; //makes panels to hold panel markers(white/black markers)
	private JPanel[][] markerPanels; //makes panels to be used as markers
	private JButton[] turnSubmit; //makes buttons for each turn to be clicked when done

	private Game game;
	private Player p;

	public GameScreen(Player p){
		this.p = p;
		game = new Game();
		//pegs = game.pegs;

		setTitle("Mastermind"); //sets title of window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //sets default close operation
		buildScreen(); //calls the method buildScreen to build the screen
		add(main); //adds the main panel onto the frame
		setExtendedState(MAXIMIZED_BOTH); //maximizes screen
		setVisible(true); //makes the screen visible
	}
	
	public void buildScreen(){
		main = new JPanel(); //makes main panel
		main.setBackground(Color.WHITE); //makes white background
		
		blank1 = new JLabel("1"); //used for space above the title
		blank1.setFont(new Font("Arial", Font.PLAIN, 230)); //makes huge
		blank1.setForeground(Color.WHITE); // makes it blend in with background
		
		turns = new JPanel(); //makes turn panel
		makeTurnPanels(); //calls make turn panels method to make turn panels
		makeHeadings(); //calls makeHeadings method to make to make headings
		makeComboBoxes(); //calls method to make combo boxes
		makeMarkerHolders(); //calls method to make markerholders
		makeMarkers(); //calls markers method to make markers
		addMarkers(); //calls method to add markers to markerholders
		makeButtons(); //calls method to make buttons
		addToTurn(); //calls method that adds objects to the turn panels
		addTurns(); //calls method that adds turn panels to the turns panel
		setDisabled(); //calls method that disables objects for other turns

		//adds everything to main panel
		main.add(blank1);
		main.add(turns); //turns panel
	}
	
	public void makeTurnPanels(){
		//makes panels for each turn
		turnPanels = new JPanel[10];
				
		//makes turns have box layout in y axis
		for (int i = 0; i < 10; i++){
			turnPanels[i] = new JPanel();
			turnPanels[i].setLayout(new BoxLayout(turnPanels[i], BoxLayout.Y_AXIS)); //panel to hold combo boxes for 1st turn
			turnPanels[i].setAlignmentX(CENTER_ALIGNMENT);
			turnPanels[i].setAlignmentY(CENTER_ALIGNMENT);
			turnPanels[i].setBackground(Color.WHITE);
		}
	}
	
	
	public void makeHeadings(){
		//makes the headings for each turn
		turnLabels = new JLabel[10];

		for (int i = 0; i < 10; i++) {
			turnLabels[i] = new JLabel("Turn " + i);
		}
	}
	
	
	public void makeComboBoxes(){
		//makes combo boxes to contain the string of colors for items
		turnBoxes = new JComboBox[10][4];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++){
				turnBoxes[i][j] = new JComboBox(Game.colorStrings);
			}
		}
	}
	
	
	public void makeMarkerHolders(){
		//makes panels for marker holders for each turn
		markerHolderPanels = new JPanel[10];

		for (int i = 0; i < 10; i++){
			markerHolderPanels[i] = new JPanel();
			markerHolderPanels[i].setBackground(Color.GRAY);
		}
	}
	
	
	public void makeMarkers(){
		//makes panels to be used as markers
		markerPanels = new JPanel[10][4];

		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 4; j++){
				markerPanels[i][j] = new JPanel(); // marker1_1 = new JPanel(); //1st marker for 1st box
				markerPanels[i][j].setBackground(Color.GRAY); //makes all markers have gray backgrounds so it blends in with marker holders
			}
		}
	}
	
	
	public void addMarkers(){
		//adds markers to marker holders
		for (int i = 0; i < 10; i ++){
			for (int j = 0; j < 4; j++) {
				markerHolderPanels[i].add(markerPanels[i][j]);
			}
		}
	}
	
	
	public void makeButtons(){
		//makes buttons for each turn to be clicked when done
		turnSubmit = new JButton[10];

		for (int i = 0; i < 10; i++){
			turnSubmit[i] = new JButton("Done");
			turnSubmit[i].setActionCommand(Integer.toString(i));
			turnSubmit[i].addActionListener(new submitListener());
		}
	}	

	public void addToTurn(){
		for(int i = 0; i < 10; i++){
			turnPanels[i].add(turnLabels[i]); //adds headings to each turn panel
			for (int j = 0; j < 4; j++){
				turnPanels[i].add(turnBoxes[i][j]); // adds Combo boxes to each turn panel
			}
			turnPanels[i].add(turnSubmit[i]); //adds buttons to each turn panel
			turnPanels[i].add(markerHolderPanels[i]); //adds markerholders into each turn panel
		}
	}
	
	
	public void addTurns(){
		//adds each turn panel into main panel
		for (int i = 0; i < 10; i ++){
			turns.add(turnPanels[i]);
		}
	}
	
	
	public void setDisabled(){
		for (int i = 1; i < 10; i++){
			for (int j = 0; j < 4; j++){
				turnBoxes[i][j].setEnabled(false); //sets all combo boxes 2nd turn and beyond to be disabled at start
			}
			turnSubmit[i].setEnabled(false); //sets all buttons for 2nd turn and beyond to be disabled at start
		}
	}
	
	//all action listeners for the buttons (one for each button)

	private class submitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = Integer.parseInt(e.getActionCommand()); // which turn associated with button

			score ++;

			//make a string to compare users guesses to the computers
			Color[] userGuesses = new Color[4];
			for (int i = 0; i < 4; i++) {
				userGuesses[i] = game.toColor(turnBoxes[index][i].getSelectedItem().toString());
			}

			//changes colors of combo boxes to match color selected
			for (int i = 0; i < 4; i++) {
				turnBoxes[index][i].setBackground(userGuesses[i]);
			}

			ArrayList<Color> markers = game.checkGuesses(userGuesses);

			// Sets markers to correct colors
			for (int i = 0; i < markers.size(); i++) {
				markerPanels[index][i].setBackground(markers.get(i));
			}

			for (int i = 0; i < 4; i++) {
				turnBoxes[index + 1][i].setEnabled(true); // Enables boxes for next turn
				turnBoxes[index][i].setEnabled(false); // Disables boxes for this turn
			}

			turnSubmit[index + 1].setEnabled(true); // Enables submit button for next turn
			turnSubmit[index].setEnabled(false); // Disables submit button for this turn

			Player p1 = p;
			if (game.checkWin(markers)) {
				dispose(); //exits screen
				new WinScreen(p1); //makes a new winning screen window
			} else {
				if (index == 9) { // last turn
					dispose();
					new GameOverScreen(game, p1);
				}
			}
		}
	}
}
//END GAME
package mastermind.gui;

import mastermind.backend.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class InstructionScreen extends JFrame {
	//makes features for screen
	private JPanel main; //main panel
	private JPanel instructions; //panel for labels holding instructions
	private JPanel buttons; //panel for the buttons for the screen
	private JLabel title; //label for the title
	private JLabel instruct1; //series of labels for instructions
	private JLabel instruct2;
	private JLabel instruct3;
	private JLabel instruct4;
	private JLabel instruct5;
	private JLabel blank1; //series of labels to be used as spacing
	private JLabel blank2;
	private JLabel blank3;
	private JLabel blank4;
	private JButton newGame; //button for new Game
	private JButton goBack; //button to go back to main window
	private Player p;
	
	public InstructionScreen(Player p){
		this.p = p;
		setTitle("Mastermind"); //sets the title of the frame
		setDefaultCloseOperation(EXIT_ON_CLOSE); //sets the default close operation for the frame
		buildScreen(); //calls method to build the screen
		add(main); //adds panel to the frame
		setExtendedState(MAXIMIZED_BOTH); //maximizes screen
		setVisible(true); //makes frame visible
	}
	public void buildScreen(){ //builds the screen
		main = new JPanel(); //makes panel
		main.setBackground(Color.BLACK); //make panel have a black background
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS)); //box layout in y axis for main panel
		instructions = new JPanel(); //makes instruction panel
		instructions.setBackground(Color.BLACK); //makes black background
		buttons = new JPanel(); //makes buttons panel
		buttons.setBackground(Color.BLACK); //make panel have a black background
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); //box layout along x axis for button panel
		
		//makes title
		title = new JLabel("Instructions"); //says instructions
		title.setForeground(Color.LIGHT_GRAY); //changes color
		title.setFont(new Font("Felix Titling", Font.BOLD, 76));
		
		//makes instruction labels
		instruct1 = new JLabel("1) The computer will randomly select an arrangment of 4 pegs from the 6 possible colors."); //instructions
		instruct1.setForeground(Color.LIGHT_GRAY); //changes text color
		instruct1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24)); //changes font
		instruct2 = new JLabel("2) You will then be able to guess the arrangment of pegs."); //instructions
		instruct2.setForeground(Color.LIGHT_GRAY); //changes text color
		instruct2.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24)); //changes font
		instruct3 = new JLabel("3) If the correct colored peg is in the correct position, a black marker will be shown."); //instructions
		instruct3.setForeground(Color.LIGHT_GRAY); //changes text color
		instruct3.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24)); //changes font
		instruct4 = new JLabel("4) If the correct colored peg is in an incorrect position, a white marker will be shown."); //instructions
		instruct4.setForeground(Color.LIGHT_GRAY); //changes text color
		instruct4.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24)); //changes font
		instruct5 = new JLabel("5) Use logic and other strategies to guess the correct arrangment within 10 turns to win."); //instructions
		instruct5.setForeground(Color.LIGHT_GRAY); //changes text color
		instruct5.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24)); //changes font
		
		//makes three blank labels for Spacing purposes
		blank1 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); //used for space above the title
		blank1.setFont(new Font("Arial", Font.PLAIN, 90)); //makes huge
		blank1.setForeground(Color.BLACK); // makes it blend in with background
		blank2 = new JLabel("111111111111111111"); //used for space between buttons
		blank2.setForeground(Color.BLACK); //makes it blend in with background
		blank3 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); //used for space below buttons
		blank3.setFont(new Font("Arial", Font.PLAIN, 90)); //makes huge
		blank3.setForeground(Color.BLACK); // makes it blend in with background
		blank4 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); //used for space between title and instructions labels
		blank4.setFont(new Font("Arial", Font.PLAIN, 45)); //makes huge
		blank4.setForeground(Color.BLACK); // makes it blend in with background
		
		//adds labels to instruction panel
		instructions.add(blank1);//spacing above title
		instructions.add(title);
		instructions.add(blank4); //spacing between title and instructions
		instructions.add(instruct1);
		instructions.add(instruct2);
		instructions.add(instruct3);
		instructions.add(instruct4);
		instructions.add(instruct5);
		
		
		//makes buttons
		newGame = new JButton("New Game"); //new game button
		newGame.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		newGame.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		goBack = new JButton("Go Back"); //go back button
		goBack.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		goBack.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		
		//adds action listeners to buttons
		newGame.addActionListener(new newGameL());
		goBack.addActionListener(new goBackL());
		
		//adds buttons to buttons panel
		buttons.add(newGame);
		buttons.add(blank2); //spacing between buttons
		buttons.add(goBack);
		
		//adds panels to main
		main.add(instructions);
		main.add(buttons);
		main.add(blank3); //spacing under buttons
		
	}
	public class newGameL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Player p1 = p;
			dispose(); //exits screen
			new GameScreen(p1); //starts up game window
		}
	}
	
	public class goBackL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose(); //exits screen
			new TitleScreen(); //goes back to title screen
		}
	}
	
}

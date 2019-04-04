package mastermind.gui;

import mastermind.backend.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TitleScreen extends JFrame{
	//creates features needed for title screen
	public JPanel main; //main panel
	public JPanel buttons; //panel to hold buttons
	public JPanel titleFrame; //panel to hold title
	public JPanel userTitleFrame;
	public JPanel userNameFrame;
	public JButton newGame; //button to make a new game
	public JButton instructions; //button to view instructions
	public JLabel title; //label for the title of the game
	public JTextField userName; // Textfield to allow user to set name
	public JButton createUser;
	public JButton loginUser;
	public JLabel userTitle;
	public JLabel userNameLabel; // label for user's name
	public JLabel blank1; //label for spacing
	public JLabel blank2; //Label for spacing
	public JLabel blank3; //label for spacing
	Player p;
	
	public TitleScreen(){
		setTitle("Mastermind");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildScreen(); //calls method to build the screen
		add(main); //adds panel to frame
		setExtendedState(MAXIMIZED_BOTH); //Maximizes the screen
		setVisible(true); //makes the frame visible
	}

	private void buildScreen(){
		//makes panels
		main = new JPanel(); //makes panel for main
		main.setBackground(Color.BLACK); //make panel have a black background
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS)); //box layout in y axis for main panel
		buttons = new JPanel(); //makes panel for all buttons
		buttons.setBackground(Color.BLACK); //make panel have a black background
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); //box layout along x axis for button panel
		titleFrame = new JPanel(); //makes panel to hold title (centering purposes)
		titleFrame.setBackground(Color.BLACK); //make panel have a black background
	
		//makes three blank labels for Spacing purposes
		blank1 = new JLabel("1"); //used for space above the title
		blank1.setFont(new Font("Arial", Font.PLAIN, 180)); //makes huge
		blank1.setForeground(Color.BLACK); // makes it blend in with background
		blank2 = new JLabel("111111111111111111"); //used for space between buttons
		blank2.setForeground(Color.BLACK); //makes it blend in with background
		blank3 = new JLabel("1"); //used for space below buttons
		blank3.setFont(new Font("Arial", Font.PLAIN, 180)); //makes huge
		blank3.setForeground(Color.BLACK); // makes it blend in with background
		
		//makes the title
		title = new JLabel("Mastermind"); //makes the label
		title.setFont(new Font("Felix Titling", Font.BOLD, 76)); //sets font and font size
		title.setForeground(Color.LIGHT_GRAY); //makes light gray text

		userTitle = new JLabel("Enter Player Name: ");
		userTitle.setFont(new Font("Felix Titling", Font.BOLD, 38)); //sets font and font size
		userTitle.setForeground(Color.LIGHT_GRAY); //makes light gray text
		
		titleFrame.add(title); //adds the title to the title frame

		userTitleFrame = new JPanel();
		userTitleFrame.setBackground(Color.BLACK); //make panel have a black background

		userTitle = new JLabel("");
		userTitle.setFont(new Font("Felix Titling", Font.BOLD, 20)); //sets font and font size
		userTitle.setForeground(Color.LIGHT_GRAY); //makes light gray text

		userTitleFrame.add(userTitle);

		userNameFrame = new JPanel();
		userNameFrame.setBackground(Color.BLACK); //make panel have a black background

		userName = new JTextField("", 5);
		userNameLabel = new JLabel("Enter player name");
		userNameLabel.setFont(new Font("Felix Titling", Font.BOLD, 20)); //sets font and font size
		userNameLabel.setForeground(Color.LIGHT_GRAY); //makes light gray text
		userNameFrame.add(userNameLabel);
		userNameFrame.add(userName);
		
		//makes buttons for the buttons panel
		newGame = new JButton("New Game"); //new game button
		newGame.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		newGame.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		instructions = new JButton("Instructions"); //instruction button
		instructions.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		instructions.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		createUser = new JButton("Create User"); //new game button
		createUser.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		createUser.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		loginUser = new JButton("Login User"); //new game button
		loginUser.setBackground(Color.LIGHT_GRAY); //changes buttons to have light gray backgrounds (match title)
		loginUser.setFont(new Font("Felix Titling", Font.BOLD, 32)); //changes font and size of buttons
		
		//adds buttons to buttons panel
		buttons.add(newGame);
		buttons.add(blank1); //spacing
		buttons.add(instructions);
		buttons.add(blank1);
		buttons.add(createUser);
		buttons.add(blank1);
		buttons.add(loginUser);
		
		//adds objects to panel
		main.add(blank1); //spacing
		main.add(titleFrame);
		main.add(userTitleFrame);
		main.add(userNameFrame);
		main.add(buttons);
		main.add(blank3); //spacing
		
		//adds action listeners for each button
		newGame.addActionListener(new newGameL());
		instructions.addActionListener(new instructionsL());
		createUser.addActionListener(new createUserB());
		loginUser.addActionListener(new loginUserB());
		
	}
	
	public class newGameL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (p != null) {
				new GameScreen(p); //opens frame for the game screen
			} else {
				new GameScreen(new Player("anon"));
			}
		}
	}
	public class instructionsL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name = userTitle.getText();
			if (name == ""){
				name = "Default Player";
			} else {
				// Use filename instead
				name = name + "_stats.txt";
			}
			dispose(); //exits current frame
			new InstructionScreen(new Player(name)); //opens frame for the game screen
		}
	}

	public class createUserB implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			userTitle.setText(userName.getText());
			p = new Player(userName.getText());
		}
	}

	public class loginUserB implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			userTitle.setText(userName.getText());
			p = new Player(userName.getText() + "_stats.txt");
		}
	}
}
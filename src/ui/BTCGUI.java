package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class develops the user interface for the application.
 * @author Zachary Wooten & Sam Crider
 * @version 2016-05-10
 */
public class BTCGUI extends JFrame {
	/** The container to hold the main panel. */
	private Container c;
	/** The panel used to control the sub-panels. */
	private ControlPanel controlPanel;
	/** The card layout object used to switch between sub-panels. */
	private CardLayout cl;
	/** The background image used for the title screen. */
	private BufferedImage titleScreenImage;
	/** The file for the title screen image. */
	private static final File TITLE_SCREEN_IMG_FILE = new File("images/titleScreen.jpg");
	/** The title for the title panel card. */
	private static final String TITLE_PANEL_TITLE = "Title Panel";
	/** The title for the game setup panel card. */
	private static final String GAME_SETUP_PANEL_TITLE = "Game Setup Panel";
	/** The title for the high score panel card. */
	private static final String HIGH_SCORE_PANEL_TITLE = "High Score Panel";
	
	
	/**
	 * Constructs the GUI.
	 */
	public BTCGUI() {
		super("Between Two Cities");
		setSize(1000, 600);
		setLocation(100, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		cl = new CardLayout();
		controlPanel = new ControlPanel();
		c.add(controlPanel);
		setVisible(true);
	}
	
	/**
	 * Runs the program.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new BTCGUI();
	}
	
	private class ControlPanel extends JPanel {
		/** The title screen for the game. */
		private TitlePanel titlePanel;
		/** The game setup screen for the game. */
		private GameSetupPanel gameSetupPanel;
		/** The high score screen for the game. */
		private HighScorePanel hsPanel;
		
		public ControlPanel() {
			titlePanel = new TitlePanel();
			gameSetupPanel = new GameSetupPanel();
			hsPanel = new HighScorePanel();
			setPreferredSize(new Dimension(1000, 600));
			setLayout(cl);
			add(titlePanel, TITLE_PANEL_TITLE);
			add(gameSetupPanel, GAME_SETUP_PANEL_TITLE);
			add(hsPanel, HIGH_SCORE_PANEL_TITLE);
			cl.show(this, TITLE_PANEL_TITLE);
		}
		
	}
	
	private class TitlePanel extends JPanel implements ActionListener {
		
		/** The button to take the user to the game setup screen. */
		private JButton gameSetupButton;
		/** The button to take the user to the high score screen. */
		private JButton highScoreButton;
		/** The button to take the user to the options screen. */
		private JButton optionsButton;
		/** The button to quit the application. */
		private JButton quitButton;
		
		public TitlePanel() {
			setPreferredSize(new Dimension(1000, 600));
			setBackground(Color.BLACK);
			titleScreenImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
			try {
				titleScreenImage = ImageIO.read(TITLE_SCREEN_IMG_FILE);
			} catch (IOException e) {
				System.out.println("Title screen image not found.");
			}
			gameSetupButton = new JButton("Game Setup");
			highScoreButton = new JButton("High Scores");
			optionsButton = new JButton("Options");
			quitButton = new JButton("Quit");
			gameSetupButton.addActionListener(this);
			highScoreButton.addActionListener(this);
			optionsButton.addActionListener(this);
			quitButton.addActionListener(this);
			add(gameSetupButton);
			add(highScoreButton);
			add(optionsButton);
			add(quitButton);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(titleScreenImage.getScaledInstance(getHeight(), getHeight(), Image.SCALE_SMOOTH), (getWidth() - getHeight()) / 2, 0, null);
		}

		/**
		 * Designates the actions performed when buttons are clicked.
		 * @param e the action event that occurs
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == gameSetupButton) {
				cl.show(controlPanel, GAME_SETUP_PANEL_TITLE);
			} else if (e.getSource() == quitButton) {
				System.exit(0);
			}
		}
		
	}
	
	private class GameSetupPanel extends JPanel implements ActionListener {

		/** The panel that holds the number of players. */
		private JPanel playerNumberPanel;
		/** The label to ask for the number of players. */
		private JLabel playerNumberLabel;
		/** The dropdown menu used to determine the number of players. */
		private JComboBox<Integer> playerNumberOptions;
		/** The textfield used to determine each player's name. */
		private JTextField[] playerNameTextField;
		/** The strings used for each player name label. */
		private String[] playerNameLabels;
		/** The panel that holds the player name text fields. */
		private JPanel playerNamePanel;
		
		public GameSetupPanel() {
			setBackground(new Color(0x073763));
			setPreferredSize(new Dimension(1000, 600));
			playerNumberPanel = new JPanel();
			playerNumberLabel = new JLabel("Select the number of players:");
			playerNumberLabel.setForeground(new Color(0xf1c232));
			playerNumberOptions = new JComboBox<Integer>();
			for (int i = 3; i <= 7; i++) {
				playerNumberOptions.addItem(i);
			}
			playerNumberOptions.addActionListener(this);
			playerNumberPanel.add(playerNumberLabel);
			playerNumberPanel.add(playerNumberOptions);
			add(playerNumberPanel);
			playerNamePanel = new JPanel();
			playerNameTextField = new JTextField[3];
			playerNamePanel.setLayout(new GridLayout(playerNameTextField.length, 2));
			for (int i = 1; i <= playerNameTextField.length; i++) {
				setForeground(new Color(0xf1c232));
				playerNamePanel.add(new JLabel("Player " + i));
				playerNameTextField[i - 1] = new JTextField(20);
				playerNamePanel.add(playerNameTextField[i - 1]);
			}
			add(playerNamePanel);
		}

		/**
		 * Designates the actions performed when buttons are clicked.
		 * @param e the action event that occurs
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == playerNumberOptions) {
				playerNameTextField = new JTextField[(Integer)playerNumberOptions.getSelectedItem()];
				playerNamePanel = new JPanel();
				playerNamePanel.setLayout(new GridLayout(playerNameTextField.length, 2));
				for (int i = 1; i <= playerNameTextField.length; i++) {
					setForeground(new Color(0xf1c232));
					playerNamePanel.add(new JLabel("Player " + i));
					playerNameTextField[i - 1] = new JTextField(20);
					playerNamePanel.add(playerNameTextField[i - 1]);
				}
				add(playerNamePanel);
				System.out.println("Selected number of players = " + playerNameTextField.length);
			}
			
		}
		
	}
	
	private class HighScorePanel extends JPanel implements ActionListener {

		/**
		 * Designates the actions performed when buttons are clicked.
		 * @param e the action event that occurs
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class OptionsPanel extends JPanel implements ActionListener {

		/**
		 * Designates the actions performed when buttons are clicked.
		 * @param e the action event that occurs
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

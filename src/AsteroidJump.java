import javax.swing.JFrame;
import javax.swing.JPanel;

public class AsteroidJump {
	// MAIN METHOD
	public static void main(String[] args) {
		AsteroidJump asteroidJump = new AsteroidJump();

	}
	
	// MEMfBER VARIABLES
	JFrame mainFrame;
	GamePanel gamePanel;
	
	final static int width = 600;
	final static int height = 900;

	
	// CONSTRUCTOR
	AsteroidJump() {
		mainFrame = new JFrame();
		gamePanel = new GamePanel();
		
		setup();
	}
	
	// METHODS
	void setup() {
		mainFrame.add(gamePanel);
		mainFrame.addKeyListener(gamePanel);
		mainFrame.setVisible(true);
		mainFrame.setSize(width, height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel.startGame();
		
	}
	
}

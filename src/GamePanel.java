import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener {
	// MEMBER VARIABLES
	Timer timer;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;

	int currentState = MENU_STATE;

	Font titleFont;
	Font textFont;

	SpaceMan spaceMan;

	static int arrowCase = 0;

	ObjectManager om = new ObjectManager();

	public static BufferedImage spaceManRlmg;
	public static BufferedImage spaceManLlmg;
	public static BufferedImage asteroidlmg;
	
	Camera camera = new Camera();

	// CONSTRUCTOR
	GamePanel() {
		timer = new Timer(1000 / 60, this);

		titleFont = new Font("FUTURA", Font.PLAIN, 48);
		textFont = new Font("Monaco", Font.PLAIN, 24);
		
		int randX2 = 0;
		
		for (int i = 2; i < 50; i++) {
			int randX = new Random().nextInt(450);
			if (i == 2) {
				randX2 = randX;
			}
			
			om.addObject(new Asteroid(randX, i * 300, 175, 50));
		}
		
		spaceMan = new SpaceMan(randX2 + 68, 150, 39, 60);

		om.setSpaceMan(spaceMan);
		
		try {
			spaceManRlmg = ImageIO.read(this.getClass().getResourceAsStream("spaceManR.png"));
			spaceManLlmg = ImageIO.read(this.getClass().getResourceAsStream("spaceManL.png"));
			asteroidlmg = ImageIO.read(this.getClass().getResourceAsStream("8bitast.png"));
		}
	
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// METHODS
	public void actionPerformed(ActionEvent arg0) {
		repaint();

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	public void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("working");
			currentState += 1;

			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			spaceMan.right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			spaceMan.left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			spaceMan.right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			spaceMan.left = false;
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {
		// om.manageEnemies();

		om.update();

		om.checkCollision();

		if (spaceMan.isAlive == false) {
			currentState = END_STATE;

			om.reset();

			SpaceMan spaceMan = new SpaceMan(250, 700, 50, 50);

			om.setSpaceMan(spaceMan);
		}

		om.getScore();
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AsteroidJump.width, AsteroidJump.height);

		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {
				int lumans = new Random().nextInt(256);
				g.setColor(new Color(lumans, lumans, lumans));
				int x = new Random().nextInt(AsteroidJump.width);
				int y = new Random().nextInt(AsteroidJump.height);
				g.fillOval(x, y, 5, 5);
			}
		}

		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("ASTEROID JUMP", 100, 200);
		g.setFont(textFont);
		g.drawString("Press ENTER to start", 150, 350);
		g.drawString("Press SPACE for intructions", 100, 450);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AsteroidJump.width, AsteroidJump.height);

		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {
				g.setColor(Color.WHITE);
				int x = new Random().nextInt(AsteroidJump.width);
				int y = new Random().nextInt(AsteroidJump.height);
				g.fillOval(x, y, 5, 5);
			}
		}

		om.draw(g, camera);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AsteroidJump.width, AsteroidJump.height);

		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {
				g.setColor(Color.WHITE);
				int x = new Random().nextInt(AsteroidJump.width);
				int y = new Random().nextInt(AsteroidJump.height);
				g.fillOval(x, y, 5, 5);
			}
		}

		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 130, 200);
		g.setFont(textFont);
		g.drawString("You killed " + om.getScore() + " aliens", 150, 350);
		g.drawString("Press BACKSPACE to Restart", 100, 450);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (spaceMan.toggle == 1) {
			spaceMan.startClick = true;
		}
		if (spaceMan.toggle == 2) {
			spaceMan.canJump = true;
		}
		
		System.out.println("click");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (spaceMan.startClick == true) {
			spaceMan.mouseY = e.getY();
		}
		
	}

}

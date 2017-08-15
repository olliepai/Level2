import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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

	static int yScore = 0;
	static int score = 0;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INS_STATE = 3;

	int currentState = MENU_STATE;

	Font titleFont;
	Font textFont;
	Font insFont;

	SpaceMan spaceMan;

	static int arrowCase = 0;

	int randX2 = 0;

	ObjectManager om = new ObjectManager();

	public static BufferedImage spaceManRlmg;
	public static BufferedImage spaceManLlmg;
	public static BufferedImage asteroidlmg;
	public static BufferedImage powerUplmg;

	Camera camera = new Camera();

	// CONSTRUCTOR
	GamePanel() {
		timer = new Timer(1000 / 60, this);

		titleFont = new Font("FUTURA", Font.PLAIN, 48);
		textFont = new Font("Monaco", Font.PLAIN, 24);
		insFont = new Font("Monaco", Font.PLAIN, 16);

		int powerSpawn1 = new Random().nextInt(10);
		int powerSpawn2 = new Random().nextInt(10) + 10;
		int powerSpawn3 = new Random().nextInt(10) + 20;
		int powerSpawn4 = new Random().nextInt(10) + 30;
		int powerSpawn5 = new Random().nextInt(10) + 40;

		for (int i = 1; i < 40; i++) {
			int randXE = new Random().nextInt(120);
			int randXO = new Random().nextInt(120) + 250;
			if (i == 1) {
				randX2 = randXO;
				om.addObjectA(new Asteroid(randXO, 200, 175, 50));
			} else if (i % 2 == 0) {
				om.addObjectA(new Asteroid(randXE, i * 500 - 300, 175, 50));
			} else {
				om.addObjectA(new Asteroid(randXO, i * 500 - 300, 175, 50));
			}
			if (powerSpawn1 == i && i % 2 == 0) {
				om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn1 == i && i % 2 != 0) {
				om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn2 == i && i % 2 == 0) {
				om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn2 == i && i % 2 != 0) {
				om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn3 == i && i % 2 == 0) {
				om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn3 == i && i % 2 != 0) {
				om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn4 == i && i % 2 == 0) {
				om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn4 == i && i % 2 != 0) {
				om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn5 == i && i % 2 == 0) {
				om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
			if (powerSpawn5 == i && i % 2 != 0) {
				om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
			}
		}

		spaceMan = new SpaceMan(randX2 + 68, 200 - 60, 39, 60);

		om.setSpaceMan(spaceMan);

		try {
			spaceManRlmg = ImageIO.read(this.getClass().getResourceAsStream("spaceManR.png"));
			spaceManLlmg = ImageIO.read(this.getClass().getResourceAsStream("spaceManL.png"));
			asteroidlmg = ImageIO.read(this.getClass().getResourceAsStream("8bitast.png"));
			powerUplmg = ImageIO.read(this.getClass().getResourceAsStream("powerUp.png"));
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
		} else if (currentState == INS_STATE) {
			updateInsState();
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
		} else if (currentState == INS_STATE) {
			drawInsState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState != GAME_STATE) {
				currentState += 1;
			}

			if (currentState > END_STATE) {
				currentState = MENU_STATE;
				score = 0;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			spaceMan.right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			spaceMan.left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU_STATE) {
				currentState = INS_STATE;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (currentState == INS_STATE) {
				currentState = MENU_STATE;
			}
			if (currentState == END_STATE) {

				currentState = GAME_STATE;
				score = 0;
			}
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
		om.update();

		om.checkCollision();

		if (spaceMan.isAlive == false) {
			om.purgeObjects();
			currentState = END_STATE;

			om.reset();

			int powerSpawn1 = new Random().nextInt(10);
			int powerSpawn2 = new Random().nextInt(10) + 10;
			int powerSpawn3 = new Random().nextInt(10) + 20;
			int powerSpawn4 = new Random().nextInt(10) + 30;
			int powerSpawn5 = new Random().nextInt(10) + 40;

			for (int i = 1; i < 40; i++) {
				int randXE = new Random().nextInt(120);
				int randXO = new Random().nextInt(120) + 250;
				if (i == 1) {
					randX2 = randXO;
					om.addObjectA(new Asteroid(randXO, 200, 175, 50));
				} else if (i % 2 == 0) {
					om.addObjectA(new Asteroid(randXE, i * 500 - 300, 175, 50));
				} else {
					om.addObjectA(new Asteroid(randXO, i * 500 - 300, 175, 50));
				}
				if (powerSpawn1 == i && i % 2 == 0) {
					om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn1 == i && i % 2 != 0) {
					om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn2 == i && i % 2 == 0) {
					om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn2 == i && i % 2 != 0) {
					om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn3 == i && i % 2 == 0) {
					om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn3 == i && i % 2 != 0) {
					om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn4 == i && i % 2 == 0) {
					om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn4 == i && i % 2 != 0) {
					om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn5 == i && i % 2 == 0) {
					om.addObjectP(new PowerUp(randXE + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
				if (powerSpawn5 == i && i % 2 != 0) {
					om.addObjectP(new PowerUp(randXO + 175 / 2 - 25 / 2, i * 500 - 300 - 25, 25, 25));
				}
			}

			spaceMan.jumpCounter = 0;

			spaceMan = new SpaceMan(randX2 + 68, 200 - 60, 39, 60);
			System.out.println(spaceMan.isAlive);

			Camera.yOffset = 0;

			om.setSpaceMan(spaceMan);

		}
	}

	void updateEndState() {

	}

	void updateInsState() {

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

		g.setColor(Color.YELLOW);
		g.setFont(textFont);
		g.drawString("Score: " + score, 225, 75);

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
		if (score == 1) {
			g.drawString("You jumped to " + score + " asteroid.", 105, 350);
		} else {
			g.drawString("You jumped to " + score + " asteroids.", 105, 350);
		}

		g.drawString("Press BACKSPACE to Restart", 100, 450);
		g.drawString("Press ENTER to go back to the Menu", 50, 550);
	}

	void drawInsState(Graphics g) {
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

		g.setColor(Color.BLUE);
		g.setFont(titleFont);
		g.drawString("INSTRUCTIONS", 120, 200);
		g.setFont(insFont);
		g.drawString("Jump down to as many ASTEROIDS as you can", 90, 300);
		g.drawString("Click the mouse ONCE to display jump power bar", 65, 375);
		g.drawString("Drag the mouse UP and DOWN to set the power of the jump", 20, 450);
		g.drawString("Use LEFT and RIGHT arrow keys to set position on asteroid", 15, 525);
		g.drawString("Click AGAIN to jump", 200, 600);
		g.drawString("Collect MUSHROOMS to double your score", 100, 675);
		g.setFont(textFont);
		g.drawString("Press BACKSPACE to go back to the menu", 25, 750);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (currentState == GAME_STATE) {
			if (spaceMan.toggle == 0) {
				spaceMan.startClick = true;
				// System.out.println(spaceMan.toggle);
			}
			if (spaceMan.toggle == 1) {
				spaceMan.startClick = false;
				spaceMan.jump();
				// System.out.println(spaceMan.toggle);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME_STATE) {
			if (spaceMan.startClick == true) {
				spaceMan.toggle = 1;
			}
		}
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

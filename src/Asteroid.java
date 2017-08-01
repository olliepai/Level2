import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Asteroid extends GameObject {
	// MEMBER VARIABLES

	boolean right = false;
	boolean left = false;

	// CONSTRUCTOR
	Asteroid(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	// METHODS
	void update() {
		super.update();
		
		collisionBox.setBounds(x, y, width, height);

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.asteroidlmg, x, y, width, height, null);
	}
}

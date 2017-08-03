import java.awt.Color;
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
		
		collisionBox.setBounds(x + 10, y + 10, width - 20, height - 10);

	}

	void draw(Graphics g, int xOffset, int yOffset) {
		g.drawImage(GamePanel.asteroidlmg, x - xOffset, y - yOffset, width, height, null);
	}
}

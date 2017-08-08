import java.awt.Graphics;

public class Asteroid extends GameObject {
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
		// g.setColor(Color.RED);
		// g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
}

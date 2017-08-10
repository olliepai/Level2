import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObject {
	// CONSTRUCTOR
	PowerUp(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	// METHODS
	void update() {
		super.update();
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g, int xOffset, int yOffset) {
		g.drawImage(GamePanel.powerUplmg, x - xOffset, y - yOffset, width, height, null);
		g.setColor(Color.RED);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
}

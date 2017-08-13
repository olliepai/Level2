import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObject {
	// MEMBER VARIABLES

	// CONSTRUCTOR
	PowerUp(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	// METHODS
	void update() {
		super.update();

		collisionBox.setBounds(x, y, width, height);

	}

	@Override
	void draw(Graphics g, int xOffset, int yOffset) {
		if (SpaceMan.hitPowerUp == false) {
			g.drawImage(GamePanel.powerUplmg, x - xOffset, y - yOffset, width, height, null);
			g.setColor(Color.RED);
			g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
		} 
		else {
			g.dispose();
		}
	}

}

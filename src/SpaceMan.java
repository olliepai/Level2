import java.awt.Graphics;

public class SpaceMan extends GameObject {
	// MEMBER VARIABLES
	int speed;

	boolean right = false;
	boolean left = false;

	// CONSTRUCTOR
	SpaceMan(int x, int y, int width, int height) {
		super(x, y, width, height);

		speed = 5;
	}

	// METHODS
	void update() {
		super.update();

		if (right) {
			x += 5;
		}
		if (left) {
			x -= 5;
		}
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.spaceManlmg, x, y, width, height, null);
	}
}

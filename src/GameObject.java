import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	// MEMBER VARIABLES
	int x;
	int y;
	int width;
	int height;

	boolean isAlive = true;

	Rectangle collisionBox;

	// CONSTRUCTOR
	GameObject(int x, int y, int width, int height) {
		collisionBox = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// METHODS
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {

	}
}

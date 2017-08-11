import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	// MEMBER VARIABLES
	int x;
	int y;

	GameObject asteroid;
	GameObject powerUp;

	// METHODS
	public void draw(Graphics g, ArrayList<Asteroid> asteroidObjects, ArrayList<PowerUp> powerUpObjects, SpaceMan spaceMan) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			asteroid = asteroidObjects.get(i);
			asteroid.draw(g, x, y);

		}
		powerUp = powerUpObjects.get(0);
		powerUp.draw(g, x, y);

		if (spaceMan.isJumping == false && spaceMan.canMove == true) {
			spaceMan.canJump = false;

			GamePanel.yScore += 1;
			// System.out.println(GamePanel.yScore);

			if (GamePanel.yScore % 12204 == 0) {
				GamePanel.score += 1;
			}

			System.out.println(y);

			if (y < 500) {
				y += 2;
				// spaceMan.canMove = false;

			}

		}

		spaceMan.draw(g, x, y);
	}
}

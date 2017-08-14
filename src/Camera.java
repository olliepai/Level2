import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	// MEMBER VARIABLES
	int x;
	static int yOffset;

	GameObject asteroid;
	GameObject powerUp;

	// METHODS
	public void draw(Graphics g, ArrayList<Asteroid> asteroidObjects, ArrayList<PowerUp> powerUpObjects,
			SpaceMan spaceMan) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			asteroid = asteroidObjects.get(i);
			asteroid.draw(g, x, yOffset);

		}
		spaceMan.draw(g, x, yOffset);
		if (powerUpObjects.size() > 1) {
			for (int i = 0; i < powerUpObjects.size(); i++) {
				powerUp = powerUpObjects.get(i);
				powerUp.draw(g, x, yOffset);
			}
		}

		if (spaceMan.isJumping == false && spaceMan.canMove == true) {
			

			// System.out.println(GamePanel.yScore);

			if (GamePanel.yScore % 250 == 0 && GamePanel.yScore % 500 != 0) {
				GamePanel.score += 1;
			}

			if (yOffset < spaceMan.maxMove) {
				GamePanel.yScore += 10;
				yOffset += 10;
				spaceMan.startClick = false;
			}

		}

	}

	public void moveAsteroids() {

	}
}

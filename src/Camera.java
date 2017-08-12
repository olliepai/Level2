import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	// MEMBER VARIABLES
	int x;
	int yOffset;

	GameObject asteroid;
	GameObject powerUp;
	
	int originalYOffset;
	

	// METHODS
	public void draw(Graphics g, ArrayList<Asteroid> asteroidObjects, ArrayList<PowerUp> powerUpObjects, SpaceMan spaceMan) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			asteroid = asteroidObjects.get(i);
			asteroid.draw(g, x, yOffset);

		}
		powerUp = powerUpObjects.get(0);
		powerUp.draw(g, x, yOffset);

		if (spaceMan.isJumping == false && spaceMan.canMove == true) {
			spaceMan.canJump = false;

			GamePanel.yScore += 1;
			// System.out.println(GamePanel.yScore);

			if (GamePanel.yScore % 12204 == 0) {
				GamePanel.score += 1;
			}

			if (yOffset % 500 == 0 && spaceMan.toggleMove == 0) {
				originalYOffset = yOffset;
				spaceMan.moveAsteroids = true;
				// spaceMan.canMove = false;
			}		
			
			if (spaceMan.moveAsteroids == true) {
				yOffset += 10;
				if (yOffset - originalYOffset > 499) {
					spaceMan.moveAsteroids = false;
					spaceMan.toggleMove = 1;
				}
				System.out.println(yOffset - originalYOffset);
			}

		}

		spaceMan.draw(g, x, yOffset);
	}
	
	public void moveAsteroids() {
		
	}
}

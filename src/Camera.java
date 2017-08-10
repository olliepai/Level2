import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	// MEMBER VARIABLES
	int x;
	int y;

	// METHODS
	public void draw(Graphics g, ArrayList<Asteroid> asteroidObjects, ArrayList<PowerUp> powerUpObjects, SpaceMan spaceMan) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			GameObject o = asteroidObjects.get(i);
			// GameObject a = powerUpObjects.get(0);
			o.draw(g, x, y);
			// a.draw(g, x, y);
			if (spaceMan.canMove == true && spaceMan.isJumping == true) {
				spaceMan.canJump = false;
				spaceMan.isMoving = true;
				GamePanel.yScore += 1;
				System.out.println(GamePanel.yScore);

				if (GamePanel.yScore % 12204 == 0) {
					GamePanel.score += 1;
				}

				// a.y -= 2;
				o.y -= 2;
				if (o.y == 700) {
					spaceMan.canMove = false;
					spaceMan.isJumping = false;
					spaceMan.isMoving = false;
				}
			}
		}

		spaceMan.draw(g, x, y);
	}
}

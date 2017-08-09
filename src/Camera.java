import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	// MEMBER VARIABLES
	int x;
	int y;
	int yScore = 0;

	// METHODS
	public void draw(Graphics g, ArrayList<Asteroid> asteroidObjects, SpaceMan spaceMan) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			GameObject o = asteroidObjects.get(i);
			o.draw(g, x, y);
			if (spaceMan.canMove == true && spaceMan.isJumping == true) {
				spaceMan.canJump = false;
				spaceMan.isMoving = true;
				yScore += 1;
				System.out.println(yScore);

				if (yScore % 12204 == 0) {
					spaceMan.score += 1;
				}

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

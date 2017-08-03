import java.awt.Graphics;
import java.util.ArrayList;

public class Camera {
	// MEMBER VARIABLES
	int x;
	int y; 
	
	// METHODS
	public void draw(Graphics g, ArrayList<Asteroid> asteroidObjects, SpaceMan spaceMan) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			GameObject o = asteroidObjects.get(i);
			o.draw(g, x, y);
			if (spaceMan.canMove == true && spaceMan.isJumping == true) {
				spaceMan.canJump = false;
				spaceMan.isMoving = true;
				o.y -= 1;
				if (o.y == 500) {
					spaceMan.canMove = false;
					spaceMan.isJumping = false;
					spaceMan.isMoving = false;
				}
			}
		}

		spaceMan.draw(g, x, y);
	}
}

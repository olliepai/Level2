import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SpaceMan extends GameObject {
	// MEMBER VARIABLES
	int speed;

	boolean right = false;
	boolean left = false;

	boolean canJump;
	boolean canMove;

	double yVelocity = 0;
	double gravity = 0.08;
	double newY = 0;

	private int yLimit = 500;

	Asteroid asteroid;
	
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

		if (asteroid != null && collisionBox.intersects(asteroid.collisionBox)) {
			canJump = true;
			
			int ax = asteroid.collisionBox.x;
			int ay = asteroid.collisionBox.y;
			int aw = asteroid.collisionBox.width;
			int ah = asteroid.collisionBox.height;

			if (x + width > ax && newY + height / 2 > ay && newY + height / 2 < ay + ah && x + width < ax + aw / 2) {
				x = asteroid.x - width;
			}

			if (x < ax + aw && newY + height / 2 > ay && newY + height / 2 < ay + ah && x > ax + aw / 2) {
				x = ax + aw;
			}
			if (newY + height > ay && x + width > ax && x < ax + aw && newY + height < ay + ah / 2) {
				newY = ay - height;
				if (ay == 700) {
					canMove = true;
				}
			}

			if (newY < ay + ah && x + width > ax && x < ax + aw && newY + height > ay + ah / 2) {
				newY = ay + ah;
			}

			yVelocity = 0;
			gravity = 0;
		} 
		
		else {
			canJump = true;
			gravity = 0.08;
			yVelocity += gravity;
			newY += yVelocity;
		}

		collisionBox.setBounds(x, (int) newY, width, height);
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.spaceManlmg, x, (int) newY, width, height, null);
	}

	public void jump() {
		if (canJump == true) {
			System.out.println("jumping");
			yVelocity = -2;
			canJump = false;
		}
	}
	
	void moveAsteroid() {
		int ax = asteroid.collisionBox.x;
		int ay = asteroid.collisionBox.y;
		int aw = asteroid.collisionBox.width;
		int ah = asteroid.collisionBox.height;

		
		if (canMove == true) {
			ay -= 2;
			if (ay == 250) {
				canMove = false;
			}
		}
	}

	void setCollisionObject(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
}

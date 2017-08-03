import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SpaceMan extends GameObject {
	// MEMBER VARIABLES
	int speed;

	boolean right = false;
	boolean left = false;

	boolean canJump;
	boolean canMove;
	boolean isJumping;
	boolean isMoving;

	double yVelocity = 0;
	double gravity = 0.08;
	double newY = 0;

	private int yLimit = 500;

	Asteroid asteroid;
	
	// CONSTRUCTOR
	SpaceMan(int x, int y, int width, int height) {
		super(x, y, width, height);
		newY = y;

		speed = 5;
	}

	// METHODS
	void update() {
		super.update();
		
		if (right && isMoving == false) {
			x += 5;
		}
		if (left && isMoving == false) {
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
				canMove = true;
			}

			if (newY < ay + ah && x + width > ax && x < ax + aw && newY + height > ay + ah / 2) {
				newY = ay + ah;
			}

			yVelocity = 0;
			gravity = 0;
		} 
		
		else {
			canMove = false;
			canJump = true;
			gravity = 0.08;
			yVelocity += gravity;
			newY += yVelocity;
		}

		collisionBox.setBounds(x, (int) newY, width, height);
	}

	void draw(Graphics g, int xOffset, int yOffset) {
		g.drawImage(GamePanel.spaceManlmg, x - xOffset, (int) newY - yOffset, width, height, null);
	}

	public void jump() {
		if (canJump == true) {
			isJumping = true;
			System.out.println("jumping");
			yVelocity = -2;
			canJump = false;
		}
	}

	void setCollisionObject(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
}

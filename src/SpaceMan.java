import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SpaceMan extends GameObject {
	// MEMBER VARIABLES
	int speed;

	boolean right = false;
	boolean left = false;

	boolean canJump = false;
	boolean canMove;
	boolean isJumping;
	boolean isMoving;

	double xVelocity = 0;
	double yVelocity = 0;
	double accel = 0.01;
	double gravity = 0.08;
	double newY = 0;

	int dir = 1;

	boolean startClick = false;
	int toggle = 0;
	int mouseY;
	int xO;
	int yO;

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
		
		


		/*
		 * if (canJump == true) { xVelocity += accel; x += xVelocity; }
		 */
		

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
				
				if (x > ax && x + width < ax + aw) {
					if (right) {
						dir = 1;
						x += 5;
					}
					if (left) {
						dir = 2;
						x -= 5;
					}
				}
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
		xO = xOffset;
		yO = yOffset;

		if (dir == 1) {
			g.drawImage(GamePanel.spaceManRlmg, x - xOffset, (int) newY - yOffset, width, height, null);
		}
		if (dir == 2) {
			g.drawImage(GamePanel.spaceManLlmg, x - xOffset, (int) newY - yOffset, width, height, null);
		}

		if (startClick == true) {
			g.setColor(Color.RED);

			if (mouseY < (int) newY - yOffset - 65) {
				mouseY = (int) newY - yOffset - 65;
			}
			g.fillRect(x - xOffset + 40, mouseY, 11, ((int) newY - yOffset - 65 + 60) - mouseY);
			g.drawRect(x - xOffset + 40, (int) newY - yOffset - 65, 10, 60);
			System.out.println((-1 * (((int) newY - yO - 65 + 60) - mouseY)) / 4);
		}
	}

	public void jump() {
		if (canJump == true) {
			isJumping = true;
			//System.out.println("jumping");
			yVelocity = (-1 * (((int) newY - yO - 65 + 60) - mouseY)) / 4;
			canJump = false;
			toggle = 0;
		}
	}

	void setCollisionObject(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
}

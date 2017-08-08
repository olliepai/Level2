import java.awt.Color;
import java.awt.Graphics;

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
	int reset;

	Asteroid asteroid;

	// CONSTRUCTOR
	SpaceMan(int x, int y, int width, int height) {
		super(x, y, width, height);
		newY = y;

		speed = 3;
	}

	// METHODS
	void update() {
		super.update();

		if (asteroid != null && collisionBox.intersects(asteroid.collisionBox)) {
			canJump = true;

			int ax = asteroid.collisionBox.x;
			int ay = asteroid.collisionBox.y;
			int aw = asteroid.collisionBox.width;
			int ah = asteroid.collisionBox.height;

			if (x > ax && newY + height / 2 > ay && newY + height / 2 < ay + ah && x + width < ax + aw / 2) {
				x = ax;
			}

			if (x + width < ax + aw && newY + height / 2 > ay && newY + height / 2 < ay + ah && x > ax + aw / 2) {
				x = ax + aw - width;
			}
			if (newY + height > ay && x > ax && x + width < ax + aw && newY + height < ay + ah / 2) {
				newY = ay - height;
				speed = 1;
				canMove = true;
			}

			if (newY < ay + ah && x > ax && x + width < ax + aw && newY + height > ay + ah / 2) {
				newY = ay + ah;
			}

			if (x < asteroid.collisionBox.x + 2) {
				x = asteroid.collisionBox.x + 2;
				left = false;
			}

			if (x + width > asteroid.collisionBox.x + asteroid.collisionBox.width - 2) {
				x = asteroid.collisionBox.x + asteroid.collisionBox.width - width - 2;
				right = false;
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

			if (dir == 1 && isJumping == true) {
				x += 2;
			}
			if (dir == 2 && isJumping == true) {
				x -= 2;
			}
		}

		if (right) {
			dir = 1;
			x += speed;
		}
		if (left) {
			dir = 2;
			x -= speed;
		}

		if (newY < 0 || newY + height > 900) {
			isAlive = false;
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

		// g.setColor(Color.RED);
		// g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);

		if (startClick == true) {
			g.setColor(Color.RED);

			if (mouseY < (int) newY - yOffset - 65) {
				mouseY = (int) newY - yOffset - 65;
			}
			g.fillRect(x - xOffset + 40, mouseY, 11, ((int) newY - yOffset - 65 + 60) - mouseY);
			g.drawRect(x - xOffset + 40, (int) newY - yOffset - 65, 10, 60);
			System.out.println((-1 * (((int) newY - yO - 65 + 60) - mouseY)) / 8);
		}

	}

	public void jump() {
		if (canJump == true) {
			isJumping = true;
			System.out.println("jumping");
			yVelocity = (-1 * (((int) newY - yO - 65 + 60) - mouseY)) / 8;
			System.out.println(yVelocity);
			canJump = false;
			toggle = 0;
		}
	}

	void setCollisionObject(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
}

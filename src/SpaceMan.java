import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SpaceMan extends GameObject {
	// MEMBER VARIABLES
	int speed;

	boolean right = false;
	boolean left = false;
	
	boolean canJump;
	
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
		
		if (asteroid != null && collisionBox.intersects(asteroid.collisionBox) ) {
			canJump = true;
			newY = asteroid.y - height;
			yVelocity = 0;
			gravity = 0;
		}
		else {
			gravity = 0.08;
			yVelocity += gravity;
			newY += yVelocity;
		}
		
		collisionBox.setBounds(x, (int) newY, width, height);
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.spaceManlmg, x, (int) newY, width, height, null);
	}
		
	public void jump(){
		if (canJump == true) {
			System.out.println("jumping");
			yVelocity = -2;
			canJump = false;
		}
	}
	
	void setCollisionObject(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
}

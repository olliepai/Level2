import java.awt.Graphics;
import java.awt.Rectangle;

public class SpaceMan extends GameObject {
	// MEMBER VARIABLES
	int speed;

	boolean right = false;
	boolean left = false;
	
	boolean isJumping = false;
	int yVelocity = 20;
	int gravity = 1;
	
	private int yLimit = 500;

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
		
		yVelocity += gravity;
		y += yVelocity;
		
		if(y >= yLimit + 1){
			y = yLimit+ 1;
			yVelocity = 0;
			isJumping = true;
		}
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.spaceManlmg, x, y, width, height, null);
	}
	
	
	
	public void jump(){
		if (isJumping == true) {
			yVelocity -= gravity;
			y -= yVelocity;
		}
			
			//canJump = false;
	}
}

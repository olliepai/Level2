import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<Asteroid> asteroidObjects;
	SpaceMan spaceMan;

	private int score = 0;

	long enemyTimer = 0;
	int enemySpawnTime = 200;

	public ObjectManager() {
		asteroidObjects = new ArrayList<Asteroid>();
	}

	public void addObject(Asteroid o) {
		asteroidObjects.add(o);
	}

	void setSpaceMan(SpaceMan spaceMan) {
		this.spaceMan = spaceMan;
	}

	public void update() {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			GameObject o = asteroidObjects.get(i);
			o.update();
		}

		spaceMan.update();

		purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			GameObject o = asteroidObjects.get(i);
			o.draw(g);
		}

		spaceMan.draw(g);
	}

	private void purgeObjects() {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			if (!asteroidObjects.get(i).isAlive) {
				asteroidObjects.remove(i);
			}
		}
	}

	// public void manageEnemies() {
	// if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
	// addObject(new Alien(new Random().nextInt(500), 0, 50, 50));
	// enemyTimer = System.currentTimeMillis();
	// }
	// }
	//
	public void checkCollision() {
		
		for (int i = 0; i < asteroidObjects.size(); i++) {
			Asteroid o1 = asteroidObjects.get(i);

			if (o1.collisionBox.intersects(spaceMan.collisionBox)) {
				spaceMan.setCollisionObject(o1);
			}

		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public void reset() {
		asteroidObjects.clear();
	}
}

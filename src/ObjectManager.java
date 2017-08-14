import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	ArrayList<Asteroid> asteroidObjects;
	ArrayList<PowerUp> powerUpObjects;
	SpaceMan spaceMan;

	private int score = 0;

	public ObjectManager() {
		asteroidObjects = new ArrayList<Asteroid>();
		powerUpObjects = new ArrayList<PowerUp>();
	}

	public void addObjectA(Asteroid o) {
		asteroidObjects.add(o);
	}

	public void addObjectP(PowerUp a) {
		powerUpObjects.add(a);
	}

	void setSpaceMan(SpaceMan spaceMan) {
		this.spaceMan = spaceMan;
	}

	public void update() {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			GameObject o = asteroidObjects.get(i);
			o.update();
		}

		for (int i = 0; i < powerUpObjects.size(); i++) {
			GameObject a = powerUpObjects.get(i);
			a.update();
		}

		spaceMan.update();

		purgeObjects();
	}

	public void draw(Graphics g, Camera camera) {
		camera.draw(g, asteroidObjects, powerUpObjects, spaceMan);
	}

	public void purgeObjects() {
		for (int i = 0; i < asteroidObjects.size(); i++) {
			if (!asteroidObjects.get(i).isAlive) {
				asteroidObjects.remove(i);
			}
		}

		for (int i = 0; i < powerUpObjects.size(); i++) {
			if (!powerUpObjects.get(i).isAlive) {
				powerUpObjects.remove(i);
			}
		}
	}

	public void checkCollision() {

		for (int i = 0; i < asteroidObjects.size(); i++) {
			Asteroid o1 = asteroidObjects.get(i);

			if (o1.collisionBox.intersects(spaceMan.collisionBox)) {
				spaceMan.setCollisionObjectA(o1);
			}

		}

		for (int i = 0; i < powerUpObjects.size(); i++) {
			PowerUp a1 = powerUpObjects.get(i);

			if (a1.collisionBox.intersects(spaceMan.collisionBox)) {
				spaceMan.setCollisionObjectP(a1);
			}

		}
	}

	public void reset() {
		powerUpObjects.clear();
		asteroidObjects.clear();
	}
}

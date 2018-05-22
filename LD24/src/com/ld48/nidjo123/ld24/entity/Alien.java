package com.ld48.nidjo123.ld24.entity;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;

public class Alien extends Entity {

	private Animation alienAnim;
	private boolean threw = false;
	private Random random;
	private int ticks = 0;
	private int targetY, dy = 1;
	private Rectangle bb;

	public Alien(int x, int y) {
		super(x, y);
		targetY = y;
		Image[] img = { Art.sheet.getSubImage(1, 1), Art.sheet.getSubImage(2, 1) };
		alienAnim = new Animation(img, 200);
		alienAnim.setAutoUpdate(true);
		bb = new Rectangle(x + 6, y + 1, 32 - 8, 32);
		random = new Random();
	}

	public void tick(GameContainer container, int delta) {
		if (Level.score > 0) {
			if (random.nextInt(200) == 0) {
				Level.getCurrentLevel().addEntity(new Spear(x, y, true));
				threw = true;
				Sound.shoot.play();
			}

			if (targetY < 20)
				targetY = 20;
			if (targetY > Game.HEIGHT - 32)
				targetY = Game.HEIGHT - 43;

			if (threw)
				ticks++;

			if (targetY < y)
				y -= delta * 0.1 * dy;
			else if (targetY > y)
				y += delta * 0.1 * dy;

			if (ticks > 40) {
				threw = false;
				ticks = 0;
				targetY = random.nextInt(Game.HEIGHT - 50);
			}

			for (int i = 0; i < Level.entities.size(); i++) {
				Entity e = (Entity) Level.entities.get(i);

				if (e instanceof Spear && !e.dead && !((Spear) e).isDangerous() && this.collides(e.getBB())) {
					e.die();
					Level.score--;
				}
			}

			bb.setLocation(x + 6, y + 1);
		}
	}

	public void render(GameContainer container) {
		if (threw) {
			alienAnim.draw(x, y);
		} else {
			Art.sheet.getSubImage(0, 1).draw(x, y);
		}
	}

	public boolean collides(Shape s) {
		if (this.bb.intersects(s))
			return true;
		return false;
	}
}

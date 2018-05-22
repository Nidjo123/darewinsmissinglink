package com.ld48.nidjo123.ld24.entity;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;

public class Caveman extends Entity {

	private Animation cavemanAnim;
	private boolean threw = false;
	private Random random;
	private int ticks = 0;
	private int targetY, dy = 1;

	public Caveman(int x, int y) {
		super(x, y);
		targetY = y;
		Image[] img = { Art.caveman.getSubImage(1, 0), Art.caveman.getSubImage(2, 0) };
		cavemanAnim = new Animation(img, 200);
		cavemanAnim.setAutoUpdate(true);
		random = new Random();
	}

	public void tick(GameContainer container, int delta) {
		if (Level.score > 0) {
			if (random.nextInt(150) == 0) {
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

			if (ticks > 100) {
				threw = false;
				ticks = 0;
				targetY = random.nextInt(Game.HEIGHT - 50 - 32);
			}
		}
	}

	public void render(GameContainer container) {
		if (threw) {
			cavemanAnim.draw(x, y);
		} else {
			Art.caveman.getSubImage(0, 0).draw(x, y);
		}
	}
}

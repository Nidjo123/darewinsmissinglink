package com.ld48.nidjo123.ld24.entity;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;

public class Spaceman extends Entity {

	private Animation spacemanAnim;
	private boolean threw = false;
	private Random random;
	private int ticks = 0;

	public Spaceman(int x, int y) {
		super(x, y);
		Image[] img = { Art.spaceman.getSubImage(1, 0), Art.spaceman.getSubImage(2, 0) };
		spacemanAnim = new Animation(img, 200);
		spacemanAnim.setAutoUpdate(true);
		random = new Random();
	}

	public void tick(GameContainer container, int delta) {
		if (Level.score > 0) {
			if (random.nextInt(150) == 0) {
				Level.getCurrentLevel().addEntity(new Rock(x, y));
				threw = true;
				Sound.shoot.play();
			}

			if (threw)
				ticks++;

			if (ticks > 100) {
				threw = false;
				ticks = 0;
			}
		}
	}

	public void render(GameContainer container) {
		if (threw) {
			spacemanAnim.draw(x, y);
		} else {
			Art.spaceman.getSubImage(0, 0).draw(x, y);
		}
	}
}

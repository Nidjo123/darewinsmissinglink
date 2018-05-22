package com.ld48.nidjo123.ld24.entity;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;

public class Monkey extends Entity {

	private Animation monkeyAnim;
	private boolean threw = false;
	private Random random;
	private int ticks = 0;

	public Monkey(int x, int y) {
		super(x, y);
		Image[] img = { Art.monkey.getSubImage(1, 0), Art.monkey.getSubImage(2, 0) };
		monkeyAnim = new Animation(img, 200);
		monkeyAnim.setAutoUpdate(true);
		random = new Random();
	}

	public void tick(GameContainer container, int delta) {
		if (Level.score > 0) {
			if (random.nextInt(200) == 0) {
				Level.getCurrentLevel().addEntity(new Barrel(x, y));
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
			monkeyAnim.draw(x, y);
		} else {
			Art.monkey.getSubImage(0, 0).draw(x, y);
		}
	}
}

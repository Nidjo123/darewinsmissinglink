package com.ld48.nidjo123.ld24.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;

public class Barrel extends Entity {
	private Animation barrelAnim;

	private int dx = 2, dy = 3;
	private Circle bb;
	private boolean dead = false;

	public Barrel(int x, int y) {
		super(x, y);
		barrelAnim = new Animation(Art.barrel, 50);
		bb = new Circle(x + 7, y + 9, 10);
	}

	public void tick(GameContainer container, int delta) {

		if (dead)
			deadTicks++;

		if (!dead) {
			x -= dx * delta * 0.05;

			if (x <= -32) {
				Level.getCurrentLevel().removeEntity(this);
				if (Level.score > 0)
					Level.score--;
			}

			if (y < Game.HEIGHT - 32) {
				y += dy * delta * 0.1;
			}

			bb.setLocation(x + 7, y + 9);
		} else if (deadTicks >= 30) {
			Level.entities.remove(this);
		}
	}

	public void render(GameContainer container) {
		if (dead) {
			Art.sheet.getSubImage(4, 0).draw(x, y);
		} else {
			barrelAnim.draw(x, y);
		}
	}

	public void die() {
		if (dead)
			return;
		Level.score++;
		Sound.crash.play();
		dead = true;
	}

	public Shape getBB() {
		return bb;
	}
}

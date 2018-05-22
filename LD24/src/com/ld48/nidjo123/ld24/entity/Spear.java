package com.ld48.nidjo123.ld24.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;
import com.ld48.nidjo123.ld24.level.Level4;

public class Spear extends Entity {
	private Rectangle bb;
	private int dx = 2;
	private boolean left = false;

	public Spear(int x, int y, boolean left) {
		super(x, y);
		bb = new Rectangle(x, y + 14, 32, 6);
		this.left = left;
	}

	public void tick(GameContainer container, int delta) {
		if (!dead) {
			if (left)
				x -= delta * 0.1 * dx;
			else
				x += delta * 0.1 * dx;

			if (x < -32) {
				Level.getCurrentLevel().removeEntity(this);
			}

			bb.setLocation(x, y + 14);
		} else {
			deadTicks++;
			if (deadTicks >= 30)
				Level.getCurrentLevel().removeEntity(this);
		}
	}

	public void render(GameContainer container) {
		if (left) {
			if (dead)
				Art.sheet.getSubImage(6, 0).draw(x, y);
			else
				Art.sheet.getSubImage(5, 0).draw(x, y);
		} else {
			if (dead)
				Art.sheet.getSubImage(6, 0).getFlippedCopy(true, false).draw(x, y);
			else
				Art.sheet.getSubImage(5, 0).getFlippedCopy(true, false).draw(x, y);
		}
	}

	public void die() {
		if (dead)
			return;
		dead = true;

		if (left && Level.score > 0 && !(Level.getCurrentLevel() instanceof Level4))
			Level.score--;
		else if (left && (Level.getCurrentLevel() instanceof Level4))
			Level.score++;

		Sound.spear.play();
	}

	public boolean isDangerous() {
		if (left)
			return true;
		return false;
	}

	public Shape getBB() {
		return bb;
	}
}

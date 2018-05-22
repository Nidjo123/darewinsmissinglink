package com.ld48.nidjo123.ld24.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class Entity {
	public int x, y;
	private Shape shape;
	protected boolean dead = false;
	protected int deadTicks = 0;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		shape = new Rectangle(x, y, 32, 32);
	}

	public void tick(GameContainer container, int delta) {

	}

	public void render(GameContainer container) {

	}

	public void die() {
		dead = true;
	}

	public Shape getBB() {
		return shape;
	}
}

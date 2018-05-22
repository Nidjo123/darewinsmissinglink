package com.ld48.nidjo123.ld24.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;
import com.ld48.nidjo123.ld24.level.Level4;

public class Player extends Entity {
	private boolean wasJumping = false, onGround = false, canJump = false;
	private int jumpTicks = 0;
	private float dy = 2;
	private Animation darwin_old, darwin_young, darwin_youngest, darwin_elder;
	private Rectangle bb = new Rectangle(x + 8, y, 32 - 10, 32);
	private int age = 1;
	private Animation[] anim = new Animation[4];
	private int spearCooldownTicks = 0;

	public Player(int x, int y, int age) {
		super(x, y);

		this.age = age;

		darwin_youngest = new Animation(Art.darwin_youngest, 80);
		darwin_youngest.setAutoUpdate(true);
		anim[0] = darwin_youngest;

		darwin_young = new Animation(Art.darwin_young, 100);
		darwin_young.setAutoUpdate(true);
		anim[1] = darwin_young;

		darwin_elder = new Animation(Art.darwin_elder, 150);
		darwin_elder.setAutoUpdate(true);
		anim[2] = darwin_elder;

		darwin_old = new Animation(Art.darwin_old, 200);
		darwin_old.setAutoUpdate(true);
		anim[3] = darwin_old;
	}

	public void tick(GameContainer container, int delta) {
		Input input = container.getInput();

		onGround = (y >= 200 - 36);

		bb.setLocation(x + 5, y);

		if (!wasJumping)
			dy = 2;

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && canJump) {
			jumpTicks++;
			if (dy < 5 && !(y + dy < 20))
				dy *= delta * 0.1 * 1.1;
			else if (y + dy < 25 && dy < 5) {
				dy *= delta * 0.1 * 0.75;
			}

			y -= dy;

			wasJumping = true;

			if (onGround)
				Sound.jump.play();

		} else if (y < 200 - 32 - 4) {
			if (dy < 5)
				dy *= delta * 0.1 * 0.95;
			y += dy;
			wasJumping = false;
		}

		if (spearCooldownTicks > 0)
			spearCooldownTicks--;

		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && Level.getCurrentLevel() instanceof Level4 && spearCooldownTicks <= 0) {
			Level.getCurrentLevel().addEntity(new Spear(x, y, false));
			spearCooldownTicks = 55;
		}

		if (jumpTicks >= 60 || onGround) {
			jumpTicks = 0;
			dy = 2;
		}

		if (y > 200 - 32) {
			y = 200 - 32;
		}

		canJump = onGround || wasJumping && y - dy > 20;

		for (int i = 0; i < Level.entities.size(); i++) {
			Entity e = (Entity) Level.entities.get(i);

			if (!e.dead
			        && (this.collides(e.getBB()) && (e instanceof Barrel || (e instanceof Spear && ((Spear) e).isDangerous()) || e instanceof Rock))) {
				e.die();
			}
		}
	}

	public void render(GameContainer container) {
		if (onGround)
			anim[age - 1].draw(x, y);
		else
			Art.sheet.getSubImage(age - 1, 0).draw(x, y);
	}

	public boolean collides(Shape s) {
		if (this.bb.intersects(s))
			return true;
		return false;
	}

}

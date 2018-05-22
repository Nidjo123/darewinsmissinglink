package com.ld48.nidjo123.ld24.level;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.entity.Entity;
import com.ld48.nidjo123.ld24.menu.FinalMenu;
import com.ld48.nidjo123.ld24.menu.Menu;

public class Level {
	public static ArrayList<Entity> entities;
	private static Level current;
	protected static int bgx = 0;
	private static int numLevel = 1;
	public static int score = 10;
	public static String hint = "";
	private static int levelUpTicks;
	private static boolean levelUp;

	public Level() {
	}

	public static void init() {
		bgx = 0;
		score = 10;
		entities = new ArrayList<Entity>();
		switch (numLevel) {
		case 1:
			Level1 level1 = new Level1();
			current = level1;
			break;
		case 2:
			Level2 level2 = new Level2();
			current = level2;
			break;
		case 3:
			Level3 level3 = new Level3();
			current = level3;
			break;
		case 4:
			Level4 level4 = new Level4();
			current = level4;
			break;
		}
	}

	public static void tick(GameContainer container, int delta) {
		if (numLevel > 4 && Menu.isNull()) {
			Menu.menu = new FinalMenu();
		}

		if (levelUpTicks > 0)
			levelUpTicks--;
		else if (levelUpTicks <= 0 && levelUp) {
			init();
			levelUp = false;
		} else {
			current.tickLevel(container, delta);
		}

		for (int i = 0; i < entities.size(); i++) {
			((Entity) entities.get(i)).tick(container, delta);
		}

		bgx -= delta * 0.1 * 2;

		if (bgx <= -Game.WIDTH)
			bgx = 0;
	}

	protected void tickLevel(GameContainer container, int delta) {
	}

	protected void renderLevel(GameContainer container, Graphics g) {
	}

	public static void render(GameContainer container, Graphics g) {
		current.renderLevel(container, g);

		for (int i = 0; i < entities.size(); i++) {
			((Entity) entities.get(i)).render(container);
		}

		g.scale(1, 1);

		g.drawString("Score: " + score, 10, 3);
	}

	public static Level getCurrentLevel() {
		return current;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public static void oneUp() {
		if (numLevel < 5)
			numLevel++;
		levelUpTicks = 200;
		levelUp = true;

		Sound.oneUp.play();
	}

	public static void restart() {
		numLevel = 1;
	}
}

package com.ld48.nidjo123.ld24.level;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.entity.Monkey;
import com.ld48.nidjo123.ld24.entity.Player;

public class Level1 extends Level {
	private Monkey monkey;
	private Player player;
	private Rectangle rect;

	public Level1() {
		monkey = new Monkey(320 - 36, 60);
		player = new Player(50, 200 - 36, 1);
		entities.add(monkey);
		entities.add(player);
		rect = new Rectangle(320 - 40, 60 + 32, 36, 200 - 60 - 32 + 1);
		hint = "Don't let that monkey hit you with a barrel!";
	}

	public void tickLevel(GameContainer container, int delta) {
		if (score <= 0) {
			Level.oneUp();
		}
	}

	public void renderLevel(GameContainer container, Graphics g) {
		if (bgx == 0) {
			Art.bg1.draw(bgx, 0);
		} else {
			Art.bg1.draw(bgx, 0);
			Art.bg1.draw(Game.WIDTH + bgx, 0);
		}

		g.setColor(Color.gray);

		ShapeRenderer.fill(rect);
	}
}

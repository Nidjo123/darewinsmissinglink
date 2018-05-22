package com.ld48.nidjo123.ld24.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.entity.Alien;
import com.ld48.nidjo123.ld24.entity.Player;

public class Level4 extends Level {
	private Player player;
	private Alien alien;
	private int bgx2 = 0;
	private int ticks = 0;

	public Level4() {
		entities.clear();
		player = new Player(50, 200 - 36, 4);
		alien = new Alien(320 - 36, 60);
		entities.add(player);
		entities.add(alien);
		hint = "Hit that alien bastard with spears! (right click)";
	}

	public void tickLevel(GameContainer container, int delta) {
		if (score <= 0)
			Level.oneUp();

		ticks++;

		if (ticks % 2 == 0) {
			bgx2 -= delta * 0.1 * 2;
		}

		if (bgx2 < -Game.WIDTH)
			bgx2 = 0;
	}

	public void renderLevel(GameContainer container, Graphics g) {
		if (bgx2 == 0) {
			Art.bg4_1.draw(bgx2, 0);
		} else {
			Art.bg4_1.draw(bgx2, 0);
			Art.bg4_1.draw(Game.WIDTH + bgx2, 0);
		}

		if (bgx == 0) {
			Art.bg4_2.draw(bgx, 0);
		} else {
			Art.bg4_2.draw(bgx, 0);
			Art.bg4_2.draw(Game.WIDTH + bgx, 0);
		}
	}
}

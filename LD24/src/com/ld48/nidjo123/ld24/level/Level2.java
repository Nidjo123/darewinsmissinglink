package com.ld48.nidjo123.ld24.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.entity.Caveman;
import com.ld48.nidjo123.ld24.entity.Player;

public class Level2 extends Level {
	private Player player;
	private Caveman caveman;

	public Level2() {
		entities.clear();
		player = new Player(50, 200 - 36, 2);
		caveman = new Caveman(320 - 36, 60);
		entities.add(player);
		entities.add(caveman);
		hint = "Collect spears 'cause they might help you in future!";
	}

	public void tickLevel(GameContainer container, int delta) {
		if (score <= 0)
			Level.oneUp();
	}

	public void renderLevel(GameContainer container, Graphics g) {
		if (bgx == 0) {
			Art.bg2.draw(bgx, 0);
		} else {
			Art.bg2.draw(bgx, 0);
			Art.bg2.draw(Game.WIDTH + bgx, 0);
		}

		for (int i = 0; i <= Game.HEIGHT / 32; i++) {
			Art.sheet.getSubImage(7, 0).draw(Game.WIDTH - 35, i * 32);
		}
	}
}

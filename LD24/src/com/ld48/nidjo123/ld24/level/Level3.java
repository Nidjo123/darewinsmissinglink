package com.ld48.nidjo123.ld24.level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.entity.Player;
import com.ld48.nidjo123.ld24.entity.Spaceman;

public class Level3 extends Level {
	private Player player;
	private Spaceman spaceman;
	private Animation spacecraftAnim;

	public Level3() {
		entities.clear();
		player = new Player(50, 200 - 36, 3);
		spaceman = new Spaceman(320 - 36, 60);
		entities.add(player);
		entities.add(spaceman);
		Image[] img = { Art.sheet.getSubImage(7, 1), Art.sheet.getSubImage(8, 1) };
		spacecraftAnim = new Animation(img, 100);
		spacecraftAnim.setAutoUpdate(true);
		hint = "Don't let that astronaut hit you with a moon rock!";
	}

	public void tickLevel(GameContainer container, int delta) {
		if (score <= 0)
			Level.oneUp();
	}

	public void renderLevel(GameContainer container, Graphics g) {
		if (bgx == 0) {
			Art.bg3.draw(bgx, 0);
		} else {
			Art.bg3.draw(bgx, 0);
			Art.bg3.draw(Game.WIDTH + bgx, 0);
		}

		spacecraftAnim.draw(320 - 36, 60 + 31);
	}
}

package com.ld48.nidjo123.ld24.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.Sound;

public class PauseMenu extends Menu {
	private static String[] options = { "Resume", "About", "Exit" };
	private int bit = 0;
	private int ticks = 0;
	private float bgx = 0;

	public void tickMenu(GameContainer container, int delta) {
		Input input = container.getInput();

		bgx -= delta * 0.1f * 0.8f;

		if (bgx <= -Game.WIDTH)
			bgx = 0;

		if (ticks > 0)
			ticks--;

		if (ticks <= 0 && input.isKeyDown(Input.KEY_DOWN)) {
			bit++;
			ticks = 8;
			Sound.menu.play();
		}

		if (ticks <= 0 && input.isKeyDown(Input.KEY_UP)) {
			bit--;
			ticks = 8;
			Sound.menu.play();
		}

		if (bit >= options.length)
			bit = 0;

		if (bit < 0)
			bit = options.length - 1;

		if (input.isKeyDown(Input.KEY_ENTER)) {
			Sound.menuChosen.play();

			switch (bit) {
			case 0:
				Menu.menu =null;
				break;
			case 1:
				Menu.menu = new AboutMenu(true);
				break;
			case 2:
				container.exit();
				break;
			}
		}
	}

	public void renderMenu(GameContainer container, Graphics g) {
		g.scale(3, 3);
		g.setColor(Color.white);

		if (bgx == 0)
			Art.bg4_1.draw(bgx, 0);
		else {
			Art.bg4_1.draw(bgx, 0);
			Art.bg4_1.draw(Game.WIDTH + bgx, 0);
		}

		Art.link.draw(Game.WIDTH / 2 - 32 - 20, Game.HEIGHT / 3 - 52);
		Art.sheet.getSubImage(3, 0).draw(Game.WIDTH / 2 - 15, Game.HEIGHT / 3 - 26);

		for (int i = 0; i < options.length; i++) {
			if (bit == i)
				g.drawString(">" + options[i], Game.WIDTH / 2 - 23, Game.HEIGHT / 2 + 18 * i);
			else
				g.drawString(options[i], Game.WIDTH / 2 - 23, Game.HEIGHT / 2 + 18 * i);
		}
	}
}

package com.ld48.nidjo123.ld24.menu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.ld48.nidjo123.ld24.Art;
import com.ld48.nidjo123.ld24.Game;
import com.ld48.nidjo123.ld24.Sound;
import com.ld48.nidjo123.ld24.level.Level;

public class FinalMenu extends Menu {
	private String[] text = { "Congrats! You passed the game. Let me tell you a", "little secret.", "", "Darewin was looking for the missing link.",
	        "He just couldn't find it. And you know where it was?", "", "It was in our hearts.", "It was in our computers.",
	        "It was in our games...", "", "", "Press Escape for the main menu or Enter to restart." };
	private Animation anim = new Animation(Art.darwin_old, 300);
	private float bgx = 0;

	public void tickMenu(GameContainer container, int delta) {
		Input input = container.getInput();

		anim.update(delta);

		bgx += delta * 0.1f * 1;

		if (bgx > Game.WIDTH) {
			bgx = -32;
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			Menu.menu = new MainMenu();
			Sound.menuChosen.play();
		} else if (input.isKeyDown(Input.KEY_ENTER)) {
			Level.restart();
			Level.init();
			Menu.menu = null;
			Sound.menuChosen.play();
		}
	}

	public void renderMenu(GameContainer container, Graphics g) {
		g.scale(2f, 2f);

		for (int i = 0; i < text.length; i++) {
			if (i >= 6 && i <= 9)
				g.setColor(Color.red);
			else
				g.setColor(Color.white);

			g.drawString(text[i], 5, i * 15);
		}

		g.scale(Game.SCALE - 1, Game.SCALE - 1);

		anim.draw(bgx, 100);
	}
}

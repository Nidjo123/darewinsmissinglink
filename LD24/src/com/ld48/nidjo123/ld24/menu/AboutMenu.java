package com.ld48.nidjo123.ld24.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.ld48.nidjo123.ld24.Game;

public class AboutMenu extends Menu {
	private String[] text = { "Darewin's Missing Link is a game made for 24th Ludum", "Dare Compo in 48 hours. It was made by",
	        "Nikola Bunjevac (Nidjo123).", "", "In the game you must jump. And you must jump A LOT.",
	        "Oh, and also, you should not get hit by something", "unless it's required. ;D If you somehow manage to",
	        "get hit, your score gets worse by one.", "", "Use only mouse for controlling the Darewin. Jump", "with left button, shoot with right.",
	        "", "", "*Darewin is on purpose!" };

	private boolean playing;

	public AboutMenu(boolean playing) {
		this.playing = playing;
	}

	public void tickMenu(GameContainer container, int delta) {
		Input input = container.getInput();

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			if (!playing)
				Menu.menu = new MainMenu();
			else
				Menu.menu = new PauseMenu();
		}
	}

	public void renderMenu(GameContainer container, Graphics g) {
		g.scale(2f, 2f);

		for (int i = 0; i < text.length; i++) {
			g.drawString(text[i], 5, i * 20);
		}
	}
}

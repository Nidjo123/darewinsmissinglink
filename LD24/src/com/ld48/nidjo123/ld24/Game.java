package com.ld48.nidjo123.ld24;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.AppletGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;

import com.ld48.nidjo123.ld24.level.Level;
import com.ld48.nidjo123.ld24.menu.MainMenu;
import com.ld48.nidjo123.ld24.menu.Menu;
import com.ld48.nidjo123.ld24.menu.PauseMenu;

public class Game extends BasicGame {

	public static final Game game = new Game();
	public static final int WIDTH = 320;
	public static final int HEIGHT = 200;
	public static final int SCALE = 3;
	public int ticks = 0, timePassed = 0;
	private Level level;
	public static AppGameContainer container;
	private static final String name = "Darewin's Missing Link";

	private Rectangle upperBlock = new Rectangle(0, 0, 320 + 1, 20);

	public Game() {
		super(name);
	}

	public static Game getGame() {
		return game;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (Menu.isNull()) {
			g.scale(SCALE, SCALE);

			Level.render(container, g);

			g.setColor(new Color(0.2f, 0.3f, 0.95f));

			ShapeRenderer.fill(upperBlock);

			g.scale(0.5f, 0.5f);
			g.setColor(Color.black);

			g.drawString("Left: " + Level.score + " |", 10, 8);
			g.drawString(Level.hint, 110, 8);
		} else {
			Menu.menu.render(container, g);
		}
	}

	public void init(GameContainer container) throws SlickException {
		if (container instanceof AppletGameContainer.Container) { 
			container.setTargetFrameRate(60);
			container.setAlwaysRender(true);
			container.setSmoothDeltas(true);
			container.setShowFPS(false);
		}
		
		level = new Level();
		level.init();
		Menu.menu = new MainMenu();
	}

	public void update(GameContainer container, int delta) throws SlickException {
		if (container.hasFocus()) {
			if (Menu.menu == null && container.getInput().isKeyDown(Input.KEY_ESCAPE)) {
				Menu.menu = new PauseMenu();
			}

			// if (!menu.isNull() &&
			// container.getInput().isKeyDown(Input.KEY_ESCAPE)) {
			// menu.setMenu(new MainMenu());
			// }

			if (Menu.menu == null)
				Level.tick(container, delta);
			else {
				Menu.tick(container, delta);
			}
		}
	}

	public static void main(String[] args) {
		try {
			container = new AppGameContainer(game, WIDTH * SCALE, HEIGHT * SCALE, false);
			container.setTitle("Darewin's Missing Link");
			// container.setVSync(true);
			container.setTargetFrameRate(60);
			container.setAlwaysRender(true);
			container.setSmoothDeltas(true);
			container.setShowFPS(false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

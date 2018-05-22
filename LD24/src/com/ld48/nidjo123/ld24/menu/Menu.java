package com.ld48.nidjo123.ld24.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Menu {
	public static Menu menu;

	//public static void setMenu(Menu menu) {
	//	this.menu = menu;
	//}

	public static void tick(GameContainer container, int delta) {
		menu.tickMenu(container, delta);
	}

	public static void render(GameContainer container, Graphics g) {
		menu.renderMenu(container, g);
	}

	protected void tickMenu(GameContainer container, int delta) {
		
	}
	
	protected void renderMenu(GameContainer container, Graphics g) {
		
	}
	
	public static boolean isNull() {
		if (menu == null)
			return true;
		return false;
	}
}

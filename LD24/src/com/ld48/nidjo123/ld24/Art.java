package com.ld48.nidjo123.ld24;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Art {
	public static SpriteSheet sheet = loadSheet("res/spritesheet.png", 32, 32);
	public static SpriteSheet darwin_old = loadSheet("res/darwin_old.png", 32, 32);
	public static SpriteSheet darwin_young = loadSheet("res/darwin_young.png", 32, 32);
	public static SpriteSheet darwin_youngest = loadSheet("res/darwin_youngest.png", 32, 32);
	public static SpriteSheet darwin_elder = loadSheet("res/darwin_elder.png", 32, 32);
	public static SpriteSheet monkey = loadSheet("res/monkey.png", 32, 32);
	public static SpriteSheet barrel = loadSheet("res/barrel.png", 32, 32);
	public static SpriteSheet caveman = loadSheet("res/caveman.png", 32, 32);
	public static SpriteSheet spaceman = loadSheet("res/spaceman.png", 32, 32);

	public static Image bg1 = loadImage("res/bg1.png");
	public static Image bg2 = loadImage("res/bg2.png");
	public static Image bg3 = loadImage("res/bg3.png");
	public static Image bg4_1 = loadImage("res/bg4_1.png");
	public static Image bg4_2 = loadImage("res/bg4_2.png");
	public static Image link = loadImage("res/link.png");

	private static SpriteSheet loadSheet(String name, int w, int h) {
		SpriteSheet sheet;
		try {
			sheet = new SpriteSheet(name, w, h);
			sheet.setFilter(Image.FILTER_NEAREST);
			return sheet;
		} catch (SlickException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Image loadImage(String name) {
		try {
			Image img = new Image(name);
			img.setFilter(Image.FILTER_NEAREST);
			return img;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
}

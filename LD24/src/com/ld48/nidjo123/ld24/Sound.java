package com.ld48.nidjo123.ld24;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Sound jump = loadSound("/res/jump.wav");
	public static Sound menu = loadSound("/res/menu.wav");
	public static Sound menuChosen = loadSound("/res/menuChosen.wav");
	public static Sound crash = loadSound("/res/crash.wav");
	public static Sound rock = loadSound("/res/rock.wav");
	public static Sound oneUp = loadSound("/res/oneUp.wav");
	public static Sound spear = loadSound("/res/spear.wav");
	public static Sound shoot = loadSound("/res/shoot.wav");

	private static Sound loadSound(String string) {
		Sound sound = new Sound();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Game.class.getResource(string));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			sound.clip = clip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sound;
	}

	private Clip clip;

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

package com.bearcavestudios.tilerpg.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 32, HEIGHT = 32;
	public static BufferedImage grass, rock, dirt, player;
	

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/forest.png"));
		grass = sheet.crop(0, 0 * HEIGHT, WIDTH, HEIGHT);
		rock = sheet.crop(32, 0, WIDTH, HEIGHT);
		dirt = sheet.crop(64, 0, WIDTH, HEIGHT);
		// change to a different sprite later on
		player = sheet.crop(0, 64, WIDTH, HEIGHT);
	}
}

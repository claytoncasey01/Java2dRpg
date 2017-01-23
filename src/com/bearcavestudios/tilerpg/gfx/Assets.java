package com.bearcavestudios.tilerpg.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 32, HEIGHT = 32;
	public static BufferedImage grass, stone, stump;
	

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/forest.png"));
		grass = sheet.crop(0, 0, WIDTH, HEIGHT);
		stone = sheet.crop(32, 0, WIDTH, HEIGHT);
		stump = sheet.crop(64, 0, WIDTH, HEIGHT);
	}
}

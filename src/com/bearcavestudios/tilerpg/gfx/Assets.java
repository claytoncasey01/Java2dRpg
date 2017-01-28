package com.bearcavestudios.tilerpg.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 32, HEIGHT = 32;
	private static final int PLAYER_WIDTH = 64, PLAYER_HEIGHT = 64;
	public static BufferedImage grass, rock, dirt;
	public static BufferedImage[] player_idle, player_up, player_down, player_left, player_right;
	

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/forest.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/character.png"));
		grass = sheet.crop(0, 0, WIDTH, HEIGHT);
		rock = sheet.crop(32 * 1, 0, WIDTH, HEIGHT);
		dirt = sheet.crop(32 * 2, 0, WIDTH, HEIGHT);
		
		// Player down animations
		player_idle = new BufferedImage[1];
		player_up = new BufferedImage[9];
		player_down = new BufferedImage[9];
		player_left = new BufferedImage[9];
		player_right = new BufferedImage[9];
		
		player_idle[0] = playerSheet.crop(0, 2 * PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		// Handle player animation sprites.
		for(int i = 0; i < player_down.length; i++) {
			player_up[i] = playerSheet.crop(i * PLAYER_WIDTH, 8 * PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
			player_down[i] = playerSheet.crop(i * PLAYER_WIDTH, 10 * PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
			player_left[i] = playerSheet.crop(i * PLAYER_WIDTH, 9 * PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
			player_right[i] = playerSheet.crop(i * PLAYER_WIDTH, 11 * PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		}

	}
}

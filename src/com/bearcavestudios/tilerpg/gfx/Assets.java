package com.bearcavestudios.tilerpg.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 32, HEIGHT = 32;
	private static final int PLAYER_WIDTH = 64, PLAYER_HEIGHT = 64;
	public static BufferedImage grass, rock, dirt, grassPatch, tree;
	public static BufferedImage[] player_idle, player_up, player_down, player_left, player_right;
	public static BufferedImage[] player_attack_up, player_attack_down, player_attack_left, player_attack_right;
	public static BufferedImage[] btn_start;
	

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/forest.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/character.png"));
		SpriteSheet uiSheet = new SpriteSheet(ImageLoader.loadImage("/textures/UI.png"));
		grass = sheet.crop(0, 0, WIDTH, HEIGHT);
		rock = sheet.crop(32 * 1, 0, WIDTH, HEIGHT);
		dirt = sheet.crop(32 * 2, 0, WIDTH, HEIGHT);
		grassPatch = sheet.crop(32 * 3, 0, WIDTH, HEIGHT);
		tree = sheet.crop(WIDTH, HEIGHT, WIDTH * 2, HEIGHT * 3);
		
		// UI
		btn_start = new BufferedImage[2];
		btn_start[0] = uiSheet.crop(0, 0, WIDTH * 2, HEIGHT);
		btn_start[1] = uiSheet.crop(WIDTH * 2, 0, WIDTH * 2, HEIGHT);
		
		// Player move animations
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
		
		// Player attack animations
		player_attack_up = new BufferedImage[5];
		player_attack_down = new BufferedImage[5];
		player_attack_left = new BufferedImage[5];
		player_attack_right = new BufferedImage[5];
		
		// Attack up
		player_attack_up[0] = playerSheet.crop(64, 1408, 128, 128);
		player_attack_up[1] = playerSheet.crop(64 * 4, 1408, 128, 128);
		player_attack_up[2] = playerSheet.crop(64 * 10, 1408, 128, 128);
		player_attack_up[3] = playerSheet.crop(64 * 13, 1408, 128, 128);
		player_attack_up[4] = playerSheet.crop(64 * 16, 1408, 128, 128);
		
		// Attack down
		player_attack_down[0] = playerSheet.crop(64, 1792, 128, 128);
		player_attack_down[1] = playerSheet.crop(64 * 4, 1792, 128, 128);
		player_attack_down[2] = playerSheet.crop(64 * 10, 1792, 128, 128);
		player_attack_down[3] = playerSheet.crop(64 * 13, 1792, 128, 128);
		player_attack_down[4] = playerSheet.crop(64 * 16, 1792, 128, 128);
		
		// Attack Left
		player_attack_left[0] = playerSheet.crop(64, 1600, 192, 128);
		player_attack_left[1] = playerSheet.crop(64 * 4, 1600, 192, 128);
		player_attack_left[2] = playerSheet.crop(64 * 10, 1600, 192, 128);
		player_attack_left[3] = playerSheet.crop(64 * 13, 1600, 192, 128);
		player_attack_left[4] = playerSheet.crop(64 * 16, 1600, 192, 128);
		

	}
}

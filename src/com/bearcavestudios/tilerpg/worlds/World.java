package com.bearcavestudios.tilerpg.worlds;

import java.awt.Graphics;
import java.util.Random;

import com.bearcavestudios.tilerpg.Game;
import com.bearcavestudios.tilerpg.tiles.Tile;
import com.bearcavestudios.tilerpg.utils.Utils;

public class World {
	
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Game game;
	
	public World(Game game, String path) {
		this.game = game;
		//loadWorld(path);
		generateWorld();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		int xStart = (int)Math.max(0, game.getCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int)Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int)Math.max(0, game.getCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int)Math.min(height, (game.getCamera().getyOffset() + game.getHeight()) / Tile.TILE_HEIGHT + 1) ;
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - game.getCamera().getxOffset()), 
						(int) (y * Tile.TILE_HEIGHT - game.getCamera().getyOffset()));
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.dirtTile;
		}
		
		return t;
	}
	
	// Loads a world from a file
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	// Generates a randomized world
	// this is just temporary
	private void generateWorld() {
		Random rand = new Random();
		width = 100;
		height = 100;
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int num = rand.nextInt(100) + 1;
				if( num > 0 && num <= 90) {
					tiles[x][y] = 0;
				} else if(num > 90 && num <= 98) {
					tiles[x][y] = 2;
				} else {
					tiles[x][y] = 1;
				}
			}
		}
	}


}

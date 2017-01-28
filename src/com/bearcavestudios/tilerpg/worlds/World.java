package com.bearcavestudios.tilerpg.worlds;

import java.awt.Graphics;

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
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
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


}

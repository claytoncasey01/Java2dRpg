package com.bearcavestudios.tilerpg.worlds;

import java.awt.Graphics;
import java.util.Random;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.entities.EntityManager;
import com.bearcavestudios.tilerpg.entities.creatures.Player;
import com.bearcavestudios.tilerpg.entities.statics.Tree;
import com.bearcavestudios.tilerpg.gfx.Assets;
import com.bearcavestudios.tilerpg.tiles.Tile;
import com.bearcavestudios.tilerpg.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	// Entities
	private EntityManager entityManager;
	
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		loadWorld(path);
		//generateWorld();
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int)Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int)Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int)Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int)Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1) ;
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()), 
						(int) (y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
			}
		}
		
		// Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		// Make sure x and y are inside of the map
		// if not just use grass tile so we don't crash.
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
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
				if(x == width - 1 || y == height - 1 || x == 0 || y == 0) { // Generate walls around map
					tiles[x][y] = 1;
				} else if(num > 0 && num <= 95) {
					tiles[x][y] = 0;
				} else if(num > 95 && num <= 98){
					tiles[x][y] = 2;
				} else {
					tiles[x][y] = 1;
 				}
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}


}

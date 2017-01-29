package com.bearcavestudios.tilerpg.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles =  new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile stoneTile = new StoneTile(1);
	public static Tile dirtTile = new DirtTile(2);
	
	public static final int TILE_WIDTH = 32,
							TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	protected boolean solid = false;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public void setSolid(boolean sol) {
		solid = sol;
	}
	
	public int getId() {
		return id;
	}
}

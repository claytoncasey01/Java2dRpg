package com.bearcavestudios.tilerpg.entities.statics;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.gfx.Assets;
import com.bearcavestudios.tilerpg.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH * 4, Tile.TILE_HEIGHT * 6);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO: Add in tree sprites
		g.drawImage(Assets.tree, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
		
	}

}

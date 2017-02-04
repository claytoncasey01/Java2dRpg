package com.bearcavestudios.tilerpg.entities.statics;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.gfx.Assets;
import com.bearcavestudios.tilerpg.tiles.Tile;

public class GrassPatch extends StaticEntity {

	public GrassPatch(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.grassPatch,(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
	}
	
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}

package com.bearcavestudios.tilerpg.tiles;

import com.bearcavestudios.tilerpg.gfx.Assets;

public class StumpTile extends Tile {
	
	public StumpTile(int id) {
		super(Assets.stump, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}

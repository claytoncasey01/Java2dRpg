package com.bearcavestudios.tilerpg.tiles;

import com.bearcavestudios.tilerpg.gfx.Assets;

public class StoneTile extends Tile {
	
	public StoneTile(int id) {
		super(Assets.rock, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}

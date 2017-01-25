package com.bearcavestudios.tilerpg.entities.creatures;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.gfx.Assets;

public class Player extends Creature {

	public Player(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)x, (int)y, null);
		
	}

}

package com.bearcavestudios.tilerpg.states;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.gfx.Assets;

public class GameState extends State {
	
	public GameState() {
		
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.stone, 0, 0, null);
		
	}

	
}

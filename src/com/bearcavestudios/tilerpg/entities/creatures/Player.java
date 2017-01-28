package com.bearcavestudios.tilerpg.entities.creatures;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Game;
import com.bearcavestudios.tilerpg.gfx.Assets;

public class Player extends Creature {
	

	public Player(Game game, float x, float y) {
		super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	}

	@Override
	public void tick() {
		getInput();
		move();
		
		// Center game camera on player
		game.getCamera().centerOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - game.getCamera().getxOffset()), 
				(int)(y - game.getCamera().getyOffset()), width, height, null);
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up) {
			yMove = -speed;
		}
		if(game.getKeyManager().down) {
			yMove = speed;
		}
		if(game.getKeyManager().left) {
			xMove = -speed;
		}
		if(game.getKeyManager().right) {
			xMove = speed;
		}
	}

}

package com.bearcavestudios.tilerpg.entities.creatures;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.gfx.Assets;

public class Player extends Creature {
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	}

	@Override
	public void tick() {
		getInput();
		move();
		
		// Center game camera on player
		handler.getCamera().centerOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - handler.getCamera().getxOffset()), 
				(int)(y - handler.getCamera().getyOffset()), width, height, null);
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		if(handler.getKeyManager().down) {
			yMove = speed;
		}
		if(handler.getKeyManager().left) {
			xMove = -speed;
		}
		if(handler.getKeyManager().right) {
			xMove = speed;
		}
	}

}

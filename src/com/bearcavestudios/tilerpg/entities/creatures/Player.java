package com.bearcavestudios.tilerpg.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.gfx.Assets;

public class Player extends Creature {
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		// Setup bounds for collision
		bounds.x = 6;
		bounds.y = 4;
		bounds.width = 24;
		bounds.height = 24;
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
		
		// Test code to show bounding box for collision detection
		/*g.setColor(Color.red);
		g.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);*/
		
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

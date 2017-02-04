package com.bearcavestudios.tilerpg.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.gfx.Animation;
import com.bearcavestudios.tilerpg.gfx.Assets;

public class Player extends Creature {
	
	// Animations
	private Animation animIdle, animUp, animDown, animLeft, animRight;
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 64, 64);
		
		// Setup bounds for collision
		bounds.x = 18;
		bounds.y = 28;
		bounds.width = 28;
		bounds.height = 32;
		
		// Animations
		animIdle = new Animation(0, Assets.player_idle);
		animUp = new Animation(100, Assets.player_up);
		animDown = new Animation(100, Assets.player_down);
		animLeft = new Animation(100, Assets.player_left);
		animRight = new Animation(100, Assets.player_right);
		
	}

	@Override
	public void tick() {
		// Animations
		animIdle.tick();
		animUp.tick();
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		
		// Movement
		getInput();
		move();
		
		// Center game camera on player
		handler.getCamera().centerOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getCamera().getxOffset()), 
				(int)(y - handler.getCamera().getyOffset()), width, height, null);
		
		// Test code to show bounding box for collision detection
		/*g.setColor(Color.red);
		g.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);*/
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		BufferedImage frame;
		
		if(xMove < 0) {
			frame = animLeft.getCurrentFrame();
		} else if(xMove > 0) {
			frame = animRight.getCurrentFrame();
		} else if(yMove < 0) {
			frame = animUp.getCurrentFrame();
		} else if(yMove > 0) {
			frame = animDown.getCurrentFrame();
		} else {
			frame = animIdle.getCurrentFrame();
		}
		
		return frame;
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

package com.bearcavestudios.tilerpg.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.entities.Entity;
import com.bearcavestudios.tilerpg.gfx.Animation;
import com.bearcavestudios.tilerpg.gfx.Assets;

public class Player extends Creature {
	
	// Animations
	private Animation animIdle, animUp, animDown, animLeft, animRight, animAttackUp, animAttackDown, animAttackLeft, animAttackRight;
	
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	

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
		
		// Attack Animations
		animAttackUp = new Animation(200, Assets.player_attack_up);
		animAttackDown = new Animation(200, Assets.player_attack_down);
		animAttackLeft = new Animation(200, Assets.player_attack_left);
		animAttackRight = new Animation(200, Assets.player_attack_right);
		
	}

	@Override
	public void tick() {
		// Animations
		animIdle.tick();
		animUp.tick();
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		
		// Attack Animations
		animAttackUp.tick();
		animAttackDown.tick();
		animAttackLeft.tick();
		animAttackRight.tick();
		
		// Movement
		getInput();
		move();
		
		// Center game camera on player
		handler.getCamera().centerOnEntity(this);
		// Check to see if we are attacking
		// and if so, does it hit anything.
		checkAttacks();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getCamera().getxOffset()), 
				(int)(y - handler.getCamera().getyOffset()), width, height, null);
		
		// This handles our attack animations
		// which require width and height to 
		// be 128.
		if(width > 64 || height > 64) {
			width = 64;
			height = 64;
		}
		
		
		// Test code to show bounding box for collision detection
		/*g.setColor(Color.red);
		g.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);*/
		
	}
	
	@Override
	public void die() {
		// Testing code
		System.out.println("You lose");
		
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
		
		// Attacking Animations
		if(handler.getKeyManager().attack) {
			if(handler.getKeyManager().up) {
				width = 128;
				height = 128;
				frame = animAttackUp.getCurrentFrame();
			} else if(handler.getKeyManager().down) {
				width = 128;
				height = 128;
				frame = animAttackDown.getCurrentFrame();
			} else if(handler.getKeyManager().left) {
				width = 192;
				height = 128;
				frame = animAttackLeft.getCurrentFrame();
			} else if(handler.getKeyManager().right) {
				width = 128;
				height = 128;
				frame = animAttackRight.getCurrentFrame();
			}
		}
		
		return frame;
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up && !handler.getKeyManager().attack) {
			yMove = -speed;
		}
		if(handler.getKeyManager().down && !handler.getKeyManager().attack) {
			yMove = speed;
		}
		if(handler.getKeyManager().left && !handler.getKeyManager().attack) {
			xMove = -speed;
		}
		if(handler.getKeyManager().right && !handler.getKeyManager().attack) {
			xMove = speed;
		}
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().attack && handler.getKeyManager().up) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			
		} else if(handler.getKeyManager().attack && handler.getKeyManager().down) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
			
		} else if(handler.getKeyManager().attack && handler.getKeyManager().left) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			
		} else if(handler.getKeyManager().attack && handler.getKeyManager().right) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(2);
				return;
			}
		}
		
	}

}

package com.bearcavestudios.tilerpg.gfx;

import com.bearcavestudios.tilerpg.Game;
import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.entities.Entity;

public class Camera {
	
	private Game game;
	private float xOffset, yOffset;
	
	public Camera(Game game, float xOffset, float yOffset) {
		this.game = game;
		this.setxOffset(xOffset);
		this.setyOffset(yOffset);
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - game.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() / 2;
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
	}
	
	// Getters and Setters
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}

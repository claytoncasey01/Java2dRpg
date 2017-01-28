package com.bearcavestudios.tilerpg;

import com.bearcavestudios.tilerpg.gfx.Camera;
import com.bearcavestudios.tilerpg.input.KeyManager;
import com.bearcavestudios.tilerpg.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.setGame(game);
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	// Getters and Setters
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}

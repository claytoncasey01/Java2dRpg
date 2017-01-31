package com.bearcavestudios.tilerpg.states;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Handler;
import com.bearcavestudios.tilerpg.entities.creatures.Player;
import com.bearcavestudios.tilerpg.worlds.World;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler,"res/worlds/world1.txt");
		handler.setWorld(world);
		
	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	
}

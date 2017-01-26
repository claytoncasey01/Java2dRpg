package com.bearcavestudios.tilerpg.states;

import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Game;
import com.bearcavestudios.tilerpg.entities.creatures.Player;
import com.bearcavestudios.tilerpg.tiles.Tile;

public class GameState extends State {
	
	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game,100, 100);
	}

	@Override
	public void tick() {
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
		Tile.tiles[0].render(g, 0, 0);
		Tile.tiles[1].render(g, 32, 0);
		Tile.tiles[2].render(g, 64, 0);
		
	}

	
}

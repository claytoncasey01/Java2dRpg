package com.bearcavestudios.tilerpg.states;

import java.awt.Color;
import java.awt.Graphics;

import com.bearcavestudios.tilerpg.Handler;

public class MenuState extends State {
	
	public MenuState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
			State.setState(handler.getGame().gameState);
		}
		System.out.println(
				"Mouse X: " + handler.getMouseManager().getMouseX() + 
				" Mouse Y: " + handler.getMouseManager().getMouseY()
				);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 5, 5);
	}

}

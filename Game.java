package com.bearcavestudios.tilerpg;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.bearcavestudios.tilerpg.Display.Display;

public class Game implements Runnable {
	
	public String title;
	public int width, height;
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	private void init() {
		display = new Display(title, width, height);
	}
	
	private void update() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		// If this is the first run, the canvas
		// will not have a buffer strategy, so create one.
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		// Draw Here!
		g.fillRect(0, 0, width, height);
		
		// End Draw!
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
		while(running) {
			update();
			render();
		}
		
		stop();
		
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

package com.bearcavestudios.tilerpg;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.bearcavestudios.tilerpg.Display.Display;
import com.bearcavestudios.tilerpg.gfx.ImageLoader;
import com.bearcavestudios.tilerpg.gfx.SpriteSheet;

public class Game implements Runnable {
	
	public String title;
	public int width, height;
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;	
	
	private BufferedImage test;
	private SpriteSheet sheet;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	private void init() {
		display = new Display(title, width, height);
		test = ImageLoader.loadImage("/textures/forest.png");
		sheet = new SpriteSheet(test);
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
		// Clear Screen
		g.clearRect(0, 0, width, height);
		
		g.drawImage(sheet.crop(0, 0, 32, 32), 5, 5, null);
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

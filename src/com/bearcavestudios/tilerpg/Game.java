package com.bearcavestudios.tilerpg;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.bearcavestudios.tilerpg.Display.Display;
import com.bearcavestudios.tilerpg.gfx.Assets;
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
	
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	private void init() {
		display = new Display(title, width, height);
		Assets.init();
	}
	
	int x = 0;
	
	private void tick() {
		x += 1;
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
		
		g.drawImage(Assets.grass, x, 10, null);
		
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
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

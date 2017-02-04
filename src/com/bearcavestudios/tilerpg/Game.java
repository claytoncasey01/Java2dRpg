package com.bearcavestudios.tilerpg;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.bearcavestudios.tilerpg.display.Display;
import com.bearcavestudios.tilerpg.gfx.Assets;
import com.bearcavestudios.tilerpg.gfx.Camera;
import com.bearcavestudios.tilerpg.input.KeyManager;
import com.bearcavestudios.tilerpg.input.MouseManager;
import com.bearcavestudios.tilerpg.states.GameState;
import com.bearcavestudios.tilerpg.states.MenuState;
import com.bearcavestudios.tilerpg.states.State;

public class Game implements Runnable {
	
	public String title;
	private int width, height;
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;	
	
	// States
	public State gameState;
	public State menuState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private Camera camera;
	
	// Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		
		// Setup input
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		camera = new Camera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
		//State.setState(gameState);
	}
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();
		}
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
		
		// Get the state of the game 
		// and run its render method
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		
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
	
	// Getters and Setters
	
	public Camera getCamera() {
		return camera;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}

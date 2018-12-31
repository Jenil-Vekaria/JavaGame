package com.gameMaking.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.display.Display;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.gfx.GameCamera;
import com.gameMaking.tilegame.gfx.ImageLoader;
import com.gameMaking.tilegame.gfx.SpriteSheet;
import com.gameMaking.tilegame.input.KeyManager;
import com.gameMaking.tilegame.states.GameState;
import com.gameMaking.tilegame.states.MenuState;
import com.gameMaking.tilegame.states.State;
import com.gameMaking.tilegame.world.Inventory;

public class Game implements Runnable
{
	private Display display;
	private String title;
	private int width,height;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState, menuState;
	
	//Input
	private KeyManager keyManager;
	
	//Camera
	private GameCamera gameCamera;
	
	
	//Handler
	private Handler handler;
	
	
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
	}
	
	
	public void init()
	{
		keyManager = new KeyManager();
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler,0,0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	


	public void update()
	{
		if(State.getState() != null)
			State.getState().tick();
	}
	
	public void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics(); 
		
		//Clear screen
		g.clearRect(0, 0, width, height);
		//Draw Here
		
		if(State.getState().isQuitGame())
			System.exit(0);
		else if(!State.getState().isPlayGame())
		{
			State.getState().render(g);
		}
		else if(State.getState().isPlayGame())
		{
			State.setState(gameState);
			GameState game = (GameState) State.getState();
			game.startMusic();
			State.getState().render(g);
		}
		
		
		
		
		
		
		//End Here
		
		g.dispose();
		bs.show();
	}
	
	
	public void run()
	{
		init();
		
		int fps = 60; //Calling undpate and render 60 times a sec
		double timerPerTick = 1000000000 / fps;//Measuring time in nano seconds
		double delta = 0;//The amount of time we have left before calling update and render
		long now;
		long lastTime = System.nanoTime();//Returns the time in nano that the computer is running at
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now-lastTime) / timerPerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000)
			{
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0 ;
				timer = 0;
			}
		}
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}



	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

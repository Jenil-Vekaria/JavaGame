package com.gameMaking.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation
{
	private int speed, index;
	private long lastTime,timer;
	private BufferedImage[] frames;
	private boolean stopAnimation;
	
	public Animation(int speed, BufferedImage[] bufferedImages){
		this.speed = speed;
		this.frames = bufferedImages;
		this.index = index;
		this.stopAnimation = false;
		timer = 0;
		lastTime = System.currentTimeMillis(); 
	}
	
	
	public void tick()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed)
		{
			index++;
			timer = 0;
			
			if(index >= frames.length && !stopAnimation)
				index = 0;
		}
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void stopAnimation()
	{
			stopAnimation = true;
	}
	
	public void startAnimation()
	{
		stopAnimation = false;
	}
	
	public int getSize()
	{
		return frames.length;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
}

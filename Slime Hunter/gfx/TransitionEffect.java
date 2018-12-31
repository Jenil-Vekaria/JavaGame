package com.gameMaking.tilegame.gfx;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;

public class TransitionEffect
{
	private int startLeft;
	private int startRight;
	private boolean transition = false;
	private double recWidth;
	private int totalRec;
	private Handler handler;
	public TransitionEffect(double width, Handler handler)
	{
		this.handler = handler;
		startLeft = 0;
		startRight = 0;
		recWidth = width;
		totalRec = 768/(int)recWidth;
	}
	
	public void tick()
	{
		if(transition)
		{
			startLeft+= 10;
			startRight-= 10;
		}
		else
			handler.getKeyManager().enableKeyboard();
	}
	
	public void start()
	{
		transition = true;
	}
	
	public void render(Graphics graphics)
	{
		if(transition)
		{
			for(int x = 0; x < totalRec; x++)
			{
				if(x%2 == 0)
					graphics.fillRect(startLeft, x*(int)recWidth, 704, (int)recWidth);
				else
					graphics.fillRect(startRight, x*(int)recWidth, 704, (int)recWidth);
			}
			
		}
		
		if(startLeft == 704 && startRight == 0)
		{
			transition = false;
		}
	}
}

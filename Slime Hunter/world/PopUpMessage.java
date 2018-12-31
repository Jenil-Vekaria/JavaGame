package com.gameMaking.tilegame.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.crypto.Cipher;
import javax.swing.Timer;

import com.gameMaking.tilegame.gfx.Assets;

public class PopUpMessage implements ActionListener
{
	private boolean showmessage;
	private Timer timer;
	private int delay = 0;
	private BufferedImage message;
	
	public PopUpMessage()
	{
		showmessage = false;
		message = null;
		timer = new Timer(1000,this);
	}
	
	public void silverKeyMessage()
	{
		message = Assets.silverkeyMessage;
		showmessage = true;
		delay = 0;
		timer.start();
	}
	
	public void goldKeyMessage()
	{
		message = Assets.goldkeyMessage;
		showmessage = true;
		delay = 0;
		timer.start();
	}
	
	public void blueKeyMessage()
	{
		message = Assets.bluekeyMessage;
		showmessage = true;
		delay = 0;
		timer.start();
	}
	
	public void brownKeyMessage()
	{
		message = Assets.brownkeyMessage;
		showmessage = true;
		delay = 0;
		timer.start();
	}
	
	public void coinMessage()
	{
		message = Assets.coinMessage;
		showmessage = true;
		delay = 0;
		timer.start();
	}
	
	public void render(Graphics graphics)
	{
		if(delay <= 3 && showmessage)
		{
			graphics.drawImage(message,454,641,null);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		delay++;
		if(delay == 3)
		{
			showmessage = false;
			timer.stop();
			delay = 0;
		}
	}
}

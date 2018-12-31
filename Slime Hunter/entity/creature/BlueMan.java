package com.gameMaking.tilegame.entity.creature;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import org.w3c.dom.css.ElementCSSInlineStyle;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Keys;
import com.gameMaking.tilegame.gfx.Assets;

public class BlueMan extends Creature
{
	private BufferedImage character = Assets.blueManRight;
	private boolean ask = false;
	private String mssg1 = "Would You Like To Buy A Key?";
	private String mssg2 = "It Will Cost 5 Coins";
	private String mssg3 = "Yes";
	private String mssg4 = "No";
	public boolean doubleOption = true;
	public boolean redText = false;
	public boolean getKey = false;
	private int whiteBoxPosY = 655;
	public BlueMan(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 64;
		bounds.height = 64;
		
	}

	@Override
	public void tick()
	{
		if(handler.getKeyManager().optionDown)
			whiteBoxPosY = 685;
		else if(handler.getKeyManager().optionUp && doubleOption)
			whiteBoxPosY = 655;
		
		if(whiteBoxPosY == 685 && handler.getKeyManager().optionEnter)
		{
				ask = false;
				handler.getKeyManager().enableKeyboard();
				handler.getKeyManager().optionEnter = false;
				whiteBoxPosY = 655;
		}
		else if(whiteBoxPosY == 655 && handler.getKeyManager().optionEnter)
		{
			if(handler.getInventory().getCoins() >= 5 && doubleOption)
			{
				mssg1 = "Thank you For Buying The Key";
				mssg2 = "";
				mssg3 = "Bye";
				mssg4 = "";
				doubleOption = false;
				getKey = true;
				handler.getInventory().setCoins(handler.getInventory().getCoins()-5);
				handler.getKeyManager().optionEnter = false;
			}
			
			if(handler.getInventory().getCoins() < 5 && doubleOption)
			{
				mssg1 = "Insufficient Coins, Come Back Later";
				mssg2 = "";
				mssg3 = "Ok";
				mssg4 = "";
				doubleOption = false;
				getKey = false;
				handler.getKeyManager().optionEnter = false;
				redText = true;
			}
			
		}
		
		if(whiteBoxPosY == 655 && handler.getKeyManager().optionEnter && !doubleOption)
		{
				ask = false;
				handler.getKeyManager().optionEnter = false;
				if(getKey)
				{
					handler.getInventory().addItemToInventory("Blue Key", new Keys(handler, 0,0,2));
					handler.getInventory().getMessage().blueKeyMessage();
				}
				handler.getKeyManager().enableKeyboard();
		}
		
		if(!ask)
		{
			mssg1 = "Would You Like To Buy A Key?";
			mssg2 = "It Will Cost 5 Coins";
			mssg3 = "Yes";
			mssg4 = "No";
			doubleOption = true;
			redText = false;
			
		}
		
		
	}
	
	public void setFacingDirection(BufferedImage direction)
	{
		character = direction;
	}
	
	public void ask()
	{
		ask = true;
		handler.getKeyManager().disableKeyboard();
	}

	@Override
	public void render(Graphics graphics)
	{
		graphics.drawImage(character, (int)x, (int)y, width, height, null);
		
		if(ask)
		{
			graphics.setColor(new Color(0,0,0,50));
			graphics.fillRect(0, 641, 704, 50);
			graphics.setColor(new Color(255, 255, 255));
			
			graphics.setFont(new Font("Times New Roman", Font.BOLD, 30));
			graphics.drawString(mssg3, 641, 670);
			graphics.drawString(mssg4, 641, 700);
			
			if(redText)
				graphics.setColor(Color.red);
			
			graphics.drawString(mssg1, 10, 670);
			graphics.drawString(mssg2, 10, 700);
			
			graphics.setColor(Color.white);
			graphics.fillRect(621, whiteBoxPosY, 10,10);
			graphics.setColor(Color.BLACK);
			graphics.drawRect(620, whiteBoxPosY-1,11, 11);
		}
	}

}

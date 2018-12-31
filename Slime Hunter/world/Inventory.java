package com.gameMaking.tilegame.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Entity;
import com.gameMaking.tilegame.gfx.Assets;

public class Inventory
{
	private ArrayList<String> invenItemName;
	private ArrayList<Entity> invenItems;
	private PopUpMessage message;
	private int coins, slime;
	private int posX;
	private int slimeBarIncrement, coinBarIncrement;
	private int slimeBarPosX, coinBarPosX;
	private int slimeBarWidth, coinBarWidth;
	
	private World world;
	public Inventory(int totalCoin, int totalSlime)
	{
		this.world = world;
		message = new PopUpMessage();
		invenItems = new ArrayList<Entity>();
		invenItemName = new ArrayList<String>();
		coins = slime = 0;
		
		slimeBarWidth = coinBarWidth = 100;
		slimeBarPosX = coinBarPosX = 0;

	}
	
	
	
	public PopUpMessage getMessage() {
		return message;
	}



	public void tick()
	{
		
		for(Entity e: invenItems)
			e.tick();
	}
	
	public void addCoin()
	{
		coins++;
		coinBarPosX += coinBarIncrement;
	}
	
	public void addSlime()
	{
		slime++;
		slimeBarPosX += slimeBarIncrement;
	}
	
	public void addItemToInventory(String itemName, Entity item)
	{
		invenItems.add(item);
		invenItemName.add(itemName);
	}
	
	public boolean hasItem(String key)
	{
		if(invenItemName.contains(key))
		{
			int indexToRemove = invenItemName.indexOf(key);
			invenItems.remove(indexToRemove);
			invenItemName.remove(indexToRemove);
			return true;
		}
		return false;
	}
	
	public void render(Graphics graphics)
	{
		posX = 130;
		
		
		graphics.drawImage(Assets.inventory,0,704,null);
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 17));
		graphics.drawString("x " + coins + "        x " + slime, 47, 742);
		
	
		for(Entity e: invenItems)
		{
			Entity item = e;
			item.setHeight(32);
			item.setWidth(32);
			item.setX(posX);
			item.setY(720);
			posX += 30;
			item.render(graphics);
		}
		
		message.render(graphics);
		
		
		graphics.setColor(Color.BLACK);
		
	}



	public int getCoins() {
		return coins;
	}



	public void setCoins(int coins) {
		this.coins = coins;
	}
}

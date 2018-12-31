package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Chest extends StaticEntity{

	int chestType;
	BufferedImage chest;
	int hiddenItem;
	boolean isOpen = false;
	
	public Chest(Handler handler, float x, float y, int chestType, int hiddenItem) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.chestType = chestType;
		this.hiddenItem = hiddenItem;
		setChestType(chestType);
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		
	}
	
	public int openChest()
	{
		isOpen = true;
		return hiddenItem;
	}
	
	public void setChestType(int chestType)
	{
		this.chestType = chestType;
		if(chestType == 1)
			chest = Assets.closedGoldChest;
		if(chestType == 2)
		{
			chest = Assets.openGoldChest;
			isOpen = true;
		}
		if(chestType == 3)
			chest = Assets.closedSilverChest;
		if(chestType == 4)
		{
			chest = Assets.openSilverChest;
			isOpen = true;
		}
	}
	
	public int getChestType() {
		return chestType;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(chest,(int) x, (int)y, width,height, null);
	}
	
	
	

}

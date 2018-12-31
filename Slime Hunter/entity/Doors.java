package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Doors extends StaticEntity{

	private int doorType; //1: wood closed   2: wood door open   3: metal closed  4: metal door open
	private BufferedImage door;
	private boolean dooropen = false;
	
	public Doors(Handler handler, float x, float y, int doorType) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.doorType = doorType;
		setDoorType(doorType);
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		
	}
	
	public void openDoor()
	{
		dooropen = true;
		setDoorType(doorType+1);
	}
	
	public void setDoorType(int doorType) {
		this.doorType = doorType;
		
		if(doorType == 1)
			door = Assets.woodDoor;
		if(doorType == 2)
		{
			door = Assets.woodDoorOpen;
			dooropen = true;
		}
		if(doorType == 3)
			door = Assets.metalDoor;
		if(doorType == 4)
		{
			door = Assets.metalDoorOpen;
			dooropen = true;
		}
			
	}
	
	public int getDoorType() {
		return doorType;
	}
	
	@Override
	public void render(Graphics graphics) {
		if(!dooropen)
			graphics.drawImage(door,(int) x, (int)y, width,height, null);
		else
		{
			graphics.drawImage(door,(int) x - 25, (int)y - 64, width,height*2, null);
			bounds.width = 17;
			bounds.height = 64;
		}
	}
	
	
	

}

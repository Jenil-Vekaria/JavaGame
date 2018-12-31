package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Stairs extends StaticEntity{

	private int stairsType; //30 = stairs going up  31 = stairs going down
	private int nextWorldRow, nextWorldCol;
	
	
	
	private BufferedImage stairImage;
	public Stairs(Handler handler, float x, float y, int stairsType, int nextWorldRow, int nextWorldCol) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.stairsType = stairsType;
		this.nextWorldRow = nextWorldRow;
		this.nextWorldCol = nextWorldCol;
		setStairImage(stairsType);
		
	}

	public int getNextWorldRow() {
		return nextWorldRow;
	}

	public int getNextWorldCol() {
		return nextWorldCol;
	}

	@Override
	public void tick() {
		
	}
	
	public int getStairsType() {
		return stairsType;
	}
	
	public void setStairImage(int type) {
		if(type == 30)
			stairImage = Assets.stairsUp;
		else if(type == 31)
			stairImage = Assets.stairsDown;
	}
	

	@Override
	public void render(Graphics graphics) {
		
			graphics.drawImage(stairImage,(int)x, (int)y, width,height, null);
		
		
	}
	
	
	

}

package com.gameMaking.tilegame.entity;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class SemiBrokenWalls extends StaticEntity{

	private int wallType;
	public SemiBrokenWalls(Handler handler, float x, float y, int wallType) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.wallType = wallType;
		
		
	}

	@Override
	public void tick() {
		
	}
	
	public int getWallType()
	{
		return wallType;
	}

	@Override
	public void render(Graphics graphics) {
		
		if(wallType == 27)
			graphics.drawImage(Assets.semiBrokenWallRight,(int)x, (int)y, width,height, null);
		else if(wallType == 28)
			graphics.drawImage(Assets.brokenRocks, (int)x, (int)y, width,height, null);
		else if(wallType == 29)
			graphics.drawImage(Assets.semiBrokenWallLeft, (int)x, (int)y, width,height, null);
		
		
	}
	
	
	

}

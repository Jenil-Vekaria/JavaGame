package com.gameMaking.tilegame.gfx;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Entity;
import com.gameMaking.tilegame.tiles.Tile;

public class GameCamera {
	
	private float xOffset, yOffset;
	private Handler handler;
	public GameCamera(Handler handler,float xOffset, float yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.handler = handler;
	}

	public void centerOnEntity(Entity e)
	{
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth()/2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight()/2;
		checkBlankSpace();
	}
	
	public void checkBlankSpace()
	{
		
		
	}
	
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}

package com.gameMaking.tilegame.entity;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Box extends StaticEntity{

	private int boxType;
	public Box(Handler handler, float x, float y, int boxType) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.boxType = boxType;
		
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics graphics) {
		
		if(boxType == 18)
			graphics.drawImage(Assets.singleCrate,(int)x, (int)y, width,height, null);
		else if(boxType == 19)
			graphics.drawImage(Assets.stackedCratesTop, (int)x, (int)y, width,height, null);
		else if(boxType == 20)
			graphics.drawImage(Assets.stackedCratesBottom, (int)x, (int)y, width,height, null);
		
		
	}
	
	
	

}

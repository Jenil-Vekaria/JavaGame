package com.gameMaking.tilegame.entity;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Arch extends StaticEntity{


	public Arch(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics graphics) {
		
		//Point Relative to
		graphics.drawImage(Assets.arch4,(int)x, (int)y, width,height, null);
	
		
		graphics.drawImage(Assets.arch1,(int)x, (int)y-64, width,height, null);
		graphics.drawImage(Assets.arch2,(int)x+64, (int)y-64, width,height, null);
		graphics.drawImage(Assets.arch3,(int)x+128, (int)y-64, width,height, null);
		graphics.drawImage(Assets.arch5,(int)x+64, (int)y, width,height, null);
		graphics.drawImage(Assets.arch6,(int)x+128, (int)y, width,height, null);
		
	}
	
	
	

}

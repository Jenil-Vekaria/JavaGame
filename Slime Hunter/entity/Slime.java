package com.gameMaking.tilegame.entity;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Slime extends StaticEntity{

	private Animation slime;
	private int counter = 0;
	public Slime(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		slime = new Animation(100, Assets.slime);
		
		bounds.x = 128;
		bounds.y = 64;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		slime.tick();	
		
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(slime.getCurrentFrame(),(int) x, (int)y, width,height, null);
	}
	
	
	

}

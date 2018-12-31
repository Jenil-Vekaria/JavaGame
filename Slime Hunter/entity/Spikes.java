package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Spikes extends StaticEntity{

	public Animation spikes;
	
	public Spikes(Handler handler, float x, float y,int index, int speed) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		spikes = new Animation(speed, Assets.spikes);
		spikes.setIndex(index);
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		spikes.tick();
		
	}
	
	

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(spikes.getCurrentFrame(),(int) x, (int)y, width,height, null);
	
	}
	
	
	

}

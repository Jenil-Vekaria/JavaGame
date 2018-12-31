package com.gameMaking.tilegame.entity;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Torch extends StaticEntity{

	private Animation torch;
	
	public Torch(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		torch = new Animation(100, Assets.torch);
		
		bounds.x = 128;
		bounds.y = 64;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		torch.tick();
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(torch.getCurrentFrame(),(int) x, (int)y, width,height, null);
	}
	
	
	

}

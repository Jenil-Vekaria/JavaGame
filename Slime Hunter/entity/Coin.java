package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Coin extends StaticEntity{

	private Animation coin;
	
	public Coin(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		coin = new Animation(100, Assets.coin);
		
		bounds.x = (int)x + 11;
		bounds.y = (int)y + 7;
		bounds.width = 41;
		bounds.height = 42;
		
		
	}

	@Override
	public void tick() {
		coin.tick();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(coin.getCurrentFrame(),(int) x, (int)y, width,height, null);
		
	}
	
	
	

}

package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Pot extends StaticEntity{

	private BufferedImage image;
	private boolean potBroken;
	public Pot(Handler handler, float x, float y, boolean potBroken) {
		
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		image = Assets.pot;
		this.potBroken = potBroken;
		bounds.x = 128;
		bounds.y = 64;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		
	}
	
	
	public void breakPot()
	{
		potBroken = true;
		/*image = Assets.brokenPot;
		int[][] staticWorld = handler.getWorld().getStaticEntity();
		staticWorld[(int)x/64][(int)y/64] = -1;*/
	}
	
	public boolean isPotBroken() {
		return potBroken;
	}

	@Override
	public void render(Graphics graphics) {
		if(!potBroken)
			graphics.drawImage(Assets.pot,(int)x, (int)y, width,height, null);
		else
			graphics.drawImage(Assets.brokenPot,(int)x, (int)y, width,height, null);
	}
	
	
	

}

package com.gameMaking.tilegame.entity;

import java.awt.Graphics;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Keys extends StaticEntity{

	private int keyType;//1: Silver Key  2: Golden Key  3: Blue Key   4: Brown Key
	
	public Keys(Handler handler, float x, float y, int keyType) {
		
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.keyType = keyType;
		bounds.x = 128;
		bounds.y = 64;
		bounds.width = 64;
		bounds.height = 64;
		
		
	}

	@Override
	public void tick() {
		
	}
	
	
	public int getkeyType()
	{
		return keyType;
	}
	
	@Override
	public void render(Graphics graphics) {
		
			if(keyType == 1)
				graphics.drawImage(Assets.silverKey,(int)x, (int)y, width,height, null);
			else if(keyType == 2)
				graphics.drawImage(Assets.bluekey,(int)x, (int)y, width,height, null);
			else if(keyType == 3)
				graphics.drawImage(Assets.brownkey,(int)x, (int)y, width,height, null);
			else if(keyType == 4)
				graphics.drawImage(Assets.goldkey,(int)x, (int)y, width,height, null);

	}
	
	
	

}

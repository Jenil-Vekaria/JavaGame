package com.gameMaking.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.gfx.Assets;

public class Tile {

	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64; 
	protected BufferedImage texture;
	protected final int id; 
	private boolean isSolid = false;
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture; 
		this.id = id; 
		
		setTile(id); 
	}
	
	public void setTile(int id)
	{ 
		switch(id)
		{
			case 0:  texture = Assets.floor; isSolid = false; break;
			case 1:  texture = Assets.brokenFloor1; isSolid = false; break;
			case 2:  texture = Assets.brokenFloor2; isSolid = false; break;
			case 3:  texture = Assets.brokenFloor3; isSolid = false; break;
			
			case 4:  texture = Assets.brokenFloor4; isSolid = false; break;
			case 5:  texture = Assets.brokenFloor5; isSolid = false; break;
			case 6:  texture = Assets.brokenFloor6; isSolid = false; break;
			case 7:  texture = Assets.brokenFloor7; isSolid = false; break;
			
			case 8:  texture = Assets.brokenFloor8; isSolid = false; break;
			case 9:  texture = Assets.brokenFloor9; isSolid = false; break;
			case 10:  texture = Assets.brokenFloor10; isSolid = false; break;
			case 11:  texture = Assets.brokenFloor11; isSolid = false; break;
			
			
			case 12:  texture = Assets.plainTL; isSolid = true; break; 
			case 13:  texture = Assets.plainTopMiddle; isSolid = true; break; 
			case 14:  texture = Assets.plainTR; isSolid = true; break;
			case 15:  texture = Assets.stairs; isSolid = false; break;
			
			
			case 16:  texture = Assets.plainLeft; isSolid = true; break; 
			case 17:  texture = Assets.plainMiddle; isSolid = true; break; 
			case 18:  texture = Assets.plainRight; isSolid = true; break;
			case 19:  texture = Assets.stairsDown; isSolid = true; break;
			
			
			case 20:  texture = Assets.plainWallBL; isSolid = true; break;
			case 21:  texture = Assets.plainWallSide; isSolid = true; break;
			case 22:  texture = Assets.plainWallBR; isSolid = true; break;
			case 23:  texture = Assets.rock; isSolid = true; break;
		
			 
			case 24:  texture = Assets.archSupportRight; isSolid = true; break; 
			case 25:  texture = Assets.archSupportLeft; isSolid = true; break; 
			case 26:  texture = Assets.brokenWallSide1; isSolid = true; break; 
			case 27:  texture = Assets.brokenWallSide2; isSolid = true; break; 
			
			
			case 28:  texture = Assets.wallTL; isSolid = true; break; 
			case 29:  texture = Assets.WallFourWay; isSolid = true; break;
			case 30:  texture = Assets.walllSide; isSolid = true; break;
			case 31:  texture = Assets.wallTR; isSolid = true; break; 
			
			case 32:  texture = Assets.TRight; isSolid = true; break;
			case 33:  texture = Assets.wallEndRight; isSolid = true; break;
			case 34:  texture = Assets.wallEndLeft; isSolid = true; break; 
			case 35:  texture = Assets.TLeft; isSolid = true; break;
		
			case 36:  texture = Assets.wallDown; isSolid = true; break; 
			case 37:  texture = Assets.wallEndUp; isSolid = true; break; 
			case 38:  texture = Assets.TDown; isSolid = true; break;  
			case 39:  texture = Assets.brokenWallDown; isSolid = true; break;
			
			case 40:  texture = Assets.wallBL; isSolid = true; break; 
			case 41:  texture = Assets.TUp; isSolid = true; break; 
			case 42:  texture = Assets.wallEndDown; isSolid = true; break;  
			case 43:  texture = Assets.wallBR; isSolid = true; break;
			
			case 44:  texture = Assets.singleWall; isSolid = true; break;
	 }
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics graphics, int x, int y)
	{
		graphics.drawImage(texture,x, y, TILEHEIGHT, TILEWIDTH, null); 
	}
	
	public boolean isSolid()
	{
		return isSolid; 
	}
	
	
	public int getId()
	{
		return id; 
	}
}

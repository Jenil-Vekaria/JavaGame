package com.gameMaking.tilegame.entity.creature;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Entity;
import com.gameMaking.tilegame.tiles.Tile;

public abstract class Creature extends Entity {
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 7.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

	protected int health;
	protected float speed;
	protected float xMove, yMove;
	int coinNumber = 0;
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = yMove = 0;
	}

	public boolean collisionWithTile(int x, int y) {
		
		return handler.getWorld().getTile(y, x).isSolid();
	}

	public void moveX()
	{
		
		if(xMove > 0)//MOVING RIGHT
		{
			//Getting the points: right side portion of the character
			int tx = (int)(x + xMove + bounds.x + bounds.width)/Tile.TILEWIDTH;
			
			//Checking collision with top right point and bottom right point
			if(!collisionWithTile(tx, (int)(y+bounds.y)/Tile.TILEHEIGHT) && !collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT))
				x += xMove;
		}
		else if(xMove < 0)//MOVING LEFT
		{
			//Getting the points: left side portion of the character
			int tx = (int)(x + xMove + bounds.x)/Tile.TILEWIDTH;
			
			//Checking collision with top left point and bottom left point
			if(!collisionWithTile(tx, (int)(y+bounds.y)/Tile.TILEHEIGHT) && !collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILEHEIGHT))
				x += xMove;
			
		}
	}
	
	public void moveY() {
		if(yMove > 0) //MOVING DOWN
		{
			//Getting the points: bottom portion of the character
			int ty = (int)(y+yMove+bounds.y+bounds.height)/Tile.TILEHEIGHT;
			
			
			if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH, ty) && !collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH, ty))
				y += yMove;
		}
		else if(yMove < 0) //MOVING UP
		{
			int ty = (int)(y+yMove+bounds.y)/Tile.TILEHEIGHT;
			if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH, ty) && !collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH, ty))
				y += yMove;
			
		}
	}

	public void move()
	{
		moveX();
		moveY();
		
		checkCoinCollision();
		checkDoorCollision(xMove,yMove);
		checkChestCollision(xMove,yMove);
		checkSpikesCollision(xMove,yMove);
		checkKeyCollision();
		checkPotCollision(xMove,yMove);
		checkSemiBrokenWallCollision(xMove, yMove);
		checkStairCollision(xMove,yMove);
		checkButtonsCollision();
		checkCratesCollision(xMove, yMove);
		checkSlimeCollision();
		checkBlueManCollision(xMove, yMove);
		
	
		
	}

	
	

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}

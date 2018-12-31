package com.gameMaking.tilegame.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
public class Player extends Creature {

	//Animation
	private Animation animUp, animDown, animLeft, animRight;
	private Animation attackUp, attackDown, attackLeft, attackRight;
	private Animation currentAnimation, curentAttackAnimation;
	public boolean attack = false;
	private boolean isMoving;
	private int worldRow, worldCol;
	private boolean loadNextWorld = false;

	public Player(Handler hanlder, float x, float y) {
		super(hanlder,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		isMoving = false;
		
		bounds.x = 25;
		bounds.y = 40;
		bounds.width = 14;
		bounds.height = 16;
		
		//Animation
		animUp = new Animation(100, Assets.player_up);
		animDown = new Animation(100, Assets.player_down);
		animLeft = new Animation(100, Assets.player_left);
		animRight = new Animation(100, Assets.player_right);
		
		attackUp = new Animation(100, Assets.player_attack_up);
		attackDown = new Animation(100, Assets.player_attack_down);
		attackLeft = new Animation(100, Assets.player_attack_left);
		attackRight = new Animation(100, Assets.player_attack_right);
		
		currentAnimation = animDown;
		curentAttackAnimation = attackDown;
		
		worldRow = 1;
		worldCol = 2;
	}

	@Override
	public void tick()
	{
		getInput();
		move();
		
		if(handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left || handler.getKeyManager().right && !handler.getKeyManager().attack)
		{
			getCurrentAnimation().tick();
			attack = false;
		}
		else if(handler.getKeyManager().attack)
		{
			getAttack().tick();
			if(getAttack().getSize()-1 == getAttack().getIndex())
			{
				getAttack().setIndex(0);
				handler.getKeyManager().attack = false;
			}
		}
		else if(!attack)
			getCurrentAnimation().setIndex(5);
		else
			getAttack().setIndex(getAttack().getSize()-1);
		
		
		//Moving Right
		if(x >= 10*64 && ( (int)y/64 >= 0  && (int)y/64 <= 10) && xMove > 0)
		{
			worldCol++;
			handler.getWorld().loadNextLevel(worldCol, worldRow);
			x = 0;
		}
		
		//Moving Left
		if(x <= 0 && (y/64 >= 0  && y/64 <= 10) && xMove < 0)
		{
			worldCol--;
			handler.getWorld().loadNextLevel(worldCol, worldRow);
			x = 10*64;
		}
		

		//Moving Up
		if(y <= 0 && ( (int)x/64 >= 0  && (int)x/64 <= 10) && yMove < 0)
		{
			worldRow--;
			handler.getWorld().loadNextLevel(worldCol, worldRow);
			y = 10*64;
		
		}
		
		//Moving Down
		if(y >= 10*64 && ( (int)x/64 >= 0  && (int)x/64 <= 10) && yMove > 0)
		{
			worldRow++;
			handler.getWorld().loadNextLevel(worldCol, worldRow);
			y = 0;
		}
		
		if(loadNextWorld)
		{
			handler.getWorld().loadNextLevel(worldCol, worldRow);
			loadNextWorld = false;
		}
		
		
	}
	
	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public int getWorldRow() {
		return worldRow;
	}

	public int getWorldCol() {
		return worldCol;
	}

	public void setWorld(int worldRow, int worldCol) {
		this.worldRow = worldRow;
		this.worldCol = worldCol;
		loadNextWorld = true;
	}

	public boolean isAttacking()
	{
		return attack;
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove -= speed;
		if(handler.getKeyManager().down)
			yMove += speed;
		if(handler.getKeyManager().left)
			xMove -= speed;
		if(handler.getKeyManager().right)
			xMove += speed;
		if(handler.getKeyManager().attack)
			attack = true;
		
		if(xMove != 0 && yMove != 0)
			isMoving = true;
		
			
	}

	@Override
	public void render(Graphics graphics) {
		if(!attack)
			graphics.drawImage(getCurrentAnimation().getCurrentFrame(), (int) x, (int) y, width, height, null);
		else
			graphics.drawImage(getAttack().getCurrentFrame(), (int) x, (int) y, width, height, null);
	}
	
	public Animation getAttack()
	{
		return curentAttackAnimation;
	}
	private Animation getCurrentAnimation()
	{
		if(xMove < 0)//Moving Left
		{
			currentAnimation = animLeft;
			curentAttackAnimation = attackLeft;
			return animLeft;
		}
		else if(xMove > 0)//Moving Right
		{
			currentAnimation = animRight;
			curentAttackAnimation = attackRight;
			return animRight;
		}
		else if(yMove < 0)//Moving Up
		{
			currentAnimation = animUp;
			curentAttackAnimation = attackUp;
			return animUp;
		}
		else if(yMove > 0)
		{
			currentAnimation = animDown;
			curentAttackAnimation = attackDown;
			return animDown;
		}
		else {
			return currentAnimation;
		}
	}

}

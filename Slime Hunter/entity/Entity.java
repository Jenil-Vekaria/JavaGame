package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.util.ArrayList;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.creature.BlueMan;
import com.gameMaking.tilegame.entity.creature.Player;
import com.gameMaking.tilegame.gfx.Assets;

public abstract class Entity {
	
	protected Handler handler;
	protected float  x,y;
	protected int width, height;
	protected Rectangle bounds;
	private boolean playButtonSound = false;
	public Entity(Handler game,float x, float y, int width, int height)
	{
		this.handler = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0,width, height);
	}
	
	public String getkeyType(int num)
	{
		if (num >= 0 && num <= 1)
			return "Gold Key";
		else if(num >= 2 && num <= 4)
			return "Silver Key";
		return "";
		
	}
	
	public void checkChestCollision(float moveX, float moveY)
	{
		
		ArrayList<Entity> chest = handler.getWorld().getEntityManager().getChestEntities();
		for(int index = 0; index < chest.size(); index++)
		{
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle chestRec = new Rectangle((int)chest.get(index).x, (int)chest.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x + (int)moveX, player.bounds.y + (int)y + (int)moveY, 14,16);
			
			Chest ch = (Chest) chest.get(index);
			int chestType = ch.getChestType();
			
			if(chestRec.intersects(playerRec) && !ch.isOpen)
			{
				if(handler.getInventory().hasItem(getkeyType(chestType)))
				{
					int item = handler.getWorld().getEntityManager().openChest(index);
					
					if(item == 0)
					{
						handler.getWorld().getEntityManager().incrementCoin();
						handler.getInventory().getMessage().coinMessage();
					}
					else if(item == 1)
					{
						handler.getInventory().addItemToInventory("Silver Key", new Keys(handler, 70, 720, 1));
						handler.getInventory().getMessage().silverKeyMessage();
					}
					else if(item == 2)
					{
						handler.getInventory().addItemToInventory("Blue Key", new Keys(handler, 100, 720, 2));
						handler.getInventory().getMessage().blueKeyMessage();
					}
					else if(item == 3)
					{
						handler.getInventory().addItemToInventory("Brown Key", new Keys(handler, 130, 720, 3));
						handler.getInventory().getMessage().brownKeyMessage();
					}
					else if(item == 4)
					{
						handler.getInventory().addItemToInventory("Gold Key", new Keys(handler, 160, 720, 4));
						handler.getInventory().getMessage().goldKeyMessage();
					}
					Assets.playSound(Assets.chestSound);
				}
			}
			
			if(chestRec.intersects(playerRec) && (ch.isOpen || !ch.isOpen))
			{
					player.x -= moveX;
					player.y -= moveY;
			
			}
			
		}
	}
	
	public void checkSpikesCollision(float moveX, float moveY)
	{
		
		ArrayList<Entity> spikes = handler.getWorld().getEntityManager().getSpikes();
		for(int index = 0; index < spikes.size(); index++)
		{
			Spikes spike = (Spikes) spikes.get(index);
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle spikesRec = new Rectangle((int)spikes.get(index).x, (int)spikes.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
			
			
			if(spike.spikes.getIndex() == 1 && (spikesRec.intersects(playerRec)) && (moveX >= 0 || moveY >= 0) )
			{
				System.out.println("GAMEOVER BITCH");
				break;
			}
		}
	}
	
	public void checkCratesCollision(float moveX, float moveY)
	{
		
		ArrayList<Entity> crates = handler.getWorld().getEntityManager().getCrates();
		for(int index = 0; index < crates.size(); index++)
		{
			Box crate = (Box) crates.get(index);
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle crateRec = new Rectangle((int)crates.get(index).x, (int)crates.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
			
			
			if(playerRec.intersects(crateRec))
			{
				player.x -= moveX;
				player.y -= moveY;
			}
		}
	}
	
	public void checkSlimeCollision()
	{
		
		ArrayList<Entity> slimes = handler.getWorld().getEntityManager().getSlime();
		for(int index = 0; index < slimes.size(); index++)
		{
			Slime slime = (Slime) slimes.get(index);
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle slimeRec = new Rectangle((int)slimes.get(index).x, (int)slimes.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
			
			
			if(playerRec.intersects(slimeRec))
			{
				handler.getWorld().getEntityManager().removeSlime(index);
				Assets.playSound(Assets.slimeSound);
				handler.getInventory().addSlime();
			}
		}
	}
	
	public void checkStairCollision(float moveX, float moveY)
	{
		
		ArrayList<Entity> stair = handler.getWorld().getEntityManager().getStairs();
		for(int index = 0; index < stair.size(); index++)
		{
			Stairs stairs = (Stairs) stair.get(index);
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle stairRec = new Rectangle((int)stair.get(index).x, (int)stair.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
			
			
			//STAIRS GOING DOWN
			if(moveX < 0 && playerRec.intersects(stairRec) && stairs.getStairsType() == 31)
			{
				
				player.setWorld(stairs.getNextWorldRow(),stairs.getNextWorldCol());
				player.setX((((int)player.x/64)-1) * 64);
			}
			//STAIRS GOING UP
			else if(moveX > 0 && playerRec.intersects(stairRec) && stairs.getStairsType() == 30)
			{
				player.setWorld(stairs.getNextWorldRow(),stairs.getNextWorldCol());
				player.setX((((int)player.x/64)+2) * 64);
				
			}
			else if(playerRec.intersects(stairRec) && (stairs.getStairsType() == 31 || stairs.getStairsType() == 30)){
				player.x -= moveX;
				player.y -= moveY;
			}
			
		}
	}
	
	public void checkPotCollision(float moveX, float moveY)
	{
		
		ArrayList<Entity> pot = handler.getWorld().getEntityManager().getPots();
		for(int index = 0; index < pot.size(); index++)
		{
			Pot pots = (Pot) pot.get(index);
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle potRec = new Rectangle((int)pot.get(index).x, (int)pot.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x + (int)moveX, player.bounds.y + (int)y + (int)moveY , 14,16);
			
			if(player.isAttacking())
			{ 
				playerRec = new Rectangle((int)x + 12,(int)y, 40, 64);
				
				if(playerRec.intersects(potRec) && !pots.isPotBroken())
				{
					handler.getWorld().getEntityManager().breakPot(index);
					Assets.playSound(Assets.brokenPotSound);
					player.setAttack(false);
					
				}
			}
			else if(!player.isAttacking() && playerRec.intersects(potRec) && !pots.isPotBroken())
			{
				player.x -= moveX;
				player.y -= moveY;
			}
			

		}
	}
	
	public void checkButtonsCollision()
	{
		
		ArrayList<Entity> buttons = handler.getWorld().getEntityManager().getButtons();
		for(int index = 0; index < buttons.size(); index++)
		{
			Buttons bt = (Buttons) buttons.get(index);
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle buttonsRec = new Rectangle((int)buttons.get(index).x, (int)buttons.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
			
			
			if(!bt.isPressed())
			{
				if(playerRec.intersects(buttonsRec))
				{
					handler.getWorld().getEntityManager().pressButton(index);
					Assets.playSound(Assets.buttonsound);
				}
			}
			
			if(!playerRec.intersects(buttonsRec))
			{
				bt.releaseButton();
			}
			
				
			
		}
	}
	
	public void checkKeyCollision()
	{
		
		ArrayList<Entity> key = handler.getWorld().getEntityManager().getKeys();
		for(int index = 0; index < key.size(); index++)
		{
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle keyRec = new Rectangle((int)key.get(index).x, (int)key.get(index).y, 64,64);
			Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
			
			Keys pickedKey = (Keys) key.get(index);
			
			if(keyRec.intersects(playerRec))
			{
				if(pickedKey.getkeyType() == 1)
				{
					handler.getInventory().addItemToInventory("Silver Key", key.get(index));
					handler.getInventory().getMessage().silverKeyMessage();
				}
				else if(pickedKey.getkeyType() == 2)
				{
					handler.getInventory().addItemToInventory("Blue Key", key.get(index));
					handler.getInventory().getMessage().blueKeyMessage();
				}
				if(pickedKey.getkeyType() == 3)
				{
					handler.getInventory().addItemToInventory("Brown Key", key.get(index));
					handler.getInventory().getMessage().brownKeyMessage();
				}
				else if(pickedKey.getkeyType() == 4)
				{
					handler.getInventory().addItemToInventory("Gold Key", key.get(index));
					handler.getInventory().getMessage().goldKeyMessage();
				}
				
				handler.getWorld().getEntityManager().pickUpKey(index);
				Assets.playSound(Assets.keySound);
				break;
			}
		}
	}
	
	
	public void checkSemiBrokenWallCollision(float moveX, float moveY)
	{
		ArrayList<Entity> semiBrokenWalls = handler.getWorld().getEntityManager().getSemiBrokenWalls();
		for(int index = 0; index < semiBrokenWalls.size(); index++)
		{
			Player player = handler.getWorld().getEntityManager().getPlayer();
			SemiBrokenWalls walls = (SemiBrokenWalls) semiBrokenWalls.get(index);
			
			//CHANGE THE NEXT LINE AFTER
			Rectangle wallRec = new Rectangle((int)semiBrokenWalls.get(index).x, (int)semiBrokenWalls.get(index).y, 64,64);
			Rectangle playerRec = player.getCollisionBounds();
			
			
			if(wallRec.intersects(playerRec) && (walls.getWallType() == 27 || walls.getWallType() == 29))
			{
				player.x -= moveX;
				player.y -= moveY;
			}
		}
	}
	
	public void checkCoinCollision()
	{
		ArrayList<Entity> coin = handler.getWorld().getEntityManager().getCoinEntities();
		for(int index = 0; index < coin.size(); index++)
		{
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle coinRec = new Rectangle((int)coin.get(index).x, (int)coin.get(index).y, 64,64);
			Rectangle playerRec = player.getCollisionBounds();
			
			
			if(coinRec.intersects(playerRec))
			{
				handler.getWorld().getEntityManager().removeCoin(index);
				
				break;
			}
		}
	}
	
	public void checkBlueManCollision(float moveX, float moveY)
	{
		BlueMan blueMan = handler.getWorld().getEntityManager().getBlueMan();
		Player player = handler.getWorld().getEntityManager().getPlayer();
		Rectangle blueManRec = new Rectangle((int)blueMan.x, (int)blueMan.y, 64,64);
		Rectangle playerRec = new Rectangle(player.bounds.x + (int)x, player.bounds.y + (int)y, 14,16);
		
		
		if(playerRec.intersects(blueManRec))
		{

			if(moveX < 0) //left
				blueMan.setFacingDirection(Assets.blueManRight);
			else if(moveX > 0)
				blueMan.setFacingDirection(Assets.blueManLeft);
			if(moveY > 0) //down
				blueMan.setFacingDirection(Assets.blueManUp);
			else if(moveY < 0)//up
				blueMan.setFacingDirection(Assets.blueManDown);
			
			
			blueMan.ask();
			player.x -= moveX;
			player.y -= moveY;
		}
	}
	
	public void checkDoorCollision(float moveX, float moveY)
	{
		ArrayList<Entity> door = handler.getWorld().getEntityManager().getDoorEntities();
		for(int index = 0; index < door.size(); index++)
		{
			Player player = handler.getWorld().getEntityManager().getPlayer();
			//CHANGE THE NEXT LINE AFTER
			Rectangle doorRec = new Rectangle((int)door.get(index).bounds.x, (int)door.get(index).bounds.y,(int)door.get(index).bounds.width, (int)door.get(index).bounds.height);
			Rectangle playerRec = player.getCollisionBounds();
			
			Doors collideDoor = (Doors) door.get(index);
			
			if(doorRec.intersects(playerRec))
			{
				if(collideDoor.getDoorType() == 1)// wood door
				{
					if(handler.getInventory().hasItem("Brown Key"))
					{
						handler.getWorld().getEntityManager().openDoor(index);
						Assets.playSound(Assets.woodDoorSound);
						break;
					}
					else
					{
						moveBack(player, moveX, moveY);
					}
						
				}
				else if (collideDoor.getDoorType() == 3) //Metal Door
				{
					
					if(handler.getInventory().hasItem("Blue Key"))
					{
						handler.getWorld().getEntityManager().openDoor(index);
						Assets.playSound(Assets.metalDoorSound);
						break;
					}
					else {
						moveBack(player, moveX, moveY);
					}
				}
				else {
					moveBack(player, moveX, moveY);
				}
				
			}
		}
	}
	
	
	public void moveBack(Player player, float moveX, float moveY)
	{
		player.x -= moveX;
		player.y -= moveY;
	}
	
	public Rectangle getCollisionBounds()
	{
		return new Rectangle((int)(x + bounds.x + 2), (int)(y+bounds.y + 2), bounds.width, bounds.height);
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();
	
	public abstract void render(Graphics graphics);
}

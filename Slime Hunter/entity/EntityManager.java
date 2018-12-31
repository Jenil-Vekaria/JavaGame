package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.w3c.dom.css.ElementCSSInlineStyle;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.creature.BlueMan;
import com.gameMaking.tilegame.entity.creature.Player;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class EntityManager
{
	private Handler handler;
	private Player player;
	private BlueMan blueMan;
	
	private ArrayList<Entity> coins, chests, archSupport, doors, torch, spikes, keys, buttons, pots, semiBrokenWalls, stairs, crates, slime;
	private int worldRowOffset, worldColOffset;
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		blueMan = new BlueMan(handler, -100,-100);
		
		coins = new ArrayList<Entity>();
		chests = new ArrayList<Entity>();
		archSupport = new ArrayList<Entity>();
		doors = new ArrayList<Entity>();
		torch = new ArrayList<Entity>();
		spikes = new ArrayList<Entity>();
		keys = new ArrayList<Entity>();
		buttons = new ArrayList<Entity>();
		pots = new ArrayList<Entity>();
		semiBrokenWalls = new ArrayList<Entity>();
		stairs = new ArrayList<Entity>();
		crates = new ArrayList<Entity>();
		slime = new ArrayList<Entity>();
	}
	
	public BlueMan getBlueMan()
	{
		return blueMan;
	}
	
	public void clear()
	{
		coins.clear();
		chests.clear();
		archSupport.clear();
		doors.clear();
		torch.clear();
		spikes.clear();
		keys.clear();
		buttons.clear();
		pots.clear();
		semiBrokenWalls.clear();
		stairs.clear();
		crates.clear();
		slime.clear();
	}
	
	public void tick()
	{
		for(Entity entity: torch)
			entity.tick();
		
		for(Entity entity: crates)
			entity.tick();
		
		for(Entity entity: slime)
			entity.tick();
		
		for(Entity entity: keys)
			entity.tick();
		
		for(Entity entity: spikes)
			entity.tick();
		
		for(Entity entity: coins)
			entity.tick();
		
		for(Entity entity: doors)
			entity.tick();
		
		for(Entity entity: archSupport)
			entity.tick();
		
		for(Entity entity: chests)
			entity.tick();
		
		for(Entity entity: buttons)
			entity.tick();
		
		for(Entity entity: pots)
			entity.tick();
		
		for(Entity entity: semiBrokenWalls)
			entity.tick();
		
		for(Entity entity: stairs)
			entity.tick();
		
		blueMan.tick();
		player.tick();
		
		
		worldRowOffset = handler.getWorld().getWorldRow();
		worldColOffset = handler.getWorld().getWorldCol();
	}
	
	public ArrayList<Entity> getCrates() {
		return crates;
	}

	public ArrayList<Entity> getTorch() {
		return torch;
	}

	public ArrayList<Entity> getSpikes() {
		return spikes;
	}
	
	
	
	
	
	
	//POTS
	public void addPots(Handler handler, float x, float y, boolean potBroken)
	{
		pots.add(new Pot(handler, x, y, potBroken));
	}
	
	public void breakPot(int index)
	{
		Pot breakPot = (Pot) pots.get(index);
		int breakRow = (int) breakPot.getY()/64;
		int breakCol = (int) breakPot.getX()/64;
		int[][] staticMap = handler.getWorld().getStaticEntity();
		staticMap[breakRow+worldRowOffset][breakCol+worldColOffset] = -4;
		breakPot.breakPot();
	}
	
	public void removePot(int row, int col)
	{
		for(Entity e: pots)
		{
			if(e.x == (col*Tile.TILEWIDTH) && e.y == (row*Tile.TILEHEIGHT))
			{
				pots.remove(pots.indexOf(e));
			}
		}
	}
	
	
	public ArrayList<Entity> getPots() {
		return pots;
	}
	
	
	
	//CRATES
	public void addCrates(Handler handler, float x, float y, int boxType)
	{
		crates.add(new Box(handler, x, y, boxType));
	}
	
	

	//BUTTONS
	public void addButtons(Handler handler, float x, float y, int buttonType, int changeRow, int changeCol, int typeChange, int tiles_static, int changeItem)
	{
		buttons.add(new Buttons(handler, x, y, buttonType,changeRow,changeCol,typeChange,tiles_static,changeItem));
	}
	
	public void pressButton(int index)
	{
		Buttons bt = (Buttons) buttons.get(index);
		int btRow = (int)bt.getY()/64;
		int btCol = (int)bt.getX()/64;
		int[][] staticMap = handler.getWorld().getStaticEntity();
		
		if(bt.getButtontType() == 23)
			staticMap[btRow+worldRowOffset][btCol+worldColOffset] = -5;
		else if(bt.getButtontType() == 24)
			staticMap[btRow+worldRowOffset][btCol+worldColOffset] = -6;
		
		bt.pressButton();
		bt.changeWorld();
	}
	
	
	
	
	
	//KEYS
	public void addKeys(Handler handler, float x, float y, int keyType)
	{
		keys.add(new Keys(handler, x, y, keyType));
	}
	
	public void pickUpKey(int index)
	{
		Keys removeKey = (Keys) keys.get(index);
		int removeRow = (int)removeKey.getY()/64;
		int removeCol = (int)removeKey.getX()/64;
		int[][] staticMap = handler.getWorld().getStaticEntity();
		staticMap[removeRow+worldRowOffset][removeCol+worldColOffset] = -1;
		
		keys.remove(index);
	}
	
	public ArrayList<Entity> getKeys() {
		return keys;
	}
	
	
	
	
	
	

	//CHEST
	public void addChest(Handler handler, int posX, int posY, int chestType, int hiddenItem)
	{
		chests.add(new Chest(handler, posX, posY, chestType,hiddenItem));
	}
	
	public int openChest(int index)
	{
		Chest chest2 = (Chest) chests.get(index);
		
		int chestRow = (int)chest2.getY()/64;
		int chestCol = (int)chest2.getX()/64;
		int[][] staticMap = handler.getWorld().getStaticEntity();
		
		
		int hiddenItem = chest2.openChest();
		//Gold Chest
		if(hiddenItem >= 0 && hiddenItem <= 1)
		{
			chest2.setChestType(2);
			staticMap[chestRow+worldRowOffset][chestCol+worldColOffset] = -2;
		}
		//Silver Chest
		else if(hiddenItem >= 2 && hiddenItem <= 4)
		{
			chest2.setChestType(4);
			staticMap[chestRow+worldRowOffset][chestCol+worldColOffset] = -3;
		}
		
		return hiddenItem;
	}
	
	
	

	public ArrayList<Entity> getStairs() {
		return stairs;
	}

	//SEMI-BROKEN WALL
	public void addStairs(Handler handler, int posX, int posY, int stairsType, int nextWorldRow, int nextWorldCol)
	{
		stairs.add(new Stairs(handler, posX, posY, stairsType, nextWorldRow, nextWorldCol));
	}
	
	
	
	
	
	
	
	//SEMI-BROKEN WALL
	public void addSemiBrokenWall(Handler handler, int posX, int posY, int wallType)
	{
		semiBrokenWalls.add(new SemiBrokenWalls(handler, posX, posY, wallType));
	}
	
	
	
	
	
	//TORCH
	public void addTorch(Handler handler, int posX, int posY)
	{
		torch.add(new Torch(handler, posX, posY));
	}
	
	
	
	
	
	//SPIKES
	public void addSpikes(Handler handler, int posX, int posY,int index, int speed)
	{
		spikes.add(new Spikes(handler, posX, posY,index,speed));
	}
	
	
	
	
	
	
	//COIN
	public void addCoins(Handler handler, int posX, int posY)
	{
		coins.add(new Coin(handler, posX, posY));
	}
	
	public void incrementCoin()
	{
		handler.getInventory().addCoin();
	}
	
	public void removeCoin(int index)
	{
		//Get the removing coin and the tile position of it
		Coin removeCoin = (Coin)coins.get(index);
		int removeCoinRow = (int)removeCoin.getY()/Tile.TILEHEIGHT;
		int removeCoinCol = (int)removeCoin.getX()/Tile.TILEWIDTH;
		
		coins.remove(index);
		handler.getInventory().addCoin();
		Assets.playSound(Assets.coinSound);
		
		//Change the static map, location of coin ---> -1
		int[][] staticMap = handler.getWorld().getStaticEntity();
		staticMap[removeCoinRow+worldRowOffset][removeCoinCol+worldColOffset] = -1;
		
	}
	
	
	
	
	
	//ARCH SUPPORT
	public void addArch(Handler handler, int posX, int posY)
	{
		archSupport.add(new Arch(handler, posX, posY));
	}
	
	
	
	
	
	//DOORS
	public void addDoor(Handler handler, int posX, int posY, int doorType)
	{
		doors.add(new Doors(handler, posX, posY, doorType));
	}
	
	public void openDoor(int index)
	{
		Doors door = (Doors) doors.get(index);
		door.openDoor();
		
		int openDoorRow = (int)door.getY()/Tile.TILEHEIGHT;
		int openDoorCol = (int)door.getX()/Tile.TILEWIDTH;
		
		int[][] staticMap = handler.getWorld().getStaticEntity();
		
		System.out.println(door.getDoorType());
		if(door.getDoorType() == 4)
			staticMap[openDoorRow+worldRowOffset][openDoorCol+worldColOffset] = -8;
		else if(door.getDoorType() == 2)
			staticMap[openDoorRow+worldRowOffset][openDoorCol+worldColOffset] = -7;
		
		
	}
	
	
	//SLIME
	
	public void addSlime(Handler handler, float x, float y)
	{
		slime.add(new Slime(handler, x, y));
	}
	
	public void removeSlime(int index)
	{
		//Get the removing slime
		Slime removeSlime = (Slime)slime.get(index);
		int removeCoinRow = (int)removeSlime.getY()/Tile.TILEHEIGHT;
		int removeCoinCol = (int)removeSlime.getX()/Tile.TILEWIDTH;
		
		int[][] staticMap = handler.getWorld().getStaticEntity();
		staticMap[removeCoinRow+worldRowOffset][removeCoinCol+worldColOffset] = -1;
		
		slime.remove(index);
	}
	
	
	
	
	
	
	public void render(Graphics graphics)
	{
		try {
			for(Entity entity: torch)
				entity.render(graphics);
			
			for(Entity entity: crates)
				entity.render(graphics);
			
			for(Entity entity: slime)
				entity.render(graphics);
			
			for(Entity entity: keys)
				entity.render(graphics);
		
			for(Entity entity: spikes)
				entity.render(graphics);
			
			for(Entity entity: chests)
				entity.render(graphics);
			
			for(Entity entity: coins)
				entity.render(graphics);
			
			for(Entity entity: doors)
				entity.render(graphics);
			
			for(Entity entity: pots)
				entity.render(graphics);
			
			for(Entity entity: semiBrokenWalls)
				entity.render(graphics);
			
			for(Entity entity: stairs)
				entity.render(graphics);
			
			for(Entity entity: buttons)
				entity.render(graphics);
			
			blueMan.render(graphics);
			player.render(graphics);
			
			for(Entity entity: archSupport)
				entity.render(graphics);
		}
		catch (ConcurrentModificationException e) {
			System.out.println("error");
		}
		
		
		
		
		
		
		
	
	}

	public ArrayList<Entity> getSlime() {
		return slime;
	}

	public ArrayList<Entity> getSemiBrokenWalls() {
		return semiBrokenWalls;
	}

	public ArrayList<Entity> getButtons() {
		return buttons;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getChestEntities() {
		return chests;
	}
	
	public ArrayList<Entity> getCoinEntities() {
		return coins;
	}
	
	public ArrayList<Entity> getDoorEntities() {
		return doors;
	}

}

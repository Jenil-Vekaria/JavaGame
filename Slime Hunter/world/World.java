package com.gameMaking.tilegame.world;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.rmi.CORBA.Util;
import javax.swing.Timer;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Spikes;
import com.gameMaking.tilegame.entity.Pot;
import com.gameMaking.tilegame.entity.Entity;
import com.gameMaking.tilegame.entity.EntityManager;
import com.gameMaking.tilegame.entity.Arch;
import com.gameMaking.tilegame.entity.Buttons;
import com.gameMaking.tilegame.entity.Coin;
import com.gameMaking.tilegame.entity.creature.Player;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.gfx.TransitionEffect;
import com.gameMaking.tilegame.tiles.Tile;
import com.gameMaking.tilegame.utils.Utils;

public class World{
	private int width, height;
	public static int offSetX, offSetY;
	private int[][] tiles;
	private int[][] staticEntity;
	
	private int[][] sperateMap;
	private int[][] sperateStaticMap;
	
	private Handler handler;
	private ArrayList<Integer> archSupportCoordinates;
	
	private int worldRow, worldCol;
	
	private TransitionEffect transitionEffect;
	

	//Inventory
	private Inventory inventory;
	
	//ENTITY
	EntityManager entityManager;
	
	
	
	public World(Handler handler, String path) {
		this.handler = handler;
		archSupportCoordinates = new ArrayList<Integer>();
		transitionEffect = new TransitionEffect(17, handler);
		offSetX = offSetY = 0;
		entityManager = new EntityManager(handler, new Player(handler, getTileXPos(4), getTileYPos(3)));
		worldRow = 11;
		worldCol = 22;
		
		loadWorld(path);
		inventory = new Inventory(entityManager.getCoinEntities().size(), entityManager.getSlime().size());
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
//===========================================================================TICK AND RENDER==========================================================//	
	
	
	public void tick()
	{
		
		entityManager.tick();
		transitionEffect.tick();
		
	}

	public void render(Graphics graphics) {
		for (int row = 0; row < 11; row++) {
			for (int col = 0; col < 11; col++) {
				getTile(row,col).render(graphics, (int) (col * Tile.TILEWIDTH),	(int) (row * Tile.TILEHEIGHT));
			}
		}
		entityManager.render(graphics);
		inventory.render(graphics);
		transitionEffect.render(graphics);
	}
	
	
	
//=========================================================================================================================================================//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//----------------------------------------------ADDING STATIC ENTITY-----------------------------------------------------------------------//	
	public void addAll()
	{
		getEntityManager().clear();
		addSpikes();
		addDoor();
		addTorch();
		addArch();
		addCoins();
		addSemiBrokenWalls();
		addStairs();
		addChest();
		addKeys();
		addPots();
		addCrates();
		addSlime();
		addButtons();
		addBlueMan();
	}
	
	public void addButtons()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				String value = sperateStaticMap[row][col] + "";
				int buttonType, changeRow, changeCol, typeChange, tiles_static, changeItem;
				if((value.contains("23") || value.contains("24")) && value.length() == 9)
				{
					buttonType = Integer.valueOf(value.substring(0,2));
					changeItem = Integer.valueOf(value.substring(2,3));
					typeChange = Integer.valueOf(value.substring(3,4));
					tiles_static = Integer.valueOf(value.substring(4,5));
					changeRow = Integer.valueOf(value.substring(5,7));
					changeCol = Integer.valueOf(value.substring(7,9));
					
					entityManager.addButtons(handler, getTileXPos(col), getTileYPos(row), buttonType, changeRow, changeCol, typeChange, tiles_static, changeItem);
					
				}
				
				if(sperateStaticMap[row][col] == -5)
				{
					entityManager.addButtons(handler, getTileXPos(col), getTileYPos(row), 23,-1,-1,-1,-1,-1);
					Buttons addButton = (Buttons) entityManager.getButtons().get(entityManager.getButtons().size()-1);
					addButton.setActionDone(true);
				}
				
				if(sperateStaticMap[row][col] == -6)
				{
					entityManager.addButtons(handler, getTileXPos(col), getTileYPos(row), 24,-1,-1,-1,-1,-1);
					Buttons addButton = (Buttons) entityManager.getButtons().get(entityManager.getButtons().size()-1);
					addButton.setActionDone(true);
				}
					
			}
		}
	}
	
	
	
	

	public void addPots()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 25)
					entityManager.addPots(handler, getTileXPos(col), getTileYPos(row), false);
				else if(sperateStaticMap[row][col] == -4)
					entityManager.addPots(handler, getTileXPos(col), getTileYPos(row), true);
			}
		}
	}
	
	public void addSemiBrokenWalls()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] >= 27 && sperateStaticMap[row][col] <= 29)
					entityManager.addSemiBrokenWall(handler, getTileXPos(col), getTileYPos(row),sperateStaticMap[row][col]);
			}
		}
	}
	
	public void addStairs()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				String value = sperateStaticMap[row][col] + "";
				int sendRow = 0;
				int senCol = 0;
				int stairType = 0;
				if( (value.contains("30") || value.contains("31") )&& value.length() == 4)
				{
					stairType = Integer.valueOf(value.substring(0,2));
					senCol = Integer.valueOf(value.substring(2,3));
					sendRow = Integer.valueOf(value.substring(3,4));
					entityManager.addStairs(handler, getTileXPos(col), getTileYPos(row), stairType,sendRow,senCol);
					
				}
					
			}
		}
	}
	
	public void addCrates()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] >= 18 && sperateStaticMap[row][col] <= 20)
					entityManager.addCrates(handler, getTileXPos(col), getTileYPos(row), sperateStaticMap[row][col]);
			}
		}
	}
	
	public void addSlime()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 32)
					entityManager.addSlime(handler, getTileXPos(col), getTileYPos(row));
			}
		}
	}
	
	
	public void addKeys()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] >= 5 && sperateStaticMap[row][col] <= 8)
					entityManager.addKeys(handler, getTileXPos(col), getTileYPos(row), sperateStaticMap[row][col]-4);
			}
		}
	}
	
	public void addCoins()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 9)
					entityManager.addCoins(handler, getTileXPos(col), getTileYPos(row));
			}
		}
	}
	
	public void addTorch()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 10)
					entityManager.addTorch(handler, getTileXPos(col), getTileYPos(row));
			}
		}
	}
	
	public void addArch()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 15)
					entityManager.addArch(handler, getTileXPos(col), getTileYPos(row));
			}
		}
	}
	
	
	
	public void addChest()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] >= 0 && sperateStaticMap[row][col] <= 1)
					entityManager.addChest(handler, getTileXPos(col), getTileYPos(row), 1,sperateStaticMap[row][col]);
				else if(sperateStaticMap[row][col] >= 2 && sperateStaticMap[row][col] <= 4)
					entityManager.addChest(handler, getTileXPos(col), getTileYPos(row),3,sperateStaticMap[row][col]);
				else if(sperateStaticMap[row][col] == -2) //Open Gold Chest
					entityManager.addChest(handler, getTileXPos(col), getTileYPos(row),2,sperateStaticMap[row][col]);
				else if(sperateStaticMap[row][col] == -3)//Open Silver Chets
					entityManager.addChest(handler, getTileXPos(col), getTileYPos(row),4,sperateStaticMap[row][col]);
				
			}
		}
	}
	
	public void addDoor()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 21)//Wood
					entityManager.addDoor(handler, getTileXPos(col), getTileYPos(row), 1);
				else if(sperateStaticMap[row][col] == 22)//Metal
					entityManager.addDoor(handler, getTileXPos(col), getTileYPos(row), 3);
				else if(sperateStaticMap[row][col] == -7)
					entityManager.addDoor(handler, getTileXPos(col), getTileYPos(row), 2);
				else if(sperateStaticMap[row][col] == -8)
					entityManager.addDoor(handler, getTileXPos(col), getTileYPos(row), 4);
			}
		}
	}
	
	public void addSpikes()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				String value = sperateStaticMap[row][col] + "";
				if(value.length() > 1)
				{
					int itemValue = Integer.parseInt(value.substring(0,2));
					if(itemValue == 11)
					{
						int oddEven = Integer.parseInt(value.substring(2,3));
						entityManager.addSpikes(handler, getTileXPos(col), getTileYPos(row), oddEven, 700);
					}
				}
			}
		}
	}
	
	public void addBlueMan()
	{
		A: for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				if(sperateStaticMap[row][col] == 33)
				{
					entityManager.getBlueMan().setX(getTileXPos(col));
					entityManager.getBlueMan().setY(getTileYPos(row));
					break A;
				}
				else
				{
					entityManager.getBlueMan().setX(-200);
					entityManager.getBlueMan().setY(-200);
				}
				
					
			}
		}
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------//	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
//======================================================LOADING WORLDS----------------------------------------------------------------------------//	

	public void loadWorld(String path) {
		width = 66;
		height = 55;
		
		tiles = new int[height][width];
		staticEntity = new int[height][width];
		
		//Loading Tiles
		loadTiles(path);
		loadStaticEntity();
		loadNextLevel(worldCol/11,worldRow/11);
		addAll();
		transitionEffect.start();
	}
	
	public void loadStaticEntity()
	{
		try {
			Scanner in = new Scanner(new File("res/World/FinalMapStatic.txt"));
			int row = 0;
			int col = 0;

			while (in.hasNextLine()) {
				Scanner line = new Scanner(in.nextLine());
				while (line.hasNextInt())
				{
					staticEntity[row][col++] = line.nextInt();
				}
				col = 0;
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void loadTiles(String path)
	{
		try {
			Scanner in = new Scanner(new File(path));
			int row = 0;
			int col = 0;

			while (in.hasNextLine()) {
				Scanner line = new Scanner(in.nextLine());
				
				while (line.hasNextInt()) {
					tiles[row][col] = line.nextInt();
							
					col++;
				}
				col = 0;
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(archSupportCoordinates);
	}
	
	public void loadSperateMap(int startRow, int startCol)
	{
		sperateMap = new int[11][11];
		int maprow = 0;
		int mapcol = 0;
		for(int row = startRow; row < startRow+11; row++)
		{
			for(int col = startCol; col < startCol+11; col++)
			{
				sperateMap[maprow][mapcol++] = tiles[row][col];
			}
			mapcol = 0;
			maprow++;
		}
	}
	
	public void loadSperateStaticEntityMap(int startRow, int startCol)
	{
		sperateStaticMap = new int[11][11];
		int maprow = 0;
		int mapcol = 0;
		for(int row = startRow; row < startRow+11; row++)
		{
			for(int col = startCol; col < startCol+11; col++)
			{
				sperateStaticMap[maprow][mapcol++] = staticEntity[row][col];
			}
			mapcol = 0;
			maprow++;
		}
	}
	
	
	public void loadNextLevel(int levelCol, int levelRow)
	{
		int endRow = levelRow*11;
		int endCol = levelCol*11;
		worldRow = endRow;
		worldCol = endCol;
				
		
		loadSperateMap(endRow, endCol);
		loadSperateStaticEntityMap(endRow,endCol);
		addAll();
	}
	
	public void printMap()
	{
		for(int row = 0; row < sperateStaticMap.length; row++)
		{
			for(int col = 0; col < sperateStaticMap[0].length; col++)
			{
				System.out.print(sperateStaticMap[row][col] + " ");
			}
			System.out.println();
		}
	}
	

//------------------------------------------------------------------------------------------------------------------------------------------------//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//------------------------------GETTERS AND SETTERS-------------------------------------------------------------------------------------------//
	
	
	public void offSet(int xOffset, int yOffSet)
	{
		offSetX = xOffset;
		offSetY = yOffSet;
	}
	
	public int[][] getStaticEntity() {
		return staticEntity;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public int[][] getSperateStaticMap() {
		return sperateStaticMap;
	}

	public void loadNewChest()
	{
		entityManager.getChestEntities().clear();
		addChest();
	}
	
	public int getTileYPos(int row)
	{
		return row*64;
	}
	
	public int getTileXPos(int col)
	{
		return col*64;
	}
	
	public EntityManager getEntityManager() {   
		return entityManager;
	}
	
	public Tile getTile(int x, int y)
	{
		return new Tile(null,sperateMap[x][y]);
	}
	
	public int[][] getTiles()
	{
		return tiles;
	}
	
	public void setTiles(int[][] tiles)
	{
		this.tiles = tiles;
	}
	
	public int getWorldRow()
	{
		return worldRow;
	}
	
	public int getWorldCol()
	{
		return worldCol;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	


//------------------------------------------------------------------------------------------------------------------------------------------------//	
	
}

package com.gameMaking.tilegame.entity;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ConcurrentModificationException;

import javax.swing.Timer;

import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;

public class Buttons extends StaticEntity implements ActionListener{

	
	/*handler.getWorld().loadSperateStaticEntityMap(worldRowOffSet, worldColOffSet);
				handler.getWorld().addAll();*/
	
	private int buttonType;
	private BufferedImage chest;
	private boolean pressed = false;
	
	//This determines whether the action after the button has pressed
	//This prevents from the action happening again if the player presses the button
	private boolean actionDone = false;
	
	//This will hold the location of which item that will change
	private int changeRow,changeCol;
	
	//TypeChange will indicate whether it will be a ADD or REMOVE operation
	private int typeChange;
	
	//This will indicate whether will are changing the tile or the static entity
	private int tiles_static;
	
	//This will be the name of the item that will be changed
	private int changeItemName;
	
	private int displayTime = 4;
	private Timer timer = new Timer(500, this);
	private int worldRow, worldCol;
	private float playerPosX, playerPosY;
	
	public Buttons(Handler handler, float x, float y, int buttonType, int changeRow, int changeCol, int typeChange, int tiles_static, int changeItemName) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		this.buttonType = buttonType;
		this.changeCol = changeCol;
		this.changeRow = changeRow;
		this.typeChange = typeChange;
		this.tiles_static = tiles_static;
		this.changeItemName = changeItemName;
		
		setButtonType(buttonType);
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = 64;
		bounds.height = 64;
		
	}

	@Override
	public void tick()
	{
	}
	
	public void pressButton()
	{
		pressed = true;
		if(buttonType == 23)
			setButtonType(25);
		else if(buttonType == 24)
			setButtonType(26);
		
	}
	
	public void changeWorld()
	{
		playerPosX = handler.getWorld().getEntityManager().getPlayer().getX();
		playerPosY = handler.getWorld().getEntityManager().getPlayer().getY();
		
		worldRow = handler.getWorld().getWorldRow();
		worldCol = handler.getWorld().getWorldCol();
		
		//IF THE CHANGES BEING MADE IS DONE ON THE SAME MAP YOU ARE CURRENTLY IN THEN THE TIMER IS NOT CALLED
		if(worldRow/11 == changeRow/11 && worldCol/11 == changeCol/11)
		{
			changeMapValues();
			if(tiles_static == 0)
			{
				handler.getWorld().loadSperateMap(worldRow, worldCol);
			}
			else if(tiles_static == 1)
			{
				handler.getWorld().loadSperateStaticEntityMap(worldRow, worldCol);
				handler.getWorld().loadNewChest();
			}
		}
		else if(!actionDone)
		{
			timer.start();
		}
	}
	
	public void changeMapValues()
	{
		//CHEST   0 = COIN    1 = SILVER KEY    2 = BLUE KEY    3 = BROWN KEY   4 = GOLD KEY
		int[][] world = null;
		
		
		if(tiles_static == 1 && !actionDone)
		{
			world = handler.getWorld().getStaticEntity();
			//0 = Add 1 = Remove
			if(typeChange == 0)
			{
				//ADDING GOLD CHEST WITH COIN
				if(changeItemName == 0)
					world[changeRow][changeCol] = 0;
				
				//ADDING GOLD CHEST WITH SILVER KEY
				else if(changeItemName == 1)
					world[changeRow][changeCol] = 1;

				//ADDING SILVER CHEST WITH BLUE KEY
				else if(changeItemName == 2)
					world[changeRow][changeCol] = 2;

				//ADDING SILVER CHEST WITH BROWN KEY
				else if(changeItemName == 3)
					world[changeRow][changeCol] = 3;

				//ADDING SILVER CHEST WITH GOLD
				else if(changeItemName == 4)
					world[changeRow][changeCol] = 4;
				
				actionDone = true;
				
			}
			else if(typeChange == 1 && !actionDone)
			{
				world[changeRow][changeCol] = -1;
				actionDone = true;
			}
		}
		
		//TILES
		if(tiles_static == 0 && !actionDone)
		{
			world = handler.getWorld().getTiles();
			//0 = Add 1 = Remove
			if(typeChange == 0)
			{
				world[changeRow][changeCol] = 44;
				actionDone = true;
				
			}
			else if(typeChange == 1)
			{
				world[changeRow][changeCol] = 1;
				actionDone = true;
				
			}
		}
	}
	
	public void setActionDone(boolean actionDone) {
		this.actionDone = actionDone;
	}

	public boolean isActionDone() {
		return actionDone;
	}

	public void releaseButton()
	{
		if(buttonType == 25 || buttonType == 26)
		{
			pressed = false;
			setButtonType(buttonType-2);
		}
		timer.stop();
		displayTime = 3;
		
	}
	
	public void setButtonType(int buttonType)
	{
		this.buttonType = buttonType;
		
		if(buttonType == 23)
			chest = Assets.greyButton;
		if(buttonType == 24)
			chest = Assets.redButton;
		if(buttonType == 25)
			chest = Assets.greyPressedButton;
		if(buttonType == 26)
			chest = Assets.redPressedButton;
	}
	
	public int getButtontType() {
		return buttonType;
	}
	
	public boolean isPressed() {
		return pressed;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(chest,(int) x, (int)y, width,height, null);
	}

	
	
	//THIS IS USED IF THE BUTTON YOU STEPED ON CHANGES THE MAP SOMEWHERE ELSE
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//DISABLE THE KEYBOARD SO THE PLAYER CAN'T MOVE IT AROUND WHILE DISPLAYING ANOTHER MAP
		handler.getKeyManager().disableKeyboard();
		
		System.out.println(displayTime);
		//FIRST DISPLAY THE MAP WHERE THE CHANGE IS BEING DONE
		if(displayTime == 3)
		{
			
			
			//THIS GETS RID OF THE PLAYER FROM THE SCREEN WHEN ANOTHER MAP IS SHOWN
			handler.getWorld().getEntityManager().getPlayer().setX(-100);
			handler.getWorld().getEntityManager().getPlayer().setY(-100);
			
			//THIS LOADS THE MAP, "changeCol" AND "changeRow" IS THE POSITION OF THE ITEM THAT IS CHANGING
			// CHANGECOL/11 GIVES YOU THE COLUMN OF THE MAP AND CHNAGEROW/11 GIVES YOU THE ROW OF THE MAP
			handler.getWorld().loadSperateMap((changeRow/11) * 11,(changeCol/11) * 11);
			handler.getWorld().loadSperateStaticEntityMap((changeRow/11) * 11,(changeCol/11) * 11);
			handler.getWorld().addAll();
		}
		//SHOW THE CHANGE
		else if(displayTime == 2)
		{
			//CHANGE THE VALUES IN THE MAP
			changeMapValues();
			
			//DISPLAY THAT CHANGE ON THE FRAME
			
			if(tiles_static == 0) // IF THE TILES ARE CHANGED THEN THE WORLD WILL LOAD THE CHNAGES THAT HAPPENS TO THE TILE
				handler.getWorld().loadSperateMap((changeRow/11) * 11,(changeCol/11) * 11);
			else//ELSE IF THE STATIC ENTITY ARE CHANGED THEN THE WORLD WILL LOAD THE CHANGES THAT HAPPENS TO THE STATIC ENTITY
			{
				handler.getWorld().loadSperateStaticEntityMap((changeRow/11) * 11,(changeCol/11) * 11);
				handler.getWorld().loadNewChest();
			}
			
		}
		//RETURN BACK TO THE MAP YOU WERE ORIGINALLY AT
		else if(displayTime == 0)
		{
			//LOAD THE ORIGINAL LEVEL
			handler.getWorld().loadNextLevel(worldCol/11,worldRow/11);
			
			//THE POSITION OF THE PLAYER BEFORE SWITCHING TO ANOTHER MAP IS STORED IN PLAYERPOSX AND PLAYPOSY, AND IS USED AGAIN WHEN RETURING BACK TO THE ORIGINAL MAP
			handler.getWorld().getEntityManager().getPlayer().setX(playerPosX);
			handler.getWorld().getEntityManager().getPlayer().setY(playerPosY);
			timer.stop();
			//ENABLE THE KEYBOARD
			handler.getKeyManager().enableKeyboard();
		}
		displayTime--;
		
		
	}
	
	
	

}

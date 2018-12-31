package com.gameMaking.tilegame;

import com.gameMaking.tilegame.gfx.GameCamera;
import com.gameMaking.tilegame.input.KeyManager;
import com.gameMaking.tilegame.world.Inventory;
import com.gameMaking.tilegame.world.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}

	public int getWidth()
	{
		return game.getWidth();
	}
	
	public int getHeight()
	{
		return game.getHeight();
	}
	
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public GameCamera getGameCamera()
	{
		return game.getGameCamera();
	}
	
	public Inventory getInventory()
	{
		return world.getInventory();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}

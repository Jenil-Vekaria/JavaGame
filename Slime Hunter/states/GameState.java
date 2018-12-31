package com.gameMaking.tilegame.states;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Pot;
import com.gameMaking.tilegame.entity.creature.Player;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;
import com.gameMaking.tilegame.world.World;

public class GameState extends State {
	
	Player player;
	private World world;
	Assets assets;
	public GameState(Handler handler) {
		super(handler);
		assets = new Assets();
		world = new World(handler,"res/World/FinalMap.txt");
		handler.setWorld(world);
		handler.getGameCamera().move(0, 0);
	}
	
	public void startMusic()
	{
		assets.playMusic();
		//world.start();
	}

	@Override
	public void tick()
	{
		world.tick();
	}

	@Override
	public void render(Graphics graphics) {
		world.render(graphics);
	}

}

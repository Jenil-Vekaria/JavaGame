package com.gameMaking.tilegame.states;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;
import com.gameMaking.tilegame.entity.Pot;
import com.gameMaking.tilegame.entity.creature.Player;
import com.gameMaking.tilegame.gfx.Animation;
import com.gameMaking.tilegame.gfx.Assets;
import com.gameMaking.tilegame.tiles.Tile;
import com.gameMaking.tilegame.world.World;

public class MenuState extends State {
	
	private Animation menuPlayer;
	private Animation menuSlime;
	private int slimePosY;
	public static boolean play, quit;
	
	public MenuState(Handler handler) {
		super(handler);
		menuPlayer = new Animation(100, Assets.player_right);
		menuSlime = new Animation(100, Assets.slime);
		slimePosY = 286;
		play = quit = false;
		Assets.playMenuBackgroundMusic();
	
	}
	
	public void play()
	{
		handler.getKeyManager().enter = false;
		setPlayGame(true);
		setQuitGame(false);
		Assets.stopMenuMusic();
	}
	
	public void quit()
	{
		handler.getKeyManager().enter = false;
		setQuitGame(true);
		setPlayGame(false);
		Assets.stopMenuMusic();
	}

	@Override
	public void tick()
	{
		if(handler.getKeyManager().down)
		{
			slimePosY = 416;
			Assets.playSound(Assets.slimeSound);
			handler.getKeyManager().down = false;
		}
		else if(handler.getKeyManager().up)
		{
			slimePosY = 286;
			Assets.playSound(Assets.slimeSound);
			handler.getKeyManager().up = false;
		}
		
		if(handler.getKeyManager().enter && slimePosY == 286)
			play();
		if(handler.getKeyManager().enter && slimePosY == 416)
			quit();
		
		
		
		menuPlayer.tick();
		menuSlime.tick();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.menu, 0, 0,704,704+64,null);
		graphics.drawImage(menuPlayer.getCurrentFrame(),74,105,64,64,null);
		graphics.drawImage(menuSlime.getCurrentFrame(),195,slimePosY,64,64,null);
	}

}

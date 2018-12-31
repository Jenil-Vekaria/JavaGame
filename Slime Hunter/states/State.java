package com.gameMaking.tilegame.states;

import java.awt.Graphics;

import com.gameMaking.tilegame.Game;
import com.gameMaking.tilegame.Handler;

public abstract class State
{
	private static State currentState = null;
	protected Handler handler;
	private boolean playGame = false;
	private boolean quitGame = false;
	
	public boolean isQuitGame() {
		return quitGame;
	}

	public void setQuitGame(boolean quitGame) {
		this.quitGame = quitGame;
	}

	public State(Handler handler)
	{
		this.handler = handler;
	}
	
	public static void setState(State state)
	{
		currentState = state;
	}
	
	
	public boolean isPlayGame() {
		return playGame;
	}

	public void setPlayGame(boolean playGame) {
		this.playGame = playGame;
	}

	public static State getState()
	{
		return currentState;
	}
	
	
	public abstract void tick();
	
	public abstract void render(Graphics graphics);
}

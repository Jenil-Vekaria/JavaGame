package com.gameMaking.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	private boolean[] keys;
	public boolean up,down,left,right, attack, enter;
	public boolean optionUp, optionDown, optionEnter;
	public boolean disable = false;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	public void tick()
	{
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!disable)
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				up = true;
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				down = true;
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
				left = true;
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				right = true;
		}
		else
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				optionUp = true;
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				optionDown = true;
			else if(e.getKeyCode() == KeyEvent.VK_ENTER)
				optionEnter = true;
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!disable)
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				up = false;
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				down = false;
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
				left = false;
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				right = false;
			else if(e.getKeyCode() == KeyEvent.VK_SPACE)
				attack = true;
		}
		else
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				optionUp = false;
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				optionDown = false;
			else if(e.getKeyCode() == KeyEvent.VK_ENTER)
				optionEnter = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			enter = true;
	}
	
	public void disableKeyboard() {
		disable = true;
		up = down = left = right = false;
	}
	public void enableKeyboard()
	{
		disable = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

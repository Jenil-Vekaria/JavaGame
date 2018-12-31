package com.gameMaking.tilegame;


public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("TileGame", 704, 768);
		game.start();
	}

}

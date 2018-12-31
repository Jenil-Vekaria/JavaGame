package com.gameMaking.tilegame.gfx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.Timer;

import org.omg.PortableInterceptor.ClientRequestInterceptor;

public class Assets implements ActionListener {

	public static BufferedImage wallTL, wallTR, wallBL, wallBR, wallDown, walllSide, TRight, TLeft, TDown, TUp,
			WallFourWay, wallEndRight, wallEndLeft, wallEndUp, wallEndDown;
	public static BufferedImage singleWall, rock;
	public static BufferedImage plainTL, plainTR, plainTopMiddle, plainLeft, plainRight, plainMiddle, plainWallBL,
			plainWallSide, plainWallBR;
	public static BufferedImage brokenWallSide1, brokenWallSide2, brokenWallDown;
	public static BufferedImage semiBrokenWallRight, semiBrokenWallLeft, brokenRocks;

	// Floor
	public static BufferedImage floor, brokenFloor1, brokenFloor2, brokenFloor3, brokenFloor4, brokenFloor5,
			brokenFloor6, brokenFloor7, brokenFloor8, brokenFloor9, brokenFloor10, brokenFloor11, brokenFloor12,
			brokenFloor13, brokenFloor14, brokenFloor15;

	// Stairs
	public static BufferedImage stairsDown, stairsUp;

	// Pot
	public static BufferedImage pot, brokenPot;

	public static BufferedImage inventory;

	// Spikes
	public static BufferedImage[] spikes;

	// Chests + Keys
	public static BufferedImage closedSilverChest, openSilverChest;
	public static BufferedImage closedGoldChest, openGoldChest;
	public static BufferedImage silverKey, goldkey, brownkey, bluekey;

	// Crates
	public static BufferedImage singleCrate, stackedCratesTop,stackedCratesBottom;

	// Doors
	public static BufferedImage woodDoor, woodDoorOpen;
	public static BufferedImage metalDoor, metalDoorOpen;

	// Stairs
	public static BufferedImage stairs;
	// Arch
	public static BufferedImage arch1, arch2, arch3;
	public static BufferedImage arch4, arch5, arch6;

	// Arch Support
	public static BufferedImage archSupportRight, archSupportLeft;

	// Animated Frames
	public static BufferedImage[] coin;
	public static BufferedImage[] torch;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_attack_down, player_attack_up, player_attack_left, player_attack_right;
	
	//Slime
	public static BufferedImage[] slime;

	// Message
	public static BufferedImage silverkeyMessage, goldkeyMessage, bluekeyMessage, brownkeyMessage, coinMessage;

	// Buttons
	public static BufferedImage greyButton, greyPressedButton;
	public static BufferedImage redButton, redPressedButton;
	// SOUND
	public static Clip sound, menuMusic;
	public static File coinSound, keySound, chestSound, woodDoorSound, metalDoorSound, lockedDoor, buttonsound,brokenPotSound, slimeSound;
	public Timer backgroundMusic;
	
	//OLDMAN
	public static BufferedImage blueManUp, blueManDown, blueManLeft, blueManRight;
	
	//MENU
	public static BufferedImage menu;
	int count = 1;
	public static final int width = 16, height = 16;

	public Assets() {
		backgroundMusic = new Timer(88000, this);
		
	}
	public void playMusic()
	{
		playBackgroundSound();
		backgroundMusic.start();
	}

	// Loads everything, it will be called once
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/spritesheet.png"));
		SpriteSheet charactersheet = new SpriteSheet(ImageLoader.loadImage("/texture/player.png"));
		SpriteSheet leftsheet = new SpriteSheet(ImageLoader.loadImage("/texture/leftpic.png"));
		SpriteSheet leftAttack = new SpriteSheet(ImageLoader.loadImage("/texture/leftattack.png"));
		SpriteSheet inventoryImage = new SpriteSheet(ImageLoader.loadImage("/texture/inventory.png"));
		SpriteSheet key = new SpriteSheet(ImageLoader.loadImage("/texture/Message.png"));
		SpriteSheet slimeSheet = new SpriteSheet(ImageLoader.loadImage("/texture/slime.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/texture/GameMenu.png"));
		SpriteSheet blueMan = new SpriteSheet(ImageLoader.loadImage("/texture/blueMan.png"));
		
		// Down
		player_down = new BufferedImage[6];

		for (int x = 0; x < player_down.length; x++) {
			player_down[x] = charactersheet.crop(getX(x) * 2, getY(1) * 2, width * 2, height * 2);
		}
		// Up
		player_up = new BufferedImage[6];

		for (int x = 0; x < player_up.length; x++) {
			player_up[x] = charactersheet.crop(getX(x) * 2, getY(2) * 2, width * 2, height * 2);
		}

		// Right
		player_right = player_down;

		// Left
		player_left = new BufferedImage[6];
		int leftIndex = 0;

		for (int x = 5; x >= 0; x--) {
			player_left[leftIndex++] = leftsheet.crop(getX(x) * 2, getY(0), width * 2, height * 2);
		}

		// Attack Right
		player_attack_right = new BufferedImage[5];

		for (int x = 0; x < player_attack_right.length; x++) {
			player_attack_right[x] = charactersheet.crop(getX(x) * 2, getY(3) * 2, width * 2, height * 2);
		}

		// Attack Left
		player_attack_left = new BufferedImage[5];
		leftIndex = 0;
		for (int x = 4; x >= 0; x--) {
			player_attack_left[leftIndex++] = leftAttack.crop(getX(x) * 2, getY(0) * 2, width * 2, height * 2);
		}

		// Attack Up
		player_attack_up = new BufferedImage[6];

		for (int x = 0; x < player_attack_up.length; x++) {
			player_attack_up[x] = charactersheet.crop(getX(x) * 2, getY(4) * 2, width * 2, height * 2);
		}

		// Attack Down
		player_attack_down = new BufferedImage[6];

		for (int x = 0; x < player_attack_down.length; x++) {
			player_attack_down[x] = charactersheet.crop(getX(x) * 2, getY(5) * 2, width * 2, height * 2);
		}
		
		
		// Coin
		coin = new BufferedImage[4];

		for (int x = 0; x < coin.length; x++) {
			coin[x] = sheet.crop(getX(x), getY(13), width, height);
		}

		// Torch
		torch = new BufferedImage[5];

		for (int x = 0; x < torch.length; x++) {
			torch[x] = sheet.crop(getX(x), getY(15), width, height);
		}

		// Spikes
		spikes = new BufferedImage[2];
		spikes[0] = sheet.crop(getX(2), getY(11), width, height);
		spikes[1] = sheet.crop(getX(0), getY(11), width, height);
		
		//Slime
		slime = new BufferedImage[9];
		
		for (int x = 0; x < slime.length; x++) {
			slime[x] = slimeSheet.crop(getX(x), 0, width, height);
		}
		
		// Arch
		arch1 = sheet.crop(getX(5), getY(3), width, height);
		arch2 = sheet.crop(getX(6), getY(3), width, height);
		arch3 = sheet.crop(getX(7), getY(3), width, height);
		arch4 = sheet.crop(getX(5), getY(4), width, height);
		arch5 = sheet.crop(getX(6), getY(4), width, height);
		arch6 = sheet.crop(getX(7), getY(4), width, height);

		// Arch Support
		archSupportLeft = sheet.crop(getX(7), getY(5), width, height);
		archSupportRight = sheet.crop(getX(5), getY(5), width, height);

		// Walls
		wallTL = sheet.crop(getX(0), getY(6), width, height);
		wallTR = sheet.crop(getX(3), getY(6), width, height);
		wallBL = sheet.crop(getX(0), getY(9), width, height);
		wallBR = sheet.crop(getX(3), getY(9), width, height);

		wallDown = sheet.crop(getX(0), getY(8), width, height);
		walllSide = sheet.crop(getX(2), getY(6), width, height);

		TUp = sheet.crop(getX(1), getY(9), width, height);
		TDown = sheet.crop(getX(2), getY(8), width, height);
		TLeft = sheet.crop(getX(3), getY(7), width, height);
		TRight = sheet.crop(getX(0), getY(7), width, height);

		WallFourWay = sheet.crop(getX(1), getY(6), width, height);

		wallEndUp = sheet.crop(getX(1), getY(8), width, height);
		wallEndDown = sheet.crop(getX(2), getY(9), width, height);
		wallEndLeft = sheet.crop(getX(2), getY(7), width, height);
		wallEndRight = sheet.crop(getX(1), getY(7), width, height);

		floor = sheet.crop(getX(0), getY(0), width, height);

		singleWall = sheet.crop(getX(4), getY(6), width, height);
		rock = sheet.crop(getX(7), getY(2), width, height);

		// Plain Walls
		plainTL = sheet.crop(getX(0), getY(3), width, height);
		plainTR = sheet.crop(getX(2), getY(3), width, height);
		plainLeft = sheet.crop(getX(0), getY(4), width, height);
		plainRight = sheet.crop(getX(2), getY(4), width, height);
		plainTopMiddle = sheet.crop(getX(1), getY(3), width, height);
		plainWallBL = sheet.crop(getX(0), getY(5), width, height);
		plainWallBR = sheet.crop(getX(2), getY(5), width, height);
		plainWallSide = sheet.crop(getX(1), getY(5), width, height);
		plainMiddle = sheet.crop(getX(1), getY(4), width, height);

		// Doors
		woodDoor = sheet.crop(getX(4), getY(7), width, height);
		woodDoorOpen = sheet.crop(getX(5), getY(6), width, height * 2);
		metalDoor = sheet.crop(getX(6), getY(7), width, height);
		metalDoorOpen = sheet.crop(getX(7), getY(6), width, height * 2);

		// Crates
		singleCrate = sheet.crop(getX(6), getY(9), width, height);
		stackedCratesTop = sheet.crop(getX(7), getY(8), width, height);
		stackedCratesBottom = sheet.crop(getX(7), getY(9), width, height);

		// Chest + key
		closedSilverChest = sheet.crop(getX(4), getY(10), width, height);
		openSilverChest = sheet.crop(getX(5), getY(10), width, height);
		closedGoldChest = sheet.crop(getX(4), getY(11), width, height);
		openGoldChest = sheet.crop(getX(5), getY(11), width, height);
		silverKey = sheet.crop(getX(6), getY(10), width, height);
		goldkey = sheet.crop(getX(6), getY(11), width, height);
		bluekey = sheet.crop(getX(7), getY(10), width, height);
		brownkey = sheet.crop(getX(7), getY(11), width, height);

		// Message
		silverkeyMessage = key.crop(0, 0, 250, 40);
		goldkeyMessage = key.crop(0, 60, 250, 40);
		bluekeyMessage = key.crop(0, 120, 250, 40);
		brownkeyMessage = key.crop(0, 180, 250, 40);
		coinMessage = key.crop(0, 240, 250, 40);

		// Inventory
		inventory = inventoryImage.crop(0, 0, 704, 64);

		// Buttons
		greyButton = sheet.crop(getX(0), getY(12), width, height);
		greyPressedButton = sheet.crop(getX(1), getY(12), width, height);
		redButton = sheet.crop(getX(2), getY(12), width, height);
		redPressedButton = sheet.crop(getX(3), getY(12), width, height);

		// Floor
		brokenFloor1 = sheet.crop(getX(1), getY(0), width, height);
		brokenFloor2 = sheet.crop(getX(2), getY(0), width, height);
		brokenFloor3 = sheet.crop(getX(3), getY(0), width, height);

		brokenFloor4 = sheet.crop(getX(0), getY(1), width, height);
		brokenFloor5 = sheet.crop(getX(1), getY(1), width, height);
		brokenFloor6 = sheet.crop(getX(2), getY(1), width, height);
		brokenFloor7 = sheet.crop(getX(3), getY(1), width, height);

		brokenFloor8 = sheet.crop(getX(0), getY(2), width, height);
		brokenFloor9 = sheet.crop(getX(1), getY(2), width, height);
		brokenFloor10 = sheet.crop(getX(2), getY(2), width, height);
		brokenFloor11 = sheet.crop(getX(3), getY(2), width, height);

		brokenFloor12 = sheet.crop(getX(4), getY(0), width, height);
		brokenFloor13 = sheet.crop(getX(5), getY(0), width, height);
		brokenFloor14 = sheet.crop(getX(4), getY(1), width, height);
		brokenFloor15 = sheet.crop(getX(5), getY(1), width, height);

		// Walls
		brokenWallSide1 = sheet.crop(getX(4), getY(2), width, height);
		brokenWallSide2 = sheet.crop(getX(5), getY(2), width, height);
		brokenWallDown = sheet.crop(getX(3), getY(8), width, height);

		semiBrokenWallLeft = sheet.crop(getX(6), getY(8), width, height);
		semiBrokenWallRight = sheet.crop(getX(4), getY(8), width, height);
		brokenRocks = sheet.crop(getX(5), getY(8), width, height);

		// Pot
		pot = sheet.crop(getX(0), getY(14), width, height);
		brokenPot = sheet.crop(getX(1), getY(14), width, height);

		// Stairs
		stairs = sheet.crop(getX(6), getY(2), width, height);
		stairsDown = sheet.crop(getX(6), getY(1), width, height);
		stairsUp = sheet.crop(getX(6), getY(0), width, height);

		// SOUND
		coinSound = new File("res/sounds/coin.wav");
		keySound = new File("res/sounds/key.wav");
		chestSound = new File("res/sounds/chest.wav");
		woodDoorSound = new File("res/sounds/woodDoor.wav");
		metalDoorSound = new File("res/sounds/metalDoor.wav");
		lockedDoor = new File("res/sounds/lockedDoor.wav");
		buttonsound = new File("res/sounds/buttons.wav");
		brokenPotSound = new File("res/sounds/pot.wav");
		slimeSound = new File("res/sounds/slime.wav");
		//menuMusic = new File("res/sounds/menu.wav");
		
		//MENU
		menu = menuSheet.crop(0, 0, 704,704);
		
		//OLDMAN
		blueManUp = blueMan.crop(getX(2)*2, getY(0), width*2, height*2);
		blueManDown = blueMan.crop(getX(1)*2, getY(0), width*2, height*2);
		blueManLeft = blueMan.crop(getX(0)*2, getY(0), width*2, height*2);
		blueManRight = blueMan.crop(getX(1)*2, getY(0), width*2, height*2);
	}

	public static void playSound(File path) {
		try {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(path));
			sound.start();

			// Thread.sleep(sound.getMicrosecondLength()/1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void stopSound()
	{
		sound.stop();
	}
	

	public static void playBackgroundSound() {
		try {
			Clip backgroundsound = AudioSystem.getClip();
			backgroundsound.open(AudioSystem.getAudioInputStream(new File("res/sounds/background.wav")));
			backgroundsound.start();

		} catch (Exception e) {
			System.out.println("not working'");
		}
	}
	
	public static void playMenuBackgroundMusic() {
		try {
			menuMusic = AudioSystem.getClip();
			menuMusic.open(AudioSystem.getAudioInputStream(new File("res/sounds/menu.wav")));
			menuMusic.start();

		} catch (Exception e) {
			System.out.println("not working'");
		}
	}
	
	public static void stopMenuMusic()
	{
		menuMusic.stop();
	}

	public static int getX(int x) {
		return x * width;
	}

	public static int getY(int y) {
		return y * height;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("PLAYING");
		playBackgroundSound();
	}
}

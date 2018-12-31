package com.gameMaking.tilegame.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	
	public static String LoadFileAsString(String path)
	{
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = bReader.readLine()) != null)
			{
				builder.append(line + "\n");
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		return builder.toString();
	}
	
	public static int parseInt(String number)
	{
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}

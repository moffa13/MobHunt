package me.ryandw11.mobhunt.util;

import java.util.Random;
import java.util.regex.Pattern;


public class Mobs {
	
	
	public static int genRandom(int low, int high){ //Randomly generate a number between low and high.
		Random r = new Random();
		int Result = r.nextInt(high-low) +low;
		return Result;
	}
	
	public static int splitFirst(String s){ // split the string than get the first int.
		String[] split = s.split(Pattern.quote(":"));
		try{
			return Integer.parseInt(split[0]);
		}catch(NumberFormatException e){
			return -1;
		}
		
	}
	
	public static int splitSecond(String s){ // split the string then get the second int.
		String[] split = s.split(":");
		try{
			return Integer.parseInt(split[1]);
		}catch(NumberFormatException e){
			return -1;
		}
	}
}

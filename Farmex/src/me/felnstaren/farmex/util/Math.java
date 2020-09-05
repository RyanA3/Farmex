package me.felnstaren.farmex.util;

public class Math {

	public static int min(int min, int in) {
		if(in < min) return min;
		else return in;
	}
	
	public static int max(int max, int in) {
		if(in > max) return max;
		else return in;
	}
	
	public static int minmax(int min, int max, int in) {
		if(in < min) return min;
		else if(in > max) return max;
		else return in;
	}
	
}

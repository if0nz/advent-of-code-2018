package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day05 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}

	public static void part1() throws URISyntaxException, IOException {
		var polymer = FileReader.readLines("/d05.txt").get(0);
		System.out.println(react(polymer).length());
	}
	
	public static void part2() throws URISyntaxException, IOException {
		var polymer = FileReader.readLines("/d05.txt").get(0);
		System.out.println(IntStream.range('A', 'Z').map(c -> {
			String p = polymer.replaceAll(Character.toString((char)c),"").replaceAll(Character.toString((char)(c+32)), "");
			return react(p).length();
		}).min().getAsInt());
	}
	
	public static String react(String polymer) {
		int i = 0;
		while (i<polymer.length()-1) {
			if (i>0 && Math.abs(polymer.charAt(i)-polymer.charAt(i+1)) == 32) {
				polymer = polymer.substring(0, i) + polymer.substring(i+2);
				i--;
			} 
			else {
				i++;
			}
		}
		return polymer;
	}
}


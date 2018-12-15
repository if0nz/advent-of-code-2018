package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.stream.Collectors;

import it.ifonz.common.FileReader;

public class Day01 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}

	public static void part1() throws URISyntaxException, IOException {
	System.out.println(
		FileReader.readLines("/d01.txt").stream().mapToLong(s -> Long.valueOf(s)).sum());
	}
	
	public static void part2() throws URISyntaxException, IOException {
	
		var changes = FileReader.readLines("/d01.txt").stream().map(s -> Long.valueOf(s)).collect(Collectors.toList());
		var frequencies = new HashSet<Long>();
		frequencies.add(0L);
		var currentFrequency = 0L;
		boolean twice = false;
		int i = 0;
		int size = changes.size();
		while (!twice) {
			currentFrequency+=Long.valueOf(changes.get(i));
			twice = !frequencies.add(currentFrequency);
			i = (i+1)%size;
		}
		
		System.out.println(currentFrequency);
		
	}
	
}

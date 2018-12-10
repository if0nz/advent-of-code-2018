package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day02 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}

	public static void part1() throws URISyntaxException, IOException {
		var ids = FileReader.readLines("/d02.txt");
		long counts[] = new long[2];
		ids.forEach(id -> {
			var map = new HashMap<Integer, Integer>();
			id.chars().forEach(c -> {
				Integer i = map.get(c);
				map.put(c, i != null ? Integer.valueOf(i+1) : 1);
			});
			counts[0] += map.entrySet().stream().filter(e -> e.getValue() == 3).count() > 0 ? 1 : 0;
			counts[1] += map.entrySet().stream().filter(e -> e.getValue() == 2).count() > 0 ? 1 : 0;
		});
		System.out.println(counts[0]*counts[1]);
	}
	
	public static void part2() throws URISyntaxException, IOException {
		var ids = FileReader.readLines("/d02.txt");
		StringBuilder s = new StringBuilder();
		int size = ids.size();
		IntStream.range(0, size).forEach(i -> {
			IntStream.range(i+1, size).forEach(j -> {
				var id_i = ids.get(i);
				var id_j = ids.get(j);
				if (stringDistance(id_i,id_j) == 1) {
					IntStream.range(0, id_i.length()).forEach(k -> {
						char char_k = id_i.charAt(k);
						if (char_k == id_j.charAt(k)) 
							s.append((char)char_k);
					});
				}
			});
		});
		System.out.println(s.toString());
	}
	
	private static int stringDistance(String s1, String s2) {
		int count[] = {0};
		IntStream.range(0, s1.length()).forEach(i -> count[0] += s1.charAt(i) == s2.charAt(i) ? 0 : 1);
		return count[0];
	}
}

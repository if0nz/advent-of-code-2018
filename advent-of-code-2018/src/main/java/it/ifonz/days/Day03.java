package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day03 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part01();
		part02();
	}

	public static void part01() throws URISyntaxException, IOException {
		var claims = FileReader.readLines("/d03.txt");
		String[][] fabric = new String[1000][1000];
		claims.forEach(claim -> {
			var tokens = claim.split(" ");
			int leftEdge = Integer.valueOf(tokens[2].split(",")[0]);
			int topEdge = Integer.valueOf(tokens[2].split(",")[1].split(":")[0]);
			int width = Integer.valueOf(tokens[3].split("x")[0]);
			int heigth = Integer.valueOf(tokens[3].split("x")[1]);
			IntStream.range(leftEdge, leftEdge+width).forEach(i -> {
				IntStream.range(topEdge, topEdge+heigth).forEach(j -> {
					fabric[i][j] = fabric[i][j] == null ? "O" : "X";
				});
			});
		});
		System.out.println(Arrays.stream(fabric).mapToLong(c -> 
		Arrays.stream(c).filter(s -> 
		"X".equals(s)).count()).sum());
	}
	
	public static void part02() throws URISyntaxException, IOException {
		var claims = FileReader.readLines("/d03.txt");
		String[][] fabric = new String[1000][1000];
		Integer[] overlapping = new Integer[claims.size()];
		claims.forEach(claim -> {
			
			var tokens = claim.split(" ");
			overlapping[Integer.valueOf(tokens[0].split("#")[1])-1]=0;
			int leftEdge = Integer.valueOf(tokens[2].split(",")[0]);
			int topEdge = Integer.valueOf(tokens[2].split(",")[1].split(":")[0]);
			int width = Integer.valueOf(tokens[3].split("x")[0]);
			int heigth = Integer.valueOf(tokens[3].split("x")[1]);
			IntStream.range(leftEdge, leftEdge+width).forEach(i -> {
				IntStream.range(topEdge, topEdge+heigth).forEach(j -> {
					if (fabric[i][j]==null) {
						fabric[i][j] = tokens[0];
					} else {
						overlapping[Integer.valueOf(tokens[0].split("#")[1])-1]++;
						overlapping[Integer.valueOf(fabric[i][j].split("#")[1])-1]++;
					}
				});
			});
		});
		System.out.println(Arrays.asList(overlapping).indexOf(0));
	}
	
}

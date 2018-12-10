package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day06 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}
	
	public static void part1() throws URISyntaxException, IOException {
		var coords = FileReader.readCoords("/d06.txt");
		int max_x = coords.stream().max((c1, c2)-> c1.x - c2.x).get().x;
		int max_y = coords.stream().max((c1, c2)-> c1.y - c2.y).get().y;
		int[][] grid = new int[max_x][max_y];
		IntStream.range(0, max_x).forEach(i -> {
			IntStream.range(0, max_y).forEach(j -> {
				int[] shortestDistances = new int[2];
				shortestDistances[0] = Integer.MAX_VALUE;
				shortestDistances[1] = Integer.MAX_VALUE;
				int[] nearestCoords = new int[1];
				coords.forEach(c ->  {
					int distance = Math.abs(i - c.x) + Math.abs(j - c.y);
					if (distance < shortestDistances[0]) {
						shortestDistances[0] = distance;
						nearestCoords[0] = c.hashCode();
					} else if (distance < shortestDistances[1]) {
						shortestDistances[1] = distance;
					}
				});
				grid[i][j] = (shortestDistances[0] != shortestDistances[1]) ? nearestCoords[0] : -1;
			});
		});
		var infiniteCoords = new HashSet<Integer>();
		IntStream.range(0, max_x).forEach(i -> {
			infiniteCoords.add(grid[i][0]);
			infiniteCoords.add(grid[i][max_y-1]);
		});
		IntStream.range(0, max_y).forEach(j -> {
			infiniteCoords.add(grid[0][j]);
			infiniteCoords.add(grid[max_x-1][j]);
		});
		System.out.println(coords.stream().mapToLong(c -> {
			if (infiniteCoords.contains(c.hashCode())) return 0;
			return Arrays.stream(grid).mapToLong(row -> 
				Arrays.stream(row).filter(column -> column == c.hashCode()).count()).sum();
		}).max().getAsLong());
	}
	
	public static void part2() throws URISyntaxException, IOException {
		var coords = FileReader.readCoords("/d06.txt");
		int max_x = coords.stream().max((c1, c2)-> c1.x - c2.x).get().x;
		int max_y = coords.stream().max((c1, c2)-> c1.y - c2.y).get().y;
		int[][] grid = new int[max_x][max_y];
		IntStream.range(0, max_x).forEach(i -> {
			IntStream.range(0, max_y).forEach(j -> {
				grid[i][j] = coords.stream().mapToInt(c -> Math.abs(i - c.x) + Math.abs(j - c.y)).sum();
			});
		});
		
		System.out.println(
				Arrays.stream(grid).mapToLong(row -> Arrays.stream(row).filter(column -> column < 10000).count()).sum()
		);
	}
	
}

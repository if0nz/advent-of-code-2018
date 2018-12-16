package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day11 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}

	public static void part1() throws URISyntaxException, IOException {
		int serial = Integer.valueOf(FileReader.readLines("/d11.txt").get(0));
		long[][] grid = new long[301][301];
		IntStream.rangeClosed(1, 300).forEach(x -> {
			IntStream.rangeClosed(1, 300).forEach(y -> {
				grid[x][y] = initSingleCell(serial, x, y);
			});
		});
		int maxX = 0;
		int maxY = 0;
		long maxValue = Long.MIN_VALUE;
		for (int i = 1; i<299; i++) {
			for (int j = 1; j<299; j++) {
				long sum = squareSum(grid, i, j, 3);
				if (sum > maxValue) {
					maxValue = sum;
					maxX = i;
					maxY = j;
				}
			}
		}
		System.out.println(maxX+","+maxY);
	}

	private static long initSingleCell(int serial, int x, int y) {
		long rackId = x+10;
		long power = y*rackId;
		power+=serial;
		power*=rackId;
		return (power/100)%10-5;
	}

	public static void part2() throws URISyntaxException, IOException {
		int serial = Integer.valueOf(FileReader.readLines("/d11.txt").get(0));
		long[][] grid = new long[301][301];
		IntStream.rangeClosed(1, 300).forEach(x -> {
			IntStream.rangeClosed(1, 300).forEach(y -> {
				grid[x][y] = initSingleCell(serial, x, y);
			});
		});
		int maxX = 0;
		int maxY = 0;
		int maxSize = 0;
		long maxValue = Long.MIN_VALUE;
		for (int i = 1; i<299; i++) {
			for (int j = 1; j<299; j++) {
				for (int size = 1; size < 301-Math.max(i, j); size++) {
					long sum = squareSum(grid, i, j, size);
					if (sum > maxValue) {
						maxValue = sum;
						maxX = i;
						maxY = j;
						maxSize = size;
					}
				}
			}
		}
		
		System.out.println(maxX+","+maxY+","+maxSize);
	}
	
	private static long squareSum(long[][] grid, int i, int j, int size) {
		long sum = 0l;
		for (int x = 0; x<size; x++) {
			for (int y = 0; y<size; y++) {
				sum+=grid[i+x][j+y];
			}
		}
		return sum;
	}
}

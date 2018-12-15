package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day09 {
	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}

	public static void part1() throws URISyntaxException, IOException {
		var input = FileReader.readLines("/d09.txt").get(0).split(" ");
		Integer playersQty = Integer.valueOf(input[0]);
		long[] players = new long[playersQty];
		IntStream.range(0, playersQty).forEach(p -> players[p] = 0);
		int marbles = Integer.valueOf(input[6]);
		playTheGame(playersQty, players, marbles);
		System.out.println(Arrays.stream(players).max().getAsLong());
	}

	public static void part2() throws URISyntaxException, IOException {
		var input = FileReader.readLines("/d09.txt").get(0).split(" ");
		Integer playersQty = Integer.valueOf(input[0]);
		var players = new long[playersQty];
		int marbles = Integer.valueOf(input[6])*100;
		playTheGame(playersQty, players, marbles);
		System.out.println(Arrays.stream(players).max().getAsLong());
	}

	private static void playTheGame(Integer playersQty, long[] players, int marbles) {
		var circle = new ArrayDeque<Long>();
		int currentPlayer = 0;
		circle.add(0L);
		for (long m = 1; m <= marbles; m++) {
			if (m % 23 == 0) {
				currentPlayer+=23;
				IntStream.range(0,7).forEach(i ->{
					var l = circle.pollLast();
					if (l!= null) circle.offerFirst(l);
				});
				players[currentPlayer%playersQty] += (m + circle.pollFirst());
			} else {

				IntStream.range(0, 2).forEach(i -> {
					var f = circle.pollFirst();
					if (f != null) circle.offerLast(f);
				});
				circle.offerFirst(m);
			}
		}
	}
}

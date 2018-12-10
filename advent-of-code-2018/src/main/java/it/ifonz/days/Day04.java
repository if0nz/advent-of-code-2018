package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

import it.ifonz.common.FileReader;

public class Day04 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}

	public static void part1() throws URISyntaxException, IOException {
		var minutesAsleep = evalLogs();
		var maxAsleep = minutesAsleep.entrySet().stream().max((g1,g2) -> Arrays.stream(g1.getValue()).mapToInt(i -> i).sum() - Arrays.stream(g2.getValue()).mapToInt(i -> i).sum()).get();
		var index = Arrays.asList(maxAsleep.getValue()).indexOf((Arrays.stream(maxAsleep.getValue()).max(Integer::compareTo).get()));
		System.out.println(maxAsleep.getKey()*(index));
	}

	public static void part2() throws URISyntaxException, IOException {
		var minutesAsleep = evalLogs();
		var maxAsleep = minutesAsleep.entrySet().stream().max((g1,g2) -> Arrays.stream(g1.getValue()).max(Integer::compare).get() - Arrays.stream(g2.getValue()).max(Integer::compare).get()).get();
		var index = Arrays.asList(maxAsleep.getValue()).indexOf((Arrays.stream(maxAsleep.getValue()).max(Integer::compareTo).get()));
		System.out.println(maxAsleep.getKey()*(index));
	}
	
	private static HashMap<Integer, Integer[]> evalLogs() throws URISyntaxException, IOException {
		var logs = FileReader.readLines("/d04.txt");
		var df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		logs.sort((s1, s2) -> {
			var date1 = LocalDateTime.parse(s1.split("\\[")[1].split("\\]")[0], df);
			var date2 = LocalDateTime.parse(s2.split("\\[")[1].split("\\]")[0], df);
			return date1.compareTo(date2);
		});
		int guard = -1;
		var minutesAsleep = new HashMap<Integer, Integer[]>();
		var falls = LocalDateTime.MIN;
		for (String log : logs) {
			if (log.contains("shift")) {
				guard = Integer.valueOf(log.split("#")[1].split(" ")[0]);
			} else if (log.contains("falls")) {
				falls = LocalDateTime.parse(log.split("\\[")[1].split("\\]")[0], df);
				if (falls.getHour() == 23) {
					falls.plusMinutes(60-falls.getMinute());
				}
			} else {
				var wakes = LocalDateTime.parse(log.split("\\[")[1].split("\\]")[0], df);
				var minutes = minutesAsleep.get(guard);
				if (minutes == null) {
					minutes = new Integer[60];
					for (int i=0; i<60; i++) minutes[i]=0;
				}
				for (int i=falls.getMinute(); i<wakes.getMinute(); i++) 
					minutes[i]++;
				minutesAsleep.put(guard,minutes);
			}
		}
		return minutesAsleep;
	}
}

package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import it.ifonz.bean.Coord;
import it.ifonz.bean.Particle;
import it.ifonz.common.FileReader;

public class Day10 {

	public static void main(String[] args) throws URISyntaxException, IOException {

		part1();
		part2();
		
	}

	public static void part1() throws URISyntaxException, IOException {
		var lines = FileReader.readLines("/d10.txt");
		var particles = lines.stream().map(l -> 
			new Particle(
					new Coord(l.split("<")[1].split(",")[0], l.split(",")[1].split(">")[0]), 
					new Coord(l.split("<")[2].split(",")[0], l.split(",")[2].split(">")[0])
				
		)).collect(Collectors.toList());
		
		boolean atLeastOneSingle = true;
		
		while(atLeastOneSingle) {
			particles.forEach(p -> {
				p.position.x+=p.velocity.x;
				p.position.y+=p.velocity.y;
			});
			atLeastOneSingle = particles.stream().filter(p1 -> 
				particles.stream().filter(p2 -> !p1.equals(p2) && (Math.abs(p1.position.x - p2.position.x) + Math.abs(p1.position.y - p2.position.y)) <= 2).count() > 0
			).count() < particles.size();
		}
		long minX = particles.stream().min((p1, p2) -> p1.position.x - p2.position.x).get().position.x;
		long minY = particles.stream().min((p1, p2) -> p1.position.y - p2.position.y).get().position.y;
		long maxX = particles.stream().max((p1, p2) -> p1.position.x - p2.position.x).get().position.x;
		long maxY = particles.stream().max((p1, p2) -> p1.position.y - p2.position.y).get().position.y;
		LongStream.rangeClosed(minY, maxY).forEach(j -> {
			LongStream.rangeClosed(minX, maxX).forEach(i -> {
				System.out.print(
						particles.stream().filter(p -> p.position.x == i && p.position.y == j).findAny().orElse(null) != null ? "#" : ".");
			});
			System.out.println();
		});
	}

	public static void part2() throws URISyntaxException, IOException {
		var lines = FileReader.readLines("/d10.txt");
		var particles = lines.stream().map(l -> 
			new Particle(
					new Coord(l.split("<")[1].split(",")[0], l.split(",")[1].split(">")[0]), 
					new Coord(l.split("<")[2].split(",")[0], l.split(",")[2].split(">")[0])
				
		)).collect(Collectors.toList());
		
		boolean atLeastOneSingle = true;
		long[] seconds = {0};
		while(atLeastOneSingle) {
			seconds[0]++;
			particles.forEach(p -> {
				p.position.x+=p.velocity.x;
				p.position.y+=p.velocity.y;
			});
			atLeastOneSingle = particles.stream().filter(p1 -> 
				particles.stream().filter(p2 -> !p1.equals(p2) && (Math.abs(p1.position.x - p2.position.x) + Math.abs(p1.position.y - p2.position.y)) <= 2).count() > 0
			).count() < particles.size();
		}
		System.out.println(seconds[0]);
	}
}

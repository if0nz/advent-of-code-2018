package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.bean.Node;
import it.ifonz.common.FileReader;

public class Day08 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}
	
	public static void part1() throws URISyntaxException, IOException {
		var tree = new ArrayDeque<>(Arrays.stream(FileReader.readLines("/d08.txt").get(0).split(" "))
				.map(s -> Integer.valueOf(s)).collect(Collectors.toList()));
		
		Node root = createNewNode(tree);
		System.out.println(root.sum());
	}
	
	public static void part2() throws URISyntaxException, IOException {
		var tree = new ArrayDeque<>(Arrays.stream(FileReader.readLines("/d08.txt").get(0).split(" "))
				.map(s -> Integer.valueOf(s)).collect(Collectors.toList()));
		
		Node root = createNewNode(tree);
		System.out.println(root.value());
	}

	private static Node createNewNode(ArrayDeque<Integer> subTree) {
		int childrensQty = subTree.poll();
		int metadataQty = subTree.poll();
		var childrens = new ArrayList<Node>();
		IntStream.range(0, childrensQty).forEach(i -> {
			childrens.add(createNewNode(subTree));
		});
		var metadata = new ArrayList<Integer>();
		IntStream.range(0, metadataQty).forEach(i -> {
			metadata.add(subTree.poll());
		});
		
		return new Node(childrens, metadata);
	}
}

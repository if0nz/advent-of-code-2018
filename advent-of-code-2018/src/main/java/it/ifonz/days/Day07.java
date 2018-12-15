package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.bean.Task;
import it.ifonz.common.FileReader;

public class Day07 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		part1();
		part2();
	}
	
	public static void part1() throws URISyntaxException, IOException {
		var queue = initQueue();
		var execution = new StringBuilder();
		do {
			var currentNode = queue.poll();
			var processingLater = new ArrayList<Task>();
			if (!currentNode.fathers.isEmpty()) {
				while (currentNode.fathers.stream().filter(f -> !f.finished).findFirst().orElse(null) != null) {
					processingLater.add(currentNode);
					currentNode = queue.poll();
				}
			}
			execution.append(currentNode.label);
			currentNode.finished = true;
			processingLater.addAll(currentNode.childs);
			processingLater.forEach(n -> {
				if (!queue.contains(n) && !n.finished) queue.add(n);
			});
		} while (!queue.isEmpty());
		System.out.println(execution);
	}
	
	public static void part2() throws URISyntaxException, IOException {
		var queue = initQueue();
		var workers = new Task[5];
		int totalTimeElapsed = 0;
		do {
			var currentNode = queue.poll();
			var processingLater = new ArrayList<Task>();
			if (currentNode != null && !currentNode.fathers.isEmpty()) {
				while (currentNode != null && currentNode.fathers.stream().filter(f -> !f.finished).findFirst().orElse(null) != null) {
					processingLater.add(currentNode);
					currentNode = queue.poll();
				}
			}
			processingLater.forEach(n -> {
				if (!queue.contains(n) && !n.finished) queue.add(n);
			});
			if (currentNode == null) {
				currentNode = Arrays.stream(workers).min((w1, w2) -> {
					if (w1 != null && w2 == null) return -1;
					if (w2 != null && w1 == null) return 1;
					if (w1 == null && w2 == null) return 0;
					return (w1.requiredTime - w1.timeElapsed) - (w2.requiredTime - w2.timeElapsed);
				}).get();
				workers[Arrays.asList(workers).indexOf(currentNode)] = null;
				currentNode.finished = true;
				int timeElapsed = currentNode.requiredTime - currentNode.timeElapsed;
				totalTimeElapsed+=timeElapsed;
				Arrays.stream(workers).filter(w -> w!= null).forEach(w -> {
					w.timeElapsed+=timeElapsed;
				});
				currentNode.childs.stream().forEach(n -> {
					if (!queue.contains(n) && !n.finished) queue.add(n);
				});
			}
			if (!currentNode.finished) {
				int i=Arrays.asList(workers).indexOf(null);
				if (i > -1) {
					workers[i] = currentNode;
					currentNode.childs.stream().forEach(n -> {
						if (!queue.contains(n) && !n.finished) queue.add(n);
					});
				} 
			}
			
		} while (!queue.isEmpty() || Arrays.stream(workers).filter(w -> w!=null).count() > 0);
		System.out.println(totalTimeElapsed);
	}

	private static PriorityQueue<Task> initQueue() throws URISyntaxException, IOException {
		var instructions = FileReader.readLines("/d07.txt");
		var dag = new HashSet<Task>();
		IntStream.rangeClosed('A', 'Z').forEach(c -> dag.add(new Task(Character.toString(c))));
		instructions.stream().forEach(instruction -> {
			var fatherLabel = String.valueOf(instruction.split("Step ")[1].charAt(0));
			var childrenLabel = String.valueOf(instruction.split("step ")[1].charAt(0));
			var fatherNode = dag.stream().filter(n -> n.label.equals(fatherLabel)).findAny().get();
			var childrenNode = dag.stream().filter(n -> n.label.equals(childrenLabel)).findAny().get();
			fatherNode.childs.add(childrenNode);
			childrenNode.fathers.add(fatherNode);
		});
		var roots = dag.stream().filter(node -> node.fathers.isEmpty()).collect(Collectors.toList());
		roots.stream().sorted((r1, r2) -> r1.label.compareTo(r2.label));
		var queue = new PriorityQueue<Task>((n1, n2) -> n1.label.compareTo(n2.label));
		queue.addAll(roots);
		return queue;
	}
}

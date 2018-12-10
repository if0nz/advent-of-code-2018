package it.ifonz.bean;

import java.util.List;

public class Node {

	public List<Node> childs;
	public List<Integer> metadata;
	
	public Node(List<Node> childs, List<Integer> metadata) {
		super();
		this.childs = childs;
		this.metadata = metadata;
	}
	
	public long sum() {
		return metadata.stream().mapToLong(i -> i).sum() + childs.stream().mapToLong(c -> c.sum()).sum();
	}
	
	public long value() {
		if (childs.isEmpty()) return sum();
		var childsQty = childs.size();
		return metadata.stream().mapToLong(i -> childsQty >=i ? childs.get(i-1).value() : 0).sum();
	}
	
}

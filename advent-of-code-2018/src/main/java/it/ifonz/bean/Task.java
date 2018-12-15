package it.ifonz.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task {

	public String label;
	public List<Task> childs;
	public Set<Task> fathers;
	public boolean finished;
	public int requiredTime;
	public int timeElapsed;
	
	public Task(String label) {
		this.label = label;
		childs = new ArrayList<>();
		fathers = new HashSet<>();
		finished = false;
		requiredTime = 60 + label.charAt(0)-64;
		timeElapsed = 0;
	}
	
	public static Task NULL = new Task(" ");
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	
	
	
	
}

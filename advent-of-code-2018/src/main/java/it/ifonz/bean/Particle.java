package it.ifonz.bean;

import java.util.Objects;

public class Particle {

	public Coord position;
	public Coord velocity;

	public Particle(Coord position, Coord velocity) {
		super();
		this.position = position;
		this.velocity = velocity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		return Objects.equals(position, other.position);
	}

}

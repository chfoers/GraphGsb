package de.materna.Graph;

public class Kante {
	
	private String edge;
	private long id = 0;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Kante () {
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	@Override
	public String toString() {
		return "Kante [edge=" + edge + "]";
	}
	
	
	}



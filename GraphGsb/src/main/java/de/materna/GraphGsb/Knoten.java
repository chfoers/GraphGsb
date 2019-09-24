package de.materna.GraphGsb;

public class Knoten {
	
	private String name;
	private long id = 0;
	private String vertexpath;
	private boolean wurzel;
	
	public boolean isWurzel() {
		return wurzel;
	}

	public void setWurzel(boolean wurzel) {
		this.wurzel = wurzel;
	}

	public String getVertexpath() {
		return vertexpath;
	}

	public void setVertexpath(String vertexpath) {
		this.vertexpath = vertexpath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Knoten() {

	  }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Knoten [name=" + name + "]";
	}


	

}

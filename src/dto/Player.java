package dto;

import java.io.Serializable;

public class Player implements Comparable<Player>,Serializable{
	private String name;
	
	private int point;
	
	public Player(String name, int point) {
		super();
		this.name = name;
		this.point = point;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point 要设置的 point
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public int compareTo(Player pla) {		
		return pla.point-this.point;
	}
	
	
}

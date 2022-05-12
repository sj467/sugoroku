package model;

//座標を表すクラス
public class Coordinate {
	
	private int x; //x座標
	private int y; //y座標
	
	public Coordinate() {
		
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

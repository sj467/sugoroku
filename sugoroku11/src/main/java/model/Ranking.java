package model;

import java.io.Serializable;

//ランキングの情報を表すJavabeansクラス
public class Ranking implements Serializable {
	
	private String name; //名前
	private int point; //ポイント
	
	public Ranking() {
		
	}

	public Ranking(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}

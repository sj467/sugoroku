package model;

import java.io.Serializable;

//プレイヤーの情報を表すJavabeansクラス
public class PlayerData implements Serializable {
	
	private String name; //名前
	private int position; //マスの番号 Coordinate[] coordinateArrayのインデックスにあたる
	private int point; //ポイント
	private int diceRemain; //分岐地点に来た時の残りの進むマスの数
	private String mark; //画面上のプレイヤーの記号
	private int diceNumber; //サイコロの出た数
	
	public PlayerData() {
		
	}
	
	public PlayerData(String name, int position, int point, int diceRemain, String mark, int diceNumber) {
		super();
		this.name = name;
		this.position = position;
		this.point = point;
		this.diceRemain = diceRemain;
		this.mark = mark;
		this.diceNumber = diceNumber;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int massPosition) {
		this.position = massPosition;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDiceRemain() {
		return diceRemain;
	}

	public void setDiceRemain(int remainingToGo) {
		this.diceRemain = remainingToGo;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getDiceNumber() {
		return diceNumber;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}
}

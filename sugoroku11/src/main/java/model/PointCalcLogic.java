package model;

public class PointCalcLogic {
	//＋、ーのマスに来た時にポイントを増減する処理
	public int excute(PlayerData turnPlayer) {
		
		int point = 0;
		
		switch (turnPlayer.getPosition()) {
			case 36:
			case 45:
			case 54:
			case 100:
			case 109:
			case 118:
			case 137:
			case 145:
			case 155:
			case 191:
			case 200:
			case 205:
				point = (int)((Math.random() * 10) + 1) * 1000;
				turnPlayer.setPoint(turnPlayer.getPoint() + point);
				break;
			case 228:
			case 239:
			case 259:
			case 276:
				point = -(int)((Math.random() * 10) + 1) * 1000;
				turnPlayer.setPoint(turnPlayer.getPoint() + point);
				break;
		}
		return point;
	}
}

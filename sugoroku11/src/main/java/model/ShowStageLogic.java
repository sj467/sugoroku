package model;

public class ShowStageLogic {
	
	public String excute(PlayerData turnPlayer, PlayerData notTurnPlayer) {
		String stage = null; //マスを表す記号
		
		//スタートからゴールまでのマスの座標を表す
		int[][] coordinateDoubleArray = new int[][]{{0,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0},{10,0},{11,0},
			{12,0},{13,0},{13,1},{13,2},{12,2},{12,3},{11,3},{11,4},{10,4},{10,5},{9,5},{9,6},{8,6},{8,7},{7,7},{7,8},
			{7,9},{7,10},{7,11},{6,11},{6,12},{5,12},{5,13},{4,13},{4,14},{3,14},{3,15},{2,15},{2,16},{1,16},{1,17},
			{0,17},{0,18},{0,19},{1,19},{2,19},{3,19},{4,19},{5,19},{6,19},{7,19},{8,19},{9,19},{10,19},{11,19},
			{12,19},{13,19},{14,19},{15,19},{16,19},{17,19},{18,19},{19,19},{20,19},{21,19},{22,19},{23,19},{24,19},
			{25,19},{26,19},{27,19},{28,19},{29,19},{30,19},{31,19},{32,19},{33,19},{33,18},{33,17},{33,16},{33,15},
			{33,14},{33,13},{33,12},{33,11},{33,10},{33,9},{33,8},{33,7},{33,6},{33,5},{33,4},{32,4},{31,4},{30,4},
			{29,4},{28,4},{27,4},{26,4},{25,4},{24,4},{23,4},{22,4},{21,4},{20,4},{19,4},{19,3},{19,2},{19,1},{19,0},
			{20,0},{21,0},{22,0},{23,0},{24,0},{25,0},{26,0},{27,0},{28,0},{29,0},{30,0},{31,0},{32,0},{33,0},{34,0},
			{35,0},{36,0},{37,0},{38,0},{39,0},{40,0},{41,0},{42,0},{43,0},{44,0},{45,0},{46,0},{47,0},{48,0},{49,0},
			{50,0},{51,0},{52,0},{52,1},{52,2},{52,3},{52,4},{52,5},{52,6},{52,7},{52,8},{52,9},{52,10},{52,11},
			{52,12},{52,13},{52,14},{52,15},{52,16},{52,17},{52,18},{52,19},{53,19},{54,19},{54,18},{55,18},{55,17},
			{56,17},{56,16},{57,16},{57,15},{58,15},{58,14},{59,14},{59,13},{60,13},{60,12},{61,12},{61,11},{62,11},
			{62,10},{63,10},{63,9},{63,8},{63,7},{63,6},{63,5},{63,4},{64,4},{64,3},{65,3},{65,2},{66,2},{66,1},{67,1},
			{67,0},{68,0},{69,0},{70,0},{70,1},{70,2},{70,3},{70,4},{70,5},{70,6},{70,7},{70,8},{70,9},{70,10},{70,11},
			{70,12},{70,13},{70,14},{70,15},{70,16},{70,17},{70,18},{70,19},{8,9},{9,9},{9,10},{9,11},{10,11},{10,12},
			{11,12},{11,13},{12,13},{12,14},{13,14},{13,15},{14,15},{14,16},{15,16},{15,17},{16,17},{16,18},{33,3},
			{33,2},{33,1},{38,1},{38,2},{38,3},{38,4},{38,5},{38,6},{38,7},{38,8},{38,9},{38,10},{38,11},{38,12},
			{38,13},{38,14},{38,15},{38,16},{38,17},{38,18},{38,19},{39,19},{40,19},{41,19},{42,19},{43,19},{44,19},
			{45,19},{46,19},{47,19},{48,19},{49,19},{50,19},{51,19},{64,10},{65,10},{66,10},{67,10},{68,10},{69,10}};
		
		//上記の配列をCoordinateクラスのxとyに格納した配列を用意する
		//この配列のインデックスがマスの番号を表す
		Coordinate[] coordinateArray = new Coordinate[coordinateDoubleArray.length];
		for(int i = 0; i < coordinateDoubleArray.length; i++) {
			coordinateArray[i] = new Coordinate(coordinateDoubleArray[i][0], coordinateDoubleArray[i][1]);
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		//ループ変数を座標として対応する記号をstringBuilderに追加して文字列として表す
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 71; j++) {
				if(turnPlayer.getPosition() == notTurnPlayer.getPosition() && j == coordinateArray[turnPlayer.getPosition()].getX() && i == coordinateArray[turnPlayer.getPosition()].getY()) {
					stringBuilder.append("◆");
				}else if(j == coordinateArray[turnPlayer.getPosition()].getX() && i == coordinateArray[turnPlayer.getPosition()].getY()) {
					stringBuilder.append(turnPlayer.getMark());
				}else if(j == coordinateArray[notTurnPlayer.getPosition()].getX() && i == coordinateArray[notTurnPlayer.getPosition()].getY()) {
					stringBuilder.append(notTurnPlayer.getMark());
				}else if(j == 4 && i == 14 || j == 0 && i == 19 || j == 9 && i == 19 || j == 26 && i == 4 || j == 19 && i == 2 || j == 26 && i == 0 || j == 45 && i == 0 || j == 52 && i == 0 || j == 52 && i == 11 || j == 64 && i == 3 || j == 70 && i == 0 || j == 70 && i == 5) {
					stringBuilder.append("＋");
				}else if(j == 12 && i == 13 || j == 33 && i == 2 || j == 38 && i == 19 || j == 67 && i == 10) {
					stringBuilder.append("ー");
				}else if(j == 7 && i == 7 || j == 35 && i == 0 || j == 38 && i == 10 || j == 46 && i == 19 || j == 61 && i == 11) {
					stringBuilder.append("★");
				}else {
					//count変数で処理が重複しないように制御
					int count = 0;
					for(int k = 0; k < coordinateArray.length; k++) {
						if(j == coordinateArray[k].getX() && i == coordinateArray[k].getY()) {
							stringBuilder.append("□");
							count++;
						}
					}
					if(count < 1) {
						stringBuilder.append("　");
					}
				}
			}
			stringBuilder.append("<br>");
		}
		stage = stringBuilder.toString();
		return stage;
	}
}

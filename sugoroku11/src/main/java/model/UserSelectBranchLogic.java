package model;

//分岐地点のマスの番号に対応する文字列を設定する
public class UserSelectBranchLogic {
	public String[] excute(PlayerData turnPlayer) {
		String[] branchStr = new String[2];
		
		switch (turnPlayer.getPosition()) {
			case 28:
				branchStr[0] = "下に進む";
				branchStr[1] = "右に進む";
				break;
			case 93:
				branchStr[0] = "左に進む";
				branchStr[1] = "上に進む";
				break;
			case 130:
				branchStr[0] = "右に進む";
				branchStr[1] = "下に進む";
				break;
			case 183:
				branchStr[0] = "上に進む";
				branchStr[1] = "右に進む";
				break;
		}
		return branchStr;
	}
}

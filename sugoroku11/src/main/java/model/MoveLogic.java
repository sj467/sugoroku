package model;

public class MoveLogic {
	public boolean excute(PlayerData turnPlayer) {
		boolean isBranch = false;
		boolean isGoal =false;
		
		int[] branchPositionArray = new int[] {28, 93, 130, 183}; //分岐地点の番号
		int num = 0; //進むマスの数
		
		//分岐地点から始まる時
		if(turnPlayer.getDiceRemain() > 0) {
			num = turnPlayer.getDiceRemain() - 1;
			turnPlayer.setDiceRemain(0);
		}else {
			num = turnPlayer.getDiceNumber();
		}
		for(int i = 0; i < num; i++) {
			//分岐から戻る処理
			switch(turnPlayer.getPosition()){
				case 237:
					turnPlayer.setPosition(61);
					break;
				case 240:
					turnPlayer.setPosition(125);
					break;
				case 272:
					turnPlayer.setPosition(163);
					break;
				case 278:
					turnPlayer.setPosition(210);
					break;
				default:
					//分岐地点に来た時に残りの進むマスの数をdiceRemainに設定し、処理を中断する
					for(int n: branchPositionArray) {
						if(turnPlayer.getPosition() == n) {
							turnPlayer.setDiceRemain(num - i);
							isBranch = true;
							break;
						}
					}
					if(isBranch) {
						break;
					}
					//プレイヤーの位置を1マス進める
					int position = turnPlayer.getPosition() + 1;
					turnPlayer.setPosition(position);
					
					//ゴール時に来たら処理を中断する
					if(turnPlayer.getPosition() == 219) {
						isGoal = true;
						break;
					}
			}
			if(isGoal) {
				break;
			}
		}
		
		
		return isBranch;
	}
}

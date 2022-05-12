package model;

//numをもとにプレイヤーの分岐先を設定する
public class DecideBranchLogic {
	public void excute(PlayerData turnPlayer, int num) {
		
		switch (turnPlayer.getPosition()) {
			case 28:
				if(num == 0) {
					turnPlayer.setPosition(29);
				}else {
					turnPlayer.setPosition(220);
				}
				break;
			case 93:
				if(num == 0) {
					turnPlayer.setPosition(94);
				}else {
					turnPlayer.setPosition(238);
				}
				break;
			case 130:
				if(num == 0) {
					turnPlayer.setPosition(131);
				}else {
					turnPlayer.setPosition(241);
				}
				break;
			case 183:
				if(num == 0) {
					turnPlayer.setPosition(184);
				}else {
					turnPlayer.setPosition(273);
				}
				break;
		}
	}
}

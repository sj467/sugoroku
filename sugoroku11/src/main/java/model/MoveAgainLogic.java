package model;

//プレイヤーが★に止まった時にmsgを返す
public class MoveAgainLogic {
	
	public String excute(PlayerData turnPlayer) {
		
		String msg = null;
		
		switch (turnPlayer.getPosition()) {
			case 26:
			case 127:
			case 250:
			case 267:
			case 180:
				msg = "★に止まりました。";
				break;
		}
		return msg;
	}
}

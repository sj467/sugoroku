package model;

//名前の入力値をチェックする
public class UserNameCheckLogic {
	
	public boolean excute(String userName) {
		boolean isName = false;
		
		if(userName.matches("[a-zA-Z0-9]{1,8}") && !userName.equals("CPU")) {
			isName = true;
		}
		return isName;
	}
}

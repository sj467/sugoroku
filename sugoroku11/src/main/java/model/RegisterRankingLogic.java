package model;

import dao.RankingLogicDAO;

//プレイヤーの情報をデータベースに登録する
public class RegisterRankingLogic {
	public void excute(PlayerData playerData) {
		RankingLogicDAO rankingLogicDAO = new RankingLogicDAO();
		rankingLogicDAO.registerRanking(playerData);
	}
}

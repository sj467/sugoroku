package model;

import dao.RankingLogicDAO;

public class DeleteRankingLogic {
	public void excute() {
		
		RankingLogicDAO rankingLogicDAO = new RankingLogicDAO();
		rankingLogicDAO.deleteRanking();
	}
}

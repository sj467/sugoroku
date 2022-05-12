package model;

import java.util.List;

import dao.RankingLogicDAO;

//DAOを呼び出し、rankingListを取得する
public class FindRankingLogic {
	public List<Ranking> excute() {
		
		List<Ranking> rankingList = null;
		
		RankingLogicDAO rankingLogicDAO = new RankingLogicDAO();
		rankingList =rankingLogicDAO.findRanking();
		
		return rankingList;
	}
}

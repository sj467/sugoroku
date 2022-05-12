package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PlayerData;
import model.Ranking;

public class RankingLogicDAO {
	
	private final String JDBC_URL = "jdbc:mariadb://localhost:3306/sugoroku";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	
	//データベースにplayerDataの名前とポイントを登録する
	public boolean registerRanking(PlayerData playerData){
		
		boolean result = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql = "INSERT INTO RANKING (NAME, POINT) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playerData.getName());
			pstmt.setInt(2, playerData.getPoint());
			int r = pstmt.executeUpdate();
			
			if(r == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//データベースからポイントの多い順に5件rankingを取得してリストに格納して返す
	public List<Ranking> findRanking() {
		
		List<Ranking> rankingList = new ArrayList<>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql = "SELECT NAME, POINT FROM RANKING ORDER BY POINT DESC LIMIT 5";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Ranking ranking = new Ranking();
				ranking.setName(rs.getString("NAME"));
				ranking.setPoint(rs.getInt("POINT"));
				rankingList.add(ranking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rankingList;
	}
	
	//データベースのポイントの多い順で上位５件以外を削除する
	public void deleteRanking(){
			
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				
				String sql = "DELETE FROM RANKING WHERE ID NOT IN (SELECT * FROM (SELECT ID FROM RANKING ORDER BY POINT DESC LIMIT 5) AS R)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}

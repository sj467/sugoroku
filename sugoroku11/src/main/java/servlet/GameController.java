package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DecideBranchLogic;
import model.DeleteRankingLogic;
import model.MoveAgainLogic;
import model.MoveLogic;
import model.PlayerData;
import model.PointCalcLogic;
import model.RegisterRankingLogic;
import model.ShowStageLogic;
import model.UserSelectBranchLogic;


@WebServlet("/GameController")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションスコープを破棄してトップに戻る
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/sugoroku11/");	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isBranch = false; //分岐の状態
		String[] branchStr = null; //分岐を選択する文字列
		PlayerData turnPlayer = null; //サイコロを振るプレイヤー
		PlayerData notTurnPlayer = null; //待ちのプレイヤー
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String moveAgain = request.getParameter("moveAgain");
		String numStr = request.getParameter("branch");
		
		//もう一度サイコロを振るマスの時、プレイヤーインスタンスを取得してサイコロの値を設定する
		if(moveAgain != null) {
			turnPlayer = (PlayerData) session.getAttribute("turnPlayer");
			notTurnPlayer = (PlayerData) session.getAttribute("notTurnPlayer");
			
			int diceNum = (int)(Math.random() * 6) + 1;
			turnPlayer.setDiceNumber(diceNum);
		//分岐地点のマスの時、プレイヤーインスタンスを取得してユーザーが選んだ分岐を設定する
		}else if(numStr != null) {
			turnPlayer = (PlayerData) session.getAttribute("turnPlayer");
			notTurnPlayer = (PlayerData) session.getAttribute("notTurnPlayer");
			
			int num = Integer.parseInt(numStr);
			DecideBranchLogic decideBunkiLogic = new DecideBranchLogic();
			decideBunkiLogic.excute(turnPlayer, num);
		//通常の時はturnPlayerを入れ替えてプレイヤーインスタンスを取得しサイコロの値を設定する
		}else {
			turnPlayer = (PlayerData) session.getAttribute("notTurnPlayer");
			notTurnPlayer = (PlayerData) session.getAttribute("turnPlayer");
			
			String first = (String) request.getAttribute("first");
			
			//NameCheckServletでリクエストスコープに保存したfirstがnullならば処理をする
			if(first == null) {
				int diceNum = (int)(Math.random() * 6) + 1;
				turnPlayer.setDiceNumber(diceNum);
			}
		}
		//マスを移動する処理
		MoveLogic moveLogic = new MoveLogic();
		isBranch = moveLogic.excute(turnPlayer);
		
		//CPUが分岐地点にいる場合、乱数から分岐を選択しマスを移動する
		if(isBranch && (turnPlayer.getName().equals("CPU"))) {
			int num = (int)(Math.random() * 2);
			DecideBranchLogic decideBunkiLogic = new DecideBranchLogic();
			decideBunkiLogic.excute(turnPlayer, num);
			MoveLogic diceLogic = new MoveLogic();
			isBranch = diceLogic.excute(turnPlayer);
		}
		//マスを表す文字列をリクエストスコープに保存する
		ShowStageLogic showStageLogic = new ShowStageLogic();
		String stage = showStageLogic.excute(turnPlayer, notTurnPlayer);
		request.setAttribute("stage", stage);
		
		//ユーザーが分岐地点にいる場合、分岐地点ごとの文字列をリクエストスコープに保存する
		if(isBranch && (!turnPlayer.getName().equals("CPU"))) {
			UserSelectBranchLogic selectBunkiLogic = new UserSelectBranchLogic();
			branchStr = selectBunkiLogic.excute(turnPlayer);
			request.setAttribute("branchStr", branchStr);
		}
		//もう一度サイコロを振るマスの場合、確認の文字列をリクエストスコープに保存する
		MoveAgainLogic moveAgainLogic = new MoveAgainLogic();
		String msg = moveAgainLogic.excute(turnPlayer);
		request.setAttribute("msg", msg);
		
		//プレイヤーが＋、ーのマスの場合、ポイントを計算してリクエストスコープに保存する
		PointCalcLogic pointCalcLogic = new PointCalcLogic();
		Integer point = Integer.valueOf(pointCalcLogic.excute(turnPlayer));
		request.setAttribute("point", point);
		
		//ゴールした場合、ゴールを知らせる文字列をリクエストスコープに保存し、データベースを更新する
		if(turnPlayer.getPosition() == 219) {
			turnPlayer.setPoint(turnPlayer.getPoint() + 10000);
			String endmsg = turnPlayer.getName() + "がゴールしました。\n" + turnPlayer.getName() + "に10000ポイントが入ります。";
			request.setAttribute("endmsg", endmsg);
			if(!turnPlayer.getName().equals("CPU")) {
				RegisterRankingLogic registerRankingLogic = new RegisterRankingLogic();
				registerRankingLogic.excute(turnPlayer);
				DeleteRankingLogic deleteRankingLogic = new DeleteRankingLogic();
				deleteRankingLogic.excute();
			}
		}
		
		//プレイヤーインスタンスをセッションスコープに保存する
		session.setAttribute("turnPlayer", turnPlayer);
		session.setAttribute("notTurnPlayer", notTurnPlayer);
		
		//turnPlayerに格納されているインスタンスごとにボタンの文字列をリクエストスコープに保存してメイン画面にフォワードする
		if(turnPlayer.getName().equals("CPU")) {
			request.setAttribute("buttonText", "サイコロを振る");
		}else {
			request.setAttribute("buttonText", "進める");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}

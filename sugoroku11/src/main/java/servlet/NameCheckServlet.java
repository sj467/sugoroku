package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PlayerData;
import model.UserNameCheckLogic;

@WebServlet("/NameCheckServlet")
public class NameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//トップ画面で入力された名前を取得してチェックする
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("name");
		
		UserNameCheckLogic userNameCheckLogic = new UserNameCheckLogic();
		boolean isName = userNameCheckLogic.excute(userName);
		
		//名前の値が正常ならばセッションスコープを破棄し、新しく生成
		//ユーザーとCPUのインスタンスを生成、保存し、GameControllerにフォワードする
		//名前の値が異常ならば注意文を保存し、トップに戻る
		if(isName) {
			session.invalidate();
			session = request.getSession();
			
			PlayerData turnPlayer = new PlayerData(userName, 0, 0, 0, "●", 0);
			session.setAttribute("turnPlayer", turnPlayer);
			PlayerData notTurnPlayer = new PlayerData("CPU", 0, 0, 0, "▲", 0);
			session.setAttribute("notTurnPlayer", notTurnPlayer);
			
			request.setAttribute("first", "");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/GameController");
			dispatcher.forward(request, response);
			
		}else {
			String errorMsg = "ユーザー名が正しく入力されていません。";
			
			if(userName.equals("CPU")) {
				errorMsg = "その名前は使用できません。";
			}
			
			session.setAttribute("errorMsg", errorMsg);
			
			response.sendRedirect("/sugoroku11/");
		}
	}

}

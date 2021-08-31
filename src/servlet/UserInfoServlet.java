package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dba.CreateSQL;
import dba.OracleDBAccess;

public class UserInfoServlet extends HttpServlet {

	CreateSQL cre = new CreateSQL();

	OracleDBAccess odba = new OracleDBAccess();

	UserBean bean = new UserBean();

	private String _username;
	private String _userpass;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");

		//POST要求によって送信されたパラメータを取得する
		_username =req.getParameter("name");
		_userpass = req.getParameter("pass");

		String sql = cre.inserUserInfo(_username, _userpass);

		odba.insertDB(sql);

		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//SELECT分の実行
		String sql = cre.selectUserInfo(_username, _userpass);

		odba.selectUserInfo(sql);

		bean = odba.getUserBean();

		req.setAttribute("user", bean);

		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("userinfo");

		//転送先に要求を転送する
		dispatcher.forward(req,res);
	}
}
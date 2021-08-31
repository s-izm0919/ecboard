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
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");

		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		_username =req.getParameter("name");
		_userpass = req.getParameter("pass");

		String sql = cre.inserUserInfo(_username, _userpass);

		odba.insertDB(sql);

		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//SELECT���̎��s
		String sql = cre.selectUserInfo(_username, _userpass);

		odba.selectUserInfo(sql);

		bean = odba.getUserBean();

		req.setAttribute("user", bean);

		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("userinfo");

		//�]����ɗv����]������
		dispatcher.forward(req,res);
	}
}
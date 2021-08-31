package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ThreadBean;
import dba.CreateSQL;
import dba.OracleDBAccess;

public class AddThreadServlet extends HttpServlet {

	//ArrayList���g�p
	private ArrayList<ThreadBean> database = new ArrayList<ThreadBean>();

	private String check=""; //���d���e�֎~�p

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");

		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String checknumber = req.getParameter("checknumber");
		String name=req.getParameter("name");
		//name�����M����Ă��Ȃ��ꍇ�́A�����I��NONAME�ƂȂ�
		if(name==""){
			name=new String("NONAME");
		}
		String _content=req.getParameter("content");
		//���s�L����HTML�p�̉��s�L���ɕϊ�����B
		String content=_content.replaceAll("\n", "<br>");

		String title=req.getParameter("title");
		String question= req.getParameter("question");
		String choice1 = req.getParameter("choice1");
		String choice2 = req.getParameter("choice2");

		//���d���e�֎~�p�̃`�F�b�N�v���O����

		if(check.equals(checknumber)){
		}
		else{
		CreateSQL cre = new CreateSQL();
		String insert_sql = cre.insertThread(name, title, content, question, choice1, choice2);
		OracleDBAccess odba = new OracleDBAccess();
		odba.insertDB(insert_sql);
		check=checknumber;
		}

		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//SELECT���̎��s

		CreateSQL cre = new CreateSQL();

		OracleDBAccess odba = new OracleDBAccess();

		String sort = req.getParameter("sort");

		if(sort == null){
			sort = "1";
		}

		String select_sql = cre.selectAll(sort);

		odba.selectDB(select_sql);
		database = odba.getDatabase();

		//����������y�[�W���쐬����v���O����

		int showpages = 10; //��x�ɕ\�����������y�[�W���͂����ɐݒ肷��B

		int pages = database.size()/showpages;
		if(database.size() %showpages !=0){
			pages +=1;
		}

		int allpages[] = new int[pages];
		for(int i=0;i<pages;i++){
			allpages[i] = i+1;
		}

		ArrayList<ThreadBean> array = new ArrayList<ThreadBean>();

		int page;
		String _page = req.getParameter("page");
		if(_page == null){
			page = 1;
		}else{
			page = Integer.parseInt(_page);
		}

		int time = showpages*(page-1);

		for(int i=0;i<database.size();i++){
			if(i>=time){
				ThreadBean bean = database.get(i);
				array.add(bean);
			}

			if(array.size()==showpages){
				break;
			}
		}

		//���y�[�W�̃v���O�����͂����܂�

		req.setAttribute("data",array);
		req.setAttribute("page",allpages);

		req.setAttribute("sort",sort);

		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("threadlist");

		//�]����ɗv����]������
		dispatcher.forward(req,res);
	}
}

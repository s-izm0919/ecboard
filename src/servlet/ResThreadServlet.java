package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ResThreadBean;
import bean.ThreadBean;
import dba.CreateSQL;
import dba.OracleDBAccess;

public class ResThreadServlet extends HttpServlet {

	//ArrayList���g�p
	private ArrayList<ThreadBean> database = new ArrayList<ThreadBean>();
	private ArrayList<ResThreadBean> resdatabase = new ArrayList<ResThreadBean>();

	private ArrayList<Integer> votingdata = new ArrayList<Integer>();


	String id = null;	//th_id�̏����l��null

	private String check="";	//���d���e�֎~�p�̃v���p�e�B

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");

		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String checknumber = req.getParameter("checknumber");

		id = req.getParameter("reid");
		String name = req.getParameter("rename");

		//name�����M����Ă��Ȃ��ꍇ�́A�����I��NONAME�ƂȂ�
		if(name==""){
			name=new String("NONAME");
		}

		String _content=req.getParameter("recontent");
		//���s�L����html�p�̉��s�L���ɕϊ�����B
		String content=_content.replaceAll("\n", "<br>");

		//�󕶂̏ꍇ�́A�����I�ɓ��e���͂���܂���ƂȂ�B

		if(content==""){
			content=new String("���e���͂���܂���B");
		}
		String voting = req.getParameter("revoting");

		//���d���e�֎~�p�̃`�F�b�N�v���O����
		if(check.equals(checknumber)){}else{

			CreateSQL cre = new CreateSQL();
			String insert_sql = cre.insertResThread(id, name, content, voting);
			OracleDBAccess odba = new OracleDBAccess();
			odba.insertDB(insert_sql);
			check=checknumber;}

		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Windows-31J");

        if(id == null){
			id = req.getParameter("id");
		}

		//SELECT���̎��s
		CreateSQL cre = new CreateSQL();
		String select_sql = cre.selectOne(id);
		String resselect_sql = cre.selectResAll(id);

		OracleDBAccess odba = new OracleDBAccess();
		odba.selectDB(select_sql);
		ThreadBean bean = odba.getThreadBean();

		odba.selectResDB(resselect_sql);
		resdatabase = odba.getResDatabase();

		votingdata = odba.getVotingData();

		//���[�����W�v����v���O����

		Stream<Integer> stream1 = votingdata.stream();
		Stream<Integer> stream2 = votingdata.stream();

		long agreement = stream1.filter(number -> number == 1).count();
		long disagreement = stream2.filter(number -> number == 2).count();

		//���y�[�W���쐬����v���O����
		int pages = resdatabase.size()/10;
		if(resdatabase.size() %10 !=0){
			pages +=1;
		}

		int allpages[] = new int[pages];
		for(int i=0;i<pages;i++){
			allpages[i] = i+1;
		}

		ArrayList<ResThreadBean> array = new ArrayList<ResThreadBean>();

		int page;
		String _page = req.getParameter("page");
		if(_page == null){
			page = 1;
		}else{
			page = Integer.parseInt(_page);
		}

		int time = 10*(page-1);

		for(int i=0;i<resdatabase.size();i++){
			if(i>=time){
				ResThreadBean resbean = resdatabase.get(i);
				array.add(resbean);
			}

			if(array.size()==10){
				break;
			}
		}
		//���y�[�W�v���O�����͂����܂�

		req.setAttribute("agreement", agreement);
		req.setAttribute("disagreement", disagreement);

		req.setAttribute("data",bean);
		req.setAttribute("resdata",array);
		req.setAttribute("id",id);
		req.setAttribute("page",allpages);

		//id������������B�������Ȃ��ƕ������e���󂯕t���Ȃ��Ȃ�B
		id = null;

		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("resthreadlist");

		//�]����ɗv����]������
		dispatcher.forward(req,res);
	}
}
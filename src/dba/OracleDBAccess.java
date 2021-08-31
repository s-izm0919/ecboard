package dba;

//Oracle�f�[�^�x�[�X�𑀍삷�邽�߂̌ŗL�N���X���Ăяo���B
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ResThreadBean;
import bean.ThreadBean;
import bean.UserBean;

public class OracleDBAccess{
	//ArrayList�̐����B������Select������Ԃ��Ă���f�[�^���i�[���Ă���B
	private ArrayList<ThreadBean> oracle = new ArrayList<ThreadBean>();
	private ArrayList<ResThreadBean> resoracle = new ArrayList<ResThreadBean>();
	private ArrayList<Integer> votingdata = new ArrayList<Integer>();

	private ThreadBean thbean = new ThreadBean();
	
	private UserBean userbean = new UserBean();

	public ArrayList<ThreadBean> getDatabase(){
		return oracle;
	}

	public ArrayList<ResThreadBean> getResDatabase(){
		return resoracle;
	}

	public ThreadBean getThreadBean(){
		return thbean;
	}

	public ArrayList<Integer> getVotingData(){
		return votingdata;
	}
	
	public UserBean getUserBean() {
		return userbean;
	}


	//Oracle�f�[�^�x�[�X��Select�������s����
	public void selectDB(String sql){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"panda","passpanda");
			
			//select��
			String _sql= sql;

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(_sql);

			int number=0;

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				//UserBean�𐶐����A�����Ƀf�[�^������Ă���
                ThreadBean bean = new ThreadBean();
				String id=rs.getString(1);	//1��ڂ̃f�[�^���擾
                String name=rs.getString(2);	//2��ڂ̃f�[�^���擾
                String content=rs.getString(3);	//3��ڂ̃f�[�^���擾
				String title=rs.getString(4);	//4��ڂ̃f�[�^���擾
				String time=rs.getString(5);	//5��ڂ̃f�[�^���擾
				String question=rs.getString(6);	//6��ڂ̃f�[�^���擾
				String choice1=rs.getString(7);	//7��ڂ̃f�[�^���擾
				String choice2=rs.getString(8);	//8��ڂ̃f�[�^���擾

				//UserBean��setter�𗘗p
				bean.setId(id);
                bean.setName(name);
				bean.setContent(content);
				bean.setTitle(title);
				bean.setTime(time);
				bean.setQuestion(question);
				bean.setChoice1(choice1);
				bean.setChoice2(choice2);
				//List��USerBean�^�̃f�[�^���i�[����

				
				bean.setNumber(++number);

				oracle.add(bean);


				if(oracle.size() < 2){
					thbean = bean;
				}
				if(oracle.size() >= 200){
					break;
				}

				//���̏��������[�v�����邱�ƂŁAOracle����f�[�^����s������UserBean�Ƀf�[�^�����遨1�s�X�N���[�����Ă܂�����������������J��Ԃ��B
			}
			
			//Oracle����ؒf����
			cn.close();

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�N���X���Ȃ��݂����B");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void selectResDB(String sql){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"panda","passpanda");
			
			//select��
			String _sql= sql;

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(_sql);

			int number=0;

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				//UserBean�𐶐����A�����Ƀf�[�^������Ă���
                ResThreadBean bean = new ResThreadBean();
				String re_id=rs.getString(2);	//2��ڂ̃f�[�^���擾
                String name=rs.getString(3);	//3��ڂ̃f�[�^���擾
                String content=rs.getString(4);	//4��ڂ̃f�[�^���擾
				String time=rs.getString(5);	//5��ڂ̃f�[�^���擾
				String voting=rs.getString(6);	//6��ڂ̃f�[�^���擾
				//UserBean��setter�𗘗p
				bean.setRe_id(re_id);
                bean.setName(name);
				bean.setContent(content);
				bean.setTime(time);
				bean.setVoting(voting);
				//List��USerBean�^�̃f�[�^���i�[����
				
				bean.setNumber(++number);

				resoracle.add(bean);

				votingdata.add(rs.getInt(6));

				if(resoracle.size() >= 200){
					break;
				}

				//���̏��������[�v�����邱�ƂŁAOracle����f�[�^����s������UserBean�Ƀf�[�^�����遨1�s�X�N���[�����Ă܂�����������������J��Ԃ��B
			}
			//Oracle����ؒf����
			cn.close();

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�N���X���Ȃ��݂����B");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void selectUserInfo(String sql){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracle�ɐڑ�����
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"panda","passpanda");
			
			//select��
			String _sql= sql;

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
			//�C���X�^���X���Ԃ�
			ResultSet rs=st.executeQuery(_sql);

			while(rs.next()) {
			
			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			String id=rs.getString(1);	//1��ڂ̃f�[�^���擾
            String name=rs.getString(2);	//2��ڂ̃f�[�^���擾
            String pass=rs.getString(3);	//3��ڂ̃f�[�^���擾

				//UserBean��setter�𗘗p
			userbean.setUserid(id);
            userbean.setUsername(name);
			userbean.setUserpass(pass);
			
			}
			
			//Oracle����ؒf����
			cn.close();

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�N���X���Ȃ��݂����B");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertDB(String sql){
        try{
			//Driver�C���^�[�t�F�C�X����������N���X�����[�h����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Connection�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X��Ԃ�
			Connection cn=
				DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl",
					"panda","passpanda");
			
			//�����R�~�b�g��OFF�ɂ���
			cn.setAutoCommit(false);
			
			//SQL����ϐ��Ɋi�[����
			String _sql=sql;
			
			//Statement�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			Statement st=cn.createStatement();
			
			//SQL�����s���g�����U�N�V�������J�n�����B�����������Ԃ����
			int count=st.executeUpdate(_sql);
			
			//�g�����U�N�V�������R�~�b�g����
			cn.commit();
			
			//�X�e�[�g�����g���N���[�Y����
			st.close();
			
			//RDBMS����ؒf����
			cn.close();

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

    }
}



package bean;

import java.io.Serializable;

public class UserBean implements Serializable{
	private String userid;
	private String username;
	private String userpass;
	
	public UserBean() {}
	
	public void setUserid(String id) {
		userid = id;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUsername(String name) {
		username = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUserpass(String pass) {
		userpass = pass;
	}
	
	public String getUserpass() {
		return userpass;
	}
}
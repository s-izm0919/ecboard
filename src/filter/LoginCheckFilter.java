package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dba.CreateSQL;
import dba.OracleDBAccess;

public class LoginCheckFilter implements Filter{
	public void init(FilterConfig config) throws ServletException{
		
	}
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		String name = req.getParameter("name");
		
		String pass = req.getParameter("pass");
		
		if(name != null && pass != null) {
			UserBean bean = new UserBean();
			
			CreateSQL cre = new CreateSQL();
			
			OracleDBAccess dba =  new OracleDBAccess();
			
			String sql = cre.selectUserInfo(name, pass);
			
			dba.selectUserInfo(sql);
			
			bean = dba.getUserBean();
			
			if(name.equals(bean.getUsername()) && pass.equals(bean.getUserpass())) {
				HttpSession session = ((HttpServletRequest)req).getSession();
				
				session.setAttribute("token", "OK");
				session.setAttribute("userinfo", bean);
				
			}else {
				req.setAttribute("nouser", "このユーザは存在しないか、ユーザー名またはパスワードが違います。");
			}
			
		}
		
		chain.doFilter(req, res);
	}
}

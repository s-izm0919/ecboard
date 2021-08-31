package filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionResetServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		
		session.invalidate();
		
		RequestDispatcher dis = req.getRequestDispatcher("index");
		
		dis.forward(req, res);
		
		}
				
}
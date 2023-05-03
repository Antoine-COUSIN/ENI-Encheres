package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UserBLL;
import bll.UserBLLException;
import bo.User;
import util.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
	
	@Override
	public void init() throws ServletException {
		userBLL = new UserBLL();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pLogin = request.getParameter("login");
		//String pPassword = request.getParameter("password");
		String pPassword = null;
		try {
			pPassword = request.getParameter("password");
			if ((!pPassword.isEmpty()) || (!pPassword.equals(""))) {
				pPassword = PasswordUtil.hashPassword(request.getParameter("password"));
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		User loggedUser;
		try {
			loggedUser = userBLL.login(pLogin, pPassword);
			if (loggedUser != null) {
				System.out.println("connected with " + pLogin);
				request.setAttribute("loggedUser", loggedUser);
				request.setAttribute("isConnected", true);
				request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
			} else {
				System.out.println("user does not exist");
				request.setAttribute("logError", true);
				request.setAttribute("isConnected", false);
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			}
			
		} catch (UserBLLException e) {
			System.out.println("user does not exist");
			request.setAttribute("errors", e.getErrors());
			request.setAttribute("isConnected", false);
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
		
	}

}

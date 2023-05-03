package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UserBLL;
import bll.UserBLLException;
import bo.User;

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
		String pPassword = request.getParameter("password");
		
		
		User loggedUser;
		try {
			loggedUser = userBLL.login(pLogin, pPassword);
			System.out.println("connected with " + pLogin);
			request.setAttribute("loggedUser", loggedUser);
			request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
		} catch (UserBLLException e) {
			System.out.println("user does not exist");
			request.setAttribute("errors", e.getErrors());
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
		
	}

}

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UserBLL;
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
		
		System.out.println("info from loginServlet -> " + pLogin + " " + pPassword);
		
		User loggedUser = new User();
		loggedUser = userBLL.login(pLogin, pPassword);
		
		if (loggedUser != null) {
			System.out.println("connected with " + pLogin);
			request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
		} else {
			System.out.println("user does not exist");
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
		
	}

}

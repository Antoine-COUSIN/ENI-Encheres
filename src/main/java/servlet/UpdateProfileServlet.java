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
import util.C_CheckEveryField;
import util.PasswordUtil;


@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
	
	
	@Override
	public void init() throws ServletException {
		userBLL = new UserBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("previousPage", "update-user");
		request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			user = C_CheckEveryField.gf_CheckEveryField(request);
		} catch (UserBLLException e) {
			request.setAttribute("errors", e.getErrors());
		}
		
		String pPassword = null;
		try {
			pPassword = PasswordUtil.hashPassword(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String pConfirmPass = null;
		try {
			pConfirmPass = PasswordUtil.hashPassword(request.getParameter("confirmPassword"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		if (pPassword.equals(pConfirmPass)) {
			//create-user
			try {
				user.setPassword(pPassword);
				userBLL.updateUser(user);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("WEB-INF/jsp/show-profile.jsp").forward(request, response);
			} catch (UserBLLException e) {
				request.setAttribute("errors", e.getErrors());
				request.setAttribute("previousPage", "update-profile");
				request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
			}
			
		} else {
			//alert different password
			request.setAttribute("previousPage", "update-profile");
			request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
		}
		
	}
	
	

}

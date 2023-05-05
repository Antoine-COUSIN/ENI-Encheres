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

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
       
	@Override
	public void init() throws ServletException {
		userBLL = new UserBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().getAttribute("partialLoggedUser");
		
		request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			user = checkEveryField(request);
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
				userBLL.createUser(user);
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("isConnected", true);
				request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
			} catch (UserBLLException e) {
				request.setAttribute("errors", e.getErrors());
				request.getSession().setAttribute("isConnected", false);
				request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
			}
			
		} else {
			//alert different password
			request.setAttribute("logError", true);
			request.getSession().setAttribute("isConnected", false);
			request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
		}
		
	}
	
	public User checkEveryField(HttpServletRequest request) throws UserBLLException{
		UserBLLException e = new UserBLLException();
		User tempUser = new User();
		
		if (request.getParameter("pseudo") == null || request.getParameter("pseudo").isEmpty()) {
			e.addError(101);
		} else if (!request.getParameter("pseudo").matches("^[a-zA-Z0-9]*$")) {
			e.addError(102);
		} else {
			tempUser.setPseudo(request.getParameter("pseudo"));
		}
		
		
		if (request.getParameter("firstName") == null || request.getParameter("firstName").isEmpty()) {
			e.addError(3);
		} else {
			tempUser.setFirstName(request.getParameter("firstName"));
		}
		
		if (request.getParameter("phone") == null || request.getParameter("phone").isEmpty()) {
			e.addError(4);
		} else {
			tempUser.setPhoneNumber(request.getParameter("phone"));
		}
		
		if (request.getParameter("postalCode") == null || request.getParameter("postalCode").isEmpty()) {
			e.addError(5);
		} else {
			tempUser.setPostalCodeAddress(request.getParameter("postalCode"));
		}
		
		if (request.getParameter("lastName") == null || request.getParameter("lastName").isEmpty()) {
			e.addError(6);
		} else {
			tempUser.setLastName(request.getParameter("lastName"));
		}
		
		if (request.getParameter("email") == null || request.getParameter("email").isEmpty()) {
			e.addError(7);
		} else {
			tempUser.setEmail(request.getParameter("email"));
		}
		
		if (request.getParameter("address") == null || request.getParameter("address").isEmpty()) {
			e.addError(8);
		} else {
			tempUser.setStreetAddress(request.getParameter("address"));
		}
		
		if (request.getParameter("city") == null || request.getParameter("city").isEmpty()) {
			e.addError(9);
		} else {
			tempUser.setCityAddress(request.getParameter("city"));
		}
		
		request.setAttribute("partialLoggedUser", tempUser);
		
		return tempUser;
	}

}

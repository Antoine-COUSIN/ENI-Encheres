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

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
       
	@Override
	public void init() throws ServletException {
		userBLL = new UserBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Form left side
		String pPseudo = request.getParameter("pseudo");
		String pFirstName = request.getParameter("firstName");
		String pPhone = request.getParameter("phone");
		String pPostalCode = request.getParameter("postalCode");
		String pPassword = request.getParameter("password");
		
		//Form right side
		String pLastName = request.getParameter("lastName");
		String pEmail = request.getParameter("email");
		String pAddress = request.getParameter("address");
		String pCity = request.getParameter("city");
		String pConfirmPass = request.getParameter("confirmPassword");
		
		if (pPassword.equals(pConfirmPass)) {
			User user = new User();
			user.setPseudo(pPseudo);
			user.setFirstName(pFirstName);
			user.setPhoneNumber(pPhone);
			user.setPostalCodeAddress(pPostalCode);
			user.setPassword(pPassword);
			user.setLastName(pLastName);
			user.setEmail(pEmail);
			user.setStreetAddress(pAddress);
			user.setCityAddress(pCity);
			
			//create-user
			try {
				userBLL.createUser(user);
				request.setAttribute("user", user);
				request.setAttribute("isConnected", true);
				request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
			} catch (UserBLLException e) {
				request.setAttribute("errors", e.getErrors());
				request.setAttribute("isConnected", false);
				request.getRequestDispatcher("WEB-INF/jsp/create-user.jsp").forward(request, response);
			}
			
		} else {
			//alert different password
		}
		
	}

}

package util;

import javax.servlet.http.HttpServletRequest;

import bll.UserBLLException;
import bo.User;

public class C_CheckEveryField {

	public static User gf_CheckEveryField(HttpServletRequest request) throws UserBLLException {
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

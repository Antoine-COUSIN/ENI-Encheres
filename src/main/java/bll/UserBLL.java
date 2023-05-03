package bll;

import bo.User;
import dal.DAOFactory;
import dal.UserDAO;

/*
 * codes erreurs : 1 -> login null 
 * 				   2 -> password null
 */

public class UserBLL {
	
	private UserDAO userDAO;
	
	public UserBLL() {
		userDAO = DAOFactory.getUserDAO();
	}
	
	public User login(String login, String password) throws UserBLLException {
		checkAuth(login, password);
		
		User user = userDAO.login(login, password);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}
	
	public void checkAuth(String login, String password) throws UserBLLException {
		UserBLLException e = new UserBLLException();
		
		if (login == null || login.isEmpty()) {
			e.addError(1);
		} 
		if (password == null || password.isEmpty()) {
			e.addError(2);
		}
		
		if (e.getErrors().size() > 0) {
			throw e;
		}
	}
	
}

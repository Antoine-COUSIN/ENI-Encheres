package bll;

import bo.User;
import dal.DAOFactory;
import dal.UserDAO;

public class UserBLL {

	private UserDAO userDAO;
	
	public UserBLL() {
		userDAO = DAOFactory.getUserDAO();
	}
	
	public User login(String login, String password) {
		User user = new User();
		user = userDAO.login(login, password);
		
		if (user != null) {
			return user;
		} else {
			return null;
		}
		
	}
	
}

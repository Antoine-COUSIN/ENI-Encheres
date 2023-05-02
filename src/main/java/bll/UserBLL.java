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
		return userDAO.login(login, password);
	}
	
}

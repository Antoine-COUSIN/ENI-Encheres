package bll;

import bo.User;
import dal.DAOFactory;
import dal.UserDAO;

/*
 * codes erreurs : 1 -> login null 
 * 				   2 -> password null
 * 				   3 -> firstName null
 *                 4 -> phone null
 *                 5 -> postalCode null
 *                 6 -> lastName null
 *                 7 -> email null
 *                 8 -> address null
 *                 9 -> city null
 *                 
 *                 10 -> existing pseudo
 *                 11 -> existing email
 *                 20 -> error during creation
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
	
	public void createUser(User user) throws UserBLLException {
		UserBLLException e = new UserBLLException();
		checkValues(user);
		verifyExistingValues(user);
		if (!userDAO.createUser(user)) {
			e.addError(20);
		}
	}
	
	public void updateUser(User user) throws UserBLLException {
		checkValues(user);
		userDAO.updateUser(user);
		
	}
	
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
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
	
	
	
	public void checkValues(User user) throws UserBLLException {
		UserBLLException e = new UserBLLException();
		if (user.getPseudo() == null || user.getPseudo().isEmpty()) {
			e.addError(1);
		}
		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
			e.addError(3);
		}
		if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
			e.addError(4);
		}
		if (user.getPostalCodeAddress() == null || user.getPostalCodeAddress().isEmpty()) {
			e.addError(5);
		}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			e.addError(2);
		}
		if (user.getLastName() == null || user.getLastName().isEmpty()) {
			e.addError(6);
		}
		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			e.addError(7);
		}
		if (user.getStreetAddress() == null || user.getStreetAddress().isEmpty()) {
			e.addError(8);
		}
		if (user.getCityAddress() == null || user.getCityAddress().isEmpty()) {
			e.addError(9);
		}
		
		
		if (e.getErrors().size() > 0) {
			throw e;
		}
	}
	
	public void verifyExistingValues(User user) throws UserBLLException {
		UserBLLException e = new UserBLLException();
		if (!userDAO.checkPseudo(user.getPseudo())) {
			e.addError(10);
		}
		
		if (!userDAO.checkEmail(user.getEmail())) {
			e.addError(11);
		}
		
		if (e.getErrors().size() > 0) {
			throw e;
		}
	}
	
}

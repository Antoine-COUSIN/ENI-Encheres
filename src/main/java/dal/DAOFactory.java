package dal;

public abstract class DAOFactory {

	private static UserDAO userDAO;
	
	public static UserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOJdbcImpl();
		}
		return userDAO;
	}
	
}

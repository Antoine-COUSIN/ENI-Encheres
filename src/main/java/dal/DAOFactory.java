package dal;

public abstract class DAOFactory {

	private static UserDAO userDAO;
	private static ArticleDAO articleDAO;
	
	public static UserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOJdbcImpl();
		}
		return userDAO;
	}
	
	public static ArticleDAO getArticleDAO() {
		if (articleDAO == null) {
			articleDAO = new ArticleDAOJdbcImpl();
		}
		return articleDAO;
	}
	
}

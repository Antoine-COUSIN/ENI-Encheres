package bll;

import java.util.ArrayList;
import java.util.List;

import bo.Category;
import dal.ArticleDAO;
import dal.DAOFactory;
import dal.UserDAO;

public class ArticleBLL {
	
private ArticleDAO articleDAO;
	
	public ArticleBLL() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<Category> listCategories() {
		List<Category> result = new ArrayList<>();
		return result = articleDAO.listAllCategories();
	}
	
}

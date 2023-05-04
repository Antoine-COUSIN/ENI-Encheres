package bll;

import java.util.List;

import bo.Category;
import bo.Item;
import dal.ArticleDAO;
import dal.DAOFactory;

public class ArticleBLL {
	
private ArticleDAO articleDAO;
	
	public ArticleBLL() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<Category> listCategories() {
		return articleDAO.listAllCategories();
	}
	
	public List<Item> listArticles() {
		return articleDAO.listAllItems();
	}
	
}

package bll;

import java.util.List;

import bo.Category;
import bo.Item;
import bo.PickupPoint;
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
	
	public void createItem(Item item, PickupPoint pickupPoint) {
		articleDAO.insertItem(item, pickupPoint);
	}
	
	public List<Item> filteredSearch(int no_category, String name) {
		return articleDAO.filteredSearch(no_category, name);
	}
	
	public List<Item> completeFilteredSearchPurchases(int no_category, String name, String sell_status ,int no_user) {
		return articleDAO.completeFilteredSearchPurchases(no_category, name, sell_status, no_user);
	}
	
}

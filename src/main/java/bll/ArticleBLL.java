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
	
	public List<Item> completeFilteredSearchPurchases(int no_category, String name, String sellStatus1, String sellStatus2, String sellStatus3 ,int no_user, String option) {
		return articleDAO.completeFilteredSearchPurchases(no_category, name, sellStatus1, sellStatus2, sellStatus3, no_user, option);
	}
	
	public Item selectOneItem(int no_article) {
		return articleDAO.selectOneItem(no_article);
	}

	public PickupPoint getSelectedItemPickupPoint(int no_article) {
		return articleDAO.getSelectedItemPickupPoint(no_article);
	}
	
}

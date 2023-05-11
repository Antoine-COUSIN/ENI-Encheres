package dal;

import java.util.List;

import bo.Category;
import bo.Item;
import bo.PickupPoint;

public interface ArticleDAO {
	
	public List<Category> listAllCategories();
	public List<Item> listAllItems();
	public void insertItem(Item item, PickupPoint pickupPoint);
	public List<Item> filteredSearch(int no_category, String name);
	
	public List<Item> completeFilteredSearchPurchases(int no_category, String name, String sellStatus1, String sellStatus2, String sellStatus3, int no_user, String option);
	public List<Item> completeFilteredSearchSales(int no_category, String name, String sell_status, int no_user);
}

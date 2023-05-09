package dal;

import java.util.List;

import bo.Category;
import bo.Item;
import bo.PickupPoint;

public interface ArticleDAO {
	
	public List<Category> listAllCategories();
	public List<Item> listAllItems();
	public void insertItem(Item item, PickupPoint pickupPoint);
}

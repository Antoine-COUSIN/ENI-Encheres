package dal;

import java.util.List;

import bo.Category;
import bo.Item;

public interface ArticleDAO {
	
	public List<Category> listAllCategories();
	
	public List<Item> listAllItems();
}

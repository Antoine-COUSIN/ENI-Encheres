package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Category;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGOIES";
	
	
	@Override
	public List<Category> listAllCategories() {
		List<Category> result = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
			
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				Category newCat = new Category();
				newCat.setNo_categorie(rs.getInt("no_categorie"));
				newCat.setLibelle(rs.getString("libelle"));
				
				result.add(newCat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}

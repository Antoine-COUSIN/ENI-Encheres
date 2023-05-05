package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.Category;
import bo.Item;
import bo.User;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente," + 
													  "A.no_utilisateur, UTILISATEURS.pseudo AS USER_PSEUDO, A.no_categorie, CATEGORIES.libelle AS CAT_LIB," +
													  "etat_vente, image FROM ARTICLES_VENDUS A " + 
													  "JOIN CATEGORIES ON CATEGORIES.no_categorie = A.no_categorie " + 
													  "JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = A.no_utilisateur ";
	
	
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


	@Override
	public List<Item> listAllItems() {
		List<Item> result = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_ARTICLES, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Item newItem = new Item();
				newItem.setNo_article(rs.getInt("no_article"));
				newItem.setName_article(rs.getString("nom_article"));
				newItem.setDescr_article(rs.getString("description"));
				
				LocalDateTime localDateTime = rs.getTimestamp("date_debut_enchere").toLocalDateTime();
				newItem.setStart_auction(localDateTime);
				
				LocalDateTime localDateTime2 = rs.getTimestamp("date_fin_enchere").toLocalDateTime();
				newItem.setEnd_auction(localDateTime2);
				
				newItem.setInitial_price(rs.getInt("prix_initial"));
				newItem.setSell_price(rs.getInt("prix_vente"));
				
				User user = new User();
				user.setNo_user(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("USER_PSEUDO"));
				newItem.setUser(user);
				
				Category cat = new Category();
				cat.setNo_categorie(rs.getInt("no_categorie"));
				cat.setLibelle(rs.getString("CAT_LIB"));
				newItem.setCategory(cat);
				
				newItem.setSell_status(rs.getString("etat_vente"));
				newItem.setImage_article(rs.getString("image"));
				
				
				result.add(newItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}

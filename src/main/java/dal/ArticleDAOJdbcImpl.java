package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.Category;
import bo.Item;
import bo.PickupPoint;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente," + 
													  "A.no_utilisateur, UTILISATEURS.pseudo AS USER_PSEUDO, A.no_categorie, CATEGORIES.libelle AS CAT_LIB," +
													  "etat_vente, image FROM ARTICLES_VENDUS A " + 
													  "JOIN CATEGORIES ON CATEGORIES.no_categorie = A.no_categorie " + 
													  "JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = A.no_utilisateur ";
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CREATE_PICKUP_POINT = "INSERT INTO RETRAITS VALUES (?, ?, ?, ?)";
	
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
			
			PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			
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
				newItem.setNo_user(rs.getInt("no_utilisateur"));
				newItem.setUser_Pseudo(rs.getString("USER_PSEUDO"));
				newItem.setNo_category(rs.getInt("no_categorie"));
				newItem.setCategory_Lib(rs.getString("CAT_LIB"));
				newItem.setSell_status(rs.getString("etat_vente"));
				newItem.setImage_article(rs.getString("image"));
				
				
				result.add(newItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public void insertItem(Item item, PickupPoint pickupPoint) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			
			PreparedStatement ps = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, item.getName_article());
			ps.setString(2, item.getDescr_article());
			ps.setTimestamp(3, Timestamp.valueOf(item.getStart_auction()));
			ps.setTimestamp(4, Timestamp.valueOf(item.getEnd_auction()));
			ps.setInt(5, item.getInitial_price());
			ps.setNull(6, java.sql.Types.INTEGER);
			ps.setInt(7, item.getNo_user());
			ps.setInt(8, item.getNo_category());
			ps.setString(9, item.getSell_status());
			ps.setString(10, item.getImage_article());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			PreparedStatement ps2_pickUp = cnx.prepareStatement(CREATE_PICKUP_POINT);
			int id = rs.getInt(1);
			pickupPoint.setNo_item(id);
			ps2_pickUp.setInt(1, pickupPoint.getNo_item());
			ps2_pickUp.setString(2, pickupPoint.getStreetAddress());
			ps2_pickUp.setString(3, pickupPoint.getPostalCode());
			ps2_pickUp.setString(4, pickupPoint.getCityAddress());
			
			ps2_pickUp.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}

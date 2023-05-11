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
	private static final String SIMPLE_FILTERED_SEARCH = "SELECT no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente," + 
														 "A.no_utilisateur, UTILISATEURS.pseudo AS USER_PSEUDO, A.no_categorie, CATEGORIES.libelle AS CAT_LIB," +
														 "etat_vente, image FROM ARTICLES_VENDUS A " + 
														 "JOIN CATEGORIES ON CATEGORIES.no_categorie = A.no_categorie " + 
														 "JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = A.no_utilisateur " +
														 "WHERE (A.no_categorie = ? OR ? IS NULL)" + 
														 "AND (nom_article LIKE ? OR ? IS NULL)";
	
	private static final String COMPLETE_FILTERED_SEARCH_PURCHASES = "SELECT A.no_article, A.nom_article, A.description, A.date_debut_enchere, A.date_fin_enchere, "
			+ " A.prix_initial, A.prix_vente,"
			+ " A.no_utilisateur, U.pseudo AS VENDEUR, A.no_categorie, C.libelle AS CAT_LIB, A.etat_vente, A.image, E.no_utilisateur,"
			+ " UT.no_utilisateur, UT.pseudo AS ACHETEUR"
			+ " FROM ARTICLES_VENDUS A"
			+ " LEFT JOIN ENCHERES E ON E.no_article = A.no_article"
			+ " LEFT JOIN UTILISATEURS U ON U.no_utilisateur = A.no_utilisateur"
			+ " LEFT JOIN UTILISATEURS UT ON UT.no_utilisateur = E.no_utilisateur"
			+ " JOIN CATEGORIES C ON C.no_categorie = A.no_categorie"
			
			+ " AND (? IS NULL OR A.no_categorie = ?)"
			+ " AND (? IS NULL OR A.nom_article LIKE '%' + ? + '%')"
			+ " AND (? IS NULL OR A.etat_vente = ?)"
			+ " AND (? IS NULL OR UT.no_utilisateur = ?)"
			+ " AND (? IS NULL OR ? IS NULL OR (A.etat_vente = ? AND UT.no_utilisateur = ?))";
	
	private static final String COMPLETE_FILTERED_SEARCH_SALES = "SELECT A.no_article, A.nom_article, A.description, A.date_debut_enchere, A.date_fin_enchere,"
			+ " A.prix_initial, A.prix_vente,"
			+ " A.no_utilisateur, U.pseudo AS VENDEUR, A.no_categorie, C.libelle AS CAT_LIB, A.etat_vente, A.image, E.no_utilisateur,"
			+ " UT.no_utilisateur, UT.pseudo AS ACHETEUR"
			+ " FROM ARTICLES_VENDUS A"
			+ " LEFT JOIN ENCHERES E ON E.no_article = A.no_article"
			+ " LEFT JOIN UTILISATEURS U ON U.no_utilisateur = A.no_utilisateur"
			+ " LEFT JOIN UTILISATEURS UT ON UT.no_utilisateur = E.no_utilisateur"
			+ " JOIN CATEGORIES C ON C.no_categorie = A.no_categorie"
			
			+ " AND (? IS NULL OR A.no_categorie = ?)"
			+ " AND (? IS NULL OR A.nom_article LIKE '%' + ? + '%')"
			+ " AND (? IS NULL OR A.etat_vente = ?)"
			+ " AND (? IS NULL OR ? IS NULL OR (A.etat_vente = ? AND A.no_utilisateur = ?))";
	
	
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


	@Override
	public List<Item> filteredSearch(int no_category, String name) {
		List<Item> result = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(SIMPLE_FILTERED_SEARCH);
			
			if (no_category != 0) {
			    ps.setInt(1, no_category);
			    ps.setInt(2, no_category);
			} else {
			    ps.setNull(1, java.sql.Types.INTEGER);
			    ps.setNull(2, java.sql.Types.INTEGER);
			}
			if (name != null && !name.isEmpty()) {
			    ps.setString(3, "%" + name + "%");
			    ps.setString(4, "%" + name + "%");
			} else {
			    ps.setNull(3, java.sql.Types.VARCHAR);
			    ps.setNull(4, java.sql.Types.VARCHAR);
			}
			
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
	public List<Item> completeFilteredSearchPurchases(int no_category, String name, String sell_status, int no_user) {
		List<Item> result = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(COMPLETE_FILTERED_SEARCH_PURCHASES);
			
			if (no_category != 0) {
			    ps.setInt(1, no_category);
			    ps.setInt(2, no_category);
			} else {
			    ps.setNull(1, java.sql.Types.INTEGER);
			    ps.setNull(2, java.sql.Types.INTEGER);
			}
			
			if (name != null && !name.isEmpty()) {
			    ps.setString(3, "%" + name + "%");
			    ps.setString(4, "%" + name + "%");
			} else {
			    ps.setNull(3, java.sql.Types.VARCHAR);
			    ps.setNull(4, java.sql.Types.VARCHAR);
			}
			
			if (sell_status != null && !sell_status.isEmpty()) {
			    ps.setString(5, sell_status);
			    ps.setString(6, sell_status);
			    ps.setString(9, sell_status);
			    ps.setString(11, sell_status);
			} else {
			    ps.setNull(5, java.sql.Types.VARCHAR);
			    ps.setNull(6, java.sql.Types.VARCHAR);
			    ps.setNull(9, java.sql.Types.VARCHAR);
			    ps.setNull(11, java.sql.Types.VARCHAR);
			}
			
			if (no_user != 0) {
				ps.setInt(7, no_user);
				ps.setInt(8, no_user);
				ps.setInt(10, no_user);
				ps.setInt(12, no_user);
			} else {
				
				ps.setNull(7, java.sql.Types.INTEGER);
				ps.setNull(8, java.sql.Types.INTEGER);
				ps.setNull(10, java.sql.Types.INTEGER);
				ps.setNull(12, java.sql.Types.INTEGER);
			}
			
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
				newItem.setUser_Pseudo(rs.getString("VENDEUR"));
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
	public List<Item> completeFilteredSearchSales(int no_category, String name, String sell_status, int no_user) {
		List<Item> result = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(COMPLETE_FILTERED_SEARCH_SALES);
			
			if (no_category != 0) {
			    ps.setInt(1, no_category);
			    ps.setInt(2, no_category);
			} else {
			    ps.setNull(1, java.sql.Types.INTEGER);
			    ps.setNull(2, java.sql.Types.INTEGER);
			}
			
			if (name != null && !name.isEmpty()) {
			    ps.setString(3, "%" + name + "%");
			    ps.setString(4, "%" + name + "%");
			} else {
			    ps.setNull(3, java.sql.Types.VARCHAR);
			    ps.setNull(4, java.sql.Types.VARCHAR);
			}
			
			if (sell_status != null && !sell_status.isEmpty()) {
			    ps.setString(5, sell_status);
			    ps.setString(6, sell_status);
			    ps.setString(9, sell_status);
			    ps.setString(11, sell_status);
			} else {
			    ps.setNull(5, java.sql.Types.VARCHAR);
			    ps.setNull(6, java.sql.Types.VARCHAR);
			    ps.setNull(9, java.sql.Types.VARCHAR);
			    ps.setNull(11, java.sql.Types.VARCHAR);
			}
			
			if (no_user != 0) {
				ps.setInt(7, no_user);
				ps.setInt(8, no_user);
				ps.setInt(10, no_user);
				ps.setInt(12, no_user);
			} else {
				
				ps.setNull(7, java.sql.Types.INTEGER);
				ps.setNull(8, java.sql.Types.INTEGER);
				ps.setNull(10, java.sql.Types.INTEGER);
				ps.setNull(12, java.sql.Types.INTEGER);
			}
			
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
				newItem.setUser_Pseudo(rs.getString("VENDEUR"));
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
	
	
	
}

package dal;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.User;
import util.PasswordUtil;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String LOGIN = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	private static final String CREATE_USER = "INSERT INTO UTILISATEURS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CHECK_EXISTING_EMAIL = "SELECT no_utilisateur from UTILISATEURS WHERE EMAIL = ?";
	private static final String CHECK_EXISTING_PSEUDO = "SELECT no_utilisateur from UTILISATEURS WHERE PSEUDO = ?";
	
	@Override
	public User login(String pseudoOrEmail, String password) {
		User result = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(LOGIN);
			
			ps.setString(1, pseudoOrEmail);
			ps.setString(2, pseudoOrEmail);
			ps.setString(3, password);
			
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User loggedUser = new User();
				loggedUser.setNo_user(rs.getInt("no_utilisateur"));
				loggedUser.setPseudo(rs.getString("pseudo"));
				loggedUser.setLastName(rs.getString("nom"));
				loggedUser.setFirstName(rs.getString("prenom"));
				loggedUser.setEmail(rs.getString("email"));
				loggedUser.setPhoneNumber(rs.getString("telephone"));
				loggedUser.setStreetAddress(rs.getString("rue"));
				loggedUser.setPostalCodeAddress(rs.getString("code_postal"));
				loggedUser.setCityAddress(rs.getString("ville"));
				
				try {
					loggedUser.setPassword(PasswordUtil.hashPassword(rs.getString("mot_de_passe")));
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
				
				loggedUser.setCredit(rs.getInt("credit"));
				loggedUser.setAdmin(rs.getBoolean("administrateur"));
				
				result = loggedUser;
			} else {
				result = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean createUser(User user) {
		boolean result = false;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getPseudo());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhoneNumber());
			ps.setString(6, user.getStreetAddress());
			ps.setString(7, user.getPostalCodeAddress());
			ps.setString(8, user.getCityAddress());
			ps.setString(9, user.getPassword());
			ps.setInt(10, user.getCredit());
			ps.setBoolean(11, false);
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public boolean checkEmail(String email) {
		boolean result = false;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(CHECK_EXISTING_EMAIL);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = false;
			} else {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public boolean checkPseudo(String pseudo) {
		boolean result = false;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(CHECK_EXISTING_PSEUDO);
			ps.setString(1, pseudo);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = false;
			} else {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

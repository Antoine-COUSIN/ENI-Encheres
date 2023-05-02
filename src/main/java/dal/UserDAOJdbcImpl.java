package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.User;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String LOGIN = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	
	@Override
	public User login(String pseudoOrEmail, String password) {
		User result = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(LOGIN);
			
			ps.setString(1, pseudoOrEmail);
			ps.setString(2, pseudoOrEmail);
			ps.setString(3, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User loggedUser = new User();
				loggedUser.setNo_user(rs.getInt("no_utilisateur"));
				loggedUser.setPseudo(rs.getString("pseudo"));
				loggedUser.setLastName(rs.getString("nom"));
				loggedUser.setFirstName(rs.getString("prenom"));
				loggedUser.setEmail(rs.getString("email"));
				loggedUser.setPhoneNumber(rs.getString("telephone"));
				loggedUser.setStreetAddress(rs.getString("rue"));
				loggedUser.setPostalCodeAddress(Integer.parseInt(rs.getString("code_postal")));
				loggedUser.setCityAddress(rs.getString("ville"));
				loggedUser.setPassword(rs.getString("mot_de_passe"));
				loggedUser.setCredit(rs.getInt("credit"));
				loggedUser.setAdmin(rs.getBoolean("administrateur"));
				
				result = loggedUser;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}

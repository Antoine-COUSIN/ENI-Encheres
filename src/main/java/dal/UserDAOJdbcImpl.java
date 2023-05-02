package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.User;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String LOGIN = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	
	@Override
	public User login(User user) {
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(LOGIN);
			
			ps.setString(1, user.getPseudo());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

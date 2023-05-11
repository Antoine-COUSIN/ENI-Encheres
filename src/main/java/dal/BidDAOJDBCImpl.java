package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import bo.Auctions;

public class BidDAOJDBCImpl implements BidDAO {

	private static final String CREATE_BID = "UPDATE ENCHERES SET no_utilisateur = ?, date_enchere = ?, montant_enchere = ? WHERE no_article = ?";
	private static final String GET_BIDS_INFO = "SELECT * FROM ENCHERES WHERE no_article = ?";
	
	@Override
	public void placeBid(int no_article, int no_user, int price) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareCall(CREATE_BID);
			
			ps.setInt(1, no_user);
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(3, price);
			ps.setInt(4, no_article);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Auctions getBidInfo(int no_article) {
		Auctions result = null;
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			PreparedStatement ps = cnx.prepareStatement(GET_BIDS_INFO);
			
			ps.setInt(1, no_article);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Auctions bid = new Auctions();
				bid.setNo_user(rs.getInt("no_utilisateur"));
				bid.setNo_item(rs.getInt("no_article"));
				LocalDateTime localDateTime = rs.getTimestamp("date_enchere").toLocalDateTime();
				bid.setAuction_date(localDateTime);
				bid.setBid_amount(rs.getInt("montant_enchere"));
				
				result = bid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

package dal;

import bo.Auctions;

public interface BidDAO {
	
	public void placeBid(int no_article, int no_user, int price);
	public Auctions getBidInfo(int no_article);
	
}

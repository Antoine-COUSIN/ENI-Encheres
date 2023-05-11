package bo;

import java.time.LocalDateTime;

public class Auctions {
	
	private int no_user;
	private int no_item;
	private LocalDateTime auction_date;
	private int bid_amount;
	
	public Auctions() {
		
	}
	
	public Auctions(int no_user, int no_item, LocalDateTime auction_date, int bid_amount) {
		super();
		this.no_user = no_user;
		this.no_item = no_item;
		this.auction_date = auction_date;
		this.bid_amount = bid_amount;
	}
	
	public int getNo_user() {
		return no_user;
	}
	public void setNo_user(int no_user) {
		this.no_user = no_user;
	}
	public int getNo_item() {
		return no_item;
	}
	public void setNo_item(int no_item) {
		this.no_item = no_item;
	}
	public LocalDateTime getAuction_date() {
		return auction_date;
	}
	public void setAuction_date(LocalDateTime auction_date) {
		this.auction_date = auction_date;
	}
	public int getBid_amount() {
		return bid_amount;
	}
	public void setBid_amount(int bid_amount) {
		this.bid_amount = bid_amount;
	}
	
}

package bo;

import java.time.LocalDateTime;

public class Item {
	
	private int no_article;
	private String name_article;
	private String descr_article;
	private LocalDateTime start_auction;
	private LocalDateTime end_auction;
	private int initial_price;
	private int sell_price;
	
	private User user;
	private Category category;
	
	private String sell_status;
	private String image_article;
	
	public Item() {
		
	}

	public Item(int no_article, String name_article, String descr_article, LocalDateTime start_auction,
			LocalDateTime end_auction, int initial_price, int sell_price, User user, Category category, String sell_status,
			String image_article) {
		this.no_article = no_article;
		this.name_article = name_article;
		this.descr_article = descr_article;
		this.start_auction = start_auction;
		this.end_auction = end_auction;
		this.initial_price = initial_price;
		this.sell_price = sell_price;
		this.user = user;
		this.category = category;
		this.sell_status = sell_status;
		this.image_article = image_article;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public String getName_article() {
		return name_article;
	}

	public void setName_article(String name_article) {
		this.name_article = name_article;
	}

	public String getDescr_article() {
		return descr_article;
	}

	public void setDescr_article(String descr_article) {
		this.descr_article = descr_article;
	}

	public LocalDateTime getStart_auction() {
		return start_auction;
	}

	public void setStart_auction(LocalDateTime start_auction) {
		this.start_auction = start_auction;
	}

	public LocalDateTime getEnd_auction() {
		return end_auction;
	}

	public void setEnd_auction(LocalDateTime end_auction) {
		this.end_auction = end_auction;
	}

	public int getInitial_price() {
		return initial_price;
	}

	public void setInitial_price(int initial_price) {
		this.initial_price = initial_price;
	}

	public int getSell_price() {
		return sell_price;
	}

	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSell_status() {
		return sell_status;
	}

	public void setSell_status(String sell_status) {
		this.sell_status = sell_status;
	}

	public String getImage_article() {
		return image_article;
	}

	public void setImage_article(String image_article) {
		this.image_article = image_article;
	}
	
}

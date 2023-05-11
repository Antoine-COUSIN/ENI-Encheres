package bll;

import bo.Auctions;
import bo.Item;
import bo.User;
import dal.ArticleDAO;
import dal.BidDAO;
import dal.DAOFactory;
import dal.UserDAO;

public class BidBLL {
	
	private BidDAO bidDAO;
	private UserDAO userDAO;
	private ArticleDAO articleDAO;
	
	public BidBLL() {
		bidDAO = DAOFactory.getBidDAO();
		userDAO = DAOFactory.getUserDAO();
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public void createBid(int no_article, int no_user, int price) throws BidBLLException{
		checkValues(no_article, no_user, price);
		bidDAO.placeBid(no_article, no_user, price);
	}
	
	public Auctions getBidInfo(int no_article) {
		return bidDAO.getBidInfo(no_article);
	}
	
	private void checkValues(int no_article, int no_user, int price) throws BidBLLException {
		BidBLLException e = new BidBLLException();
		User user = userDAO.getUser(no_user);
		Item item = articleDAO.selectOneItem(no_article);
		
		Auctions bid = bidDAO.getBidInfo(no_article);
		
		if (user.getCredit() < price) {
			e.addError(1);
		}
		if (price <= 0) {
			e.addError(2);
		}
		if (bid.getBid_amount() > price) {
			e.addError(3);
		}
		
		if (e.getErrors().size() > 0) {
			throw e;
		}
	}
	
	
}

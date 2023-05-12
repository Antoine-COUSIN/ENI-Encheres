package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bll.BidBLL;
import bll.BidBLLException;
import bll.UserBLL;
import bo.Auctions;
import bo.Item;
import bo.PickupPoint;
import bo.User;


@WebServlet("/article-detail")
public class AuctionDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	BidBLL bidBLL;
	UserBLL userBLL;
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
		bidBLL = new BidBLL();
		userBLL = new UserBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String StringNo_article = request.getParameter("id");
		int no_article = Integer.parseInt(StringNo_article);
		Item selectedItem = articleBLL.selectOneItem(no_article);
		Auctions selectedBid = bidBLL.getBidInfo(no_article);
		PickupPoint selectedPickupPoint = articleBLL.getSelectedItemPickupPoint(no_article);
		User userVendor = userBLL.getUser(selectedItem.getNo_user());
		request.setAttribute("userVendor", userVendor);
		request.getSession().setAttribute("selectedBid", selectedBid);
		request.getSession().setAttribute("selectedItem", selectedItem);
		request.getSession().setAttribute("selectedPickupPoint", selectedPickupPoint);
		request.getRequestDispatcher("WEB-INF/jsp/item-detail.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pBid = request.getParameter("bid");
		User userSession = (User) request.getSession().getAttribute("user");
		Item item = (Item) request.getSession().getAttribute("selectedItem");
		
		
		try {
			bidBLL.createBid(item.getNo_article(), userSession.getNo_user(), (pBid != null && pBid != "" ? Integer.parseInt(pBid) : -1));
			request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BidBLLException e) {
			request.setAttribute("bidErrors", e.getErrors());
			request.getRequestDispatcher("WEB-INF/jsp/item-detail.jsp").forward(request, response);
		}
	}
			
		
}

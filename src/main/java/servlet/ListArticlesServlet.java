package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bo.Item;
import bo.User;


@WebServlet("/liste_auctions")
public class ListArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBLL articleBLL;
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pCategory = Integer.parseInt(request.getParameter("category"));
		String pArticleName = request.getParameter("article-name");
		
		List<Item> filteredSearch = articleBLL.filteredSearch(pCategory, pArticleName);
		request.getSession().setAttribute("articles", filteredSearch);
		
		if ((Boolean) request.getSession().getAttribute("isConnected")) {
			//unlocking more filters
			String pFilterRadio = request.getParameter("option");
			switch (pFilterRadio) {
				case "my-purchases":
					//PURCHASES FILTERS
					filterOnPurchases(request, pCategory, pArticleName);
					break;
					
					//SALES FILTERS
				case "my-sales":
					filterOnSales(request, pCategory, pArticleName);
					break;
				
				default:
			}
			
			
			
		} 
		
		request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);

	}

	

	private void filterOnPurchases(HttpServletRequest request, int no_category, String name_article) {
		
		
		User userSession = (User) request.getSession().getAttribute("user");
		int no_user = 0;
		
		String pOpenAuction = request.getParameter("open_auction");
		String pMyAuctions = request.getParameter("my_auctions");
		String pMyAuctionsWon = request.getParameter("my_auctions_won");
		String sellStatus = null;
		
		if (pOpenAuction != null) {
			sellStatus = "EC";
		} else if (pMyAuctionsWon != null) {
			sellStatus = "VD";
		}
		if (pMyAuctions != null) {
			sellStatus = "";
		}
		
		if (pMyAuctions != null || pMyAuctionsWon != null) {
			no_user = userSession.getNo_user();
		}
		
		List<Item> newCompleteFilteredSearch = articleBLL.completeFilteredSearchPurchases(no_category, name_article, sellStatus, no_user);
		request.getSession().setAttribute("articles", newCompleteFilteredSearch);
	}
	
	
	private void filterOnSales(HttpServletRequest request, int no_category, String name_article) {
		
	}

}

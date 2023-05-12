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
		List<Item> articles = articleBLL.listArticles();
		request.getSession().setAttribute("articles", articles);
		
		request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pCategory = Integer.parseInt(request.getParameter("category"));
		String pArticleName = request.getParameter("article-name");
		String option = "";
		boolean isConnected = false;

		if (request.getSession().getAttribute("isConnected") == null) {
			isConnected = false;
		} else {
			isConnected = true;
		}

		if (isConnected) {
			// unlocking more filters
			String pFilterRadio = request.getParameter("option");
			if (pFilterRadio == null || pFilterRadio.isEmpty()) {
				List<Item> noFilters = articleBLL.listArticles();
				request.getSession().setAttribute("articles", noFilters);
			} else {
				switch (pFilterRadio) {
				case "my-purchases":
					// PURCHASES FILTERS
					option = "purchases";
					break;
				// SALES FILTERS
				case "my-sales":
					option = "sales";
					break;

				default:
					break;
				}

				User userSession = (User) request.getSession().getAttribute("user");
				int no_user = 0;
				String pOpenAuction = request.getParameter("open_auction");
				String pMyAuctions = request.getParameter("my_auctions");
				String pMyAuctionsWon = request.getParameter("my_auctions_won");

				String pMySales = request.getParameter("my_sales");
				String pMyNotStartedAuctions = request.getParameter("not_started_sales");
				String pClosedSales = request.getParameter("closed_sales");

				String sellStatus1 = "";
				String sellStatus2 = "";
				String sellStatus3 = "";

				if (pOpenAuction != null || pMyAuctions != null) {
					sellStatus1 = "EC";
				}
				if (pMyAuctionsWon != null || pClosedSales != null) {
					sellStatus2 = "VD";
				}
				if (pMyNotStartedAuctions != null) {
					sellStatus3 = "CR";
				}
				if (pMyAuctions != null || pMyAuctionsWon != null || pMySales != null) {
					no_user = userSession.getNo_user();
				}

				List<Item> newCompleteFilteredSearch = articleBLL.completeFilteredSearchPurchases(pCategory,
						pArticleName, sellStatus1, sellStatus2, sellStatus3, no_user, option);
				request.getSession().setAttribute("articles", newCompleteFilteredSearch);

			}

			request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);

		} else {
			List<Item> noFilters = articleBLL.filteredSearch(pCategory, pArticleName);
			request.getSession().setAttribute("articles", noFilters);
			request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
		}

	}
}

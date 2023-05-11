package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bo.Item;


@WebServlet("/article-detail")
public class AuctionDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String StringNo_article = request.getParameter("id");
		int no_article = Integer.parseInt(StringNo_article);
		Item selectedItem = articleBLL.selectOneItem(no_article);
		request.getSession().setAttribute("selectedItem", selectedItem);
		request.getRequestDispatcher("WEB-INF/jsp/item-detail.jsp").forward(request, response);
			
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pBid = request.getParameter("bid");
		
		if (pBid != null && !pBid.equals("") && Integer.parseInt(pBid) > 0) {
				System.out.println(pBid);
				request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
		} else {
				request.setAttribute("errors", 21);
				request.getRequestDispatcher("WEB-INF/jsp/item-detail.jsp").forward(request, response);
		}
	}
			
		
}

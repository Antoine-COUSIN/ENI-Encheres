package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleBLL;
import bo.Category;
import bo.Item;


@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBLL articleBLL;
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = articleBLL.listCategories();
		List<Item> articles = articleBLL.listArticles();
		
		request.getSession().setAttribute("categories", categories);
		request.getSession().setAttribute("articles", articles);
		
		request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
	}


}

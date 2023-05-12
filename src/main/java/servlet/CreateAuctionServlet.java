package servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bll.ArticleBLL;
import bll.ArticleBLLException;
import bo.Category;
import bo.Item;
import bo.PickupPoint;
import bo.User;


@WebServlet("/createAuction")
@MultipartConfig
public class CreateAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBLL articleBLL;
	public static final String SAVE_DIRECTORY = "uploads";
	
	@Override
	public void init() throws ServletException {
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		
		String formattedCurrentDate = currentDate.format(formatter);
		String formattedEndDate = currentDate.plusDays(30).format(formatter);
		
		request.setAttribute("currentDate", formattedCurrentDate);
		request.setAttribute("endAuctionDate", formattedEndDate);
		
		
		//DELETE THIS
		List<Category> categories = articleBLL.listCategories();
		request.getSession().setAttribute("categories", categories);
		//***************
		
		request.getRequestDispatcher("WEB-INF/jsp/create-auction.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if ("save".equals(action)) {
			
			String pArticleName = request.getParameter("articleName");
			String pDescr = request.getParameter("descr");
			int pNoCat = Integer.parseInt(request.getParameter("category"));
			int pPrice = Integer.parseInt(request.getParameter("price"));
			LocalDateTime pStartAuction = LocalDateTime.parse(request.getParameter("startAuction"));
			LocalDateTime pEndAuction = LocalDateTime.parse(request.getParameter("endAuction"));
			String pStreetAddress = request.getParameter("streetAddress");
			String pPostalCodeAddress = request.getParameter("postalCode");
			String pCityAddress = request.getParameter("cityAddress");
			String appPath = request.getServletContext().getRealPath("");
			Part part = request.getPart("pictureFile");
			String fileName = saveFile(appPath, part);
			
			Item newItem = new Item();
			newItem.setName_article(pArticleName);
			newItem.setDescr_article(pDescr);
			newItem.setStart_auction(pStartAuction);
			newItem.setEnd_auction(pEndAuction);
			newItem.setInitial_price(pPrice);
			
			User currentUser = (User) request.getSession().getAttribute("user");
			newItem.setNo_user(currentUser.getNo_user());
			
			newItem.setNo_category(pNoCat);
			
			newItem.setSell_status("CR");
			newItem.setImage_article(fileName);
			
			PickupPoint newPU = new PickupPoint();
			newPU.setCityAddress(pCityAddress);
			newPU.setPostalCode(pPostalCodeAddress);
			newPU.setStreetAddress(pStreetAddress);
			
			try {
				articleBLL.createItem(newItem, newPU);
				request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
			} catch (ArticleBLLException e) {
				request.setAttribute("createArticleError", e.getErrors());
				request.getRequestDispatcher("WEB-INF/jsp/create-auction.jsp").forward(request, response);
			}
			
			
			
		} else if ("cancel".equals(action)) {
			request.getRequestDispatcher("WEB-INF/jsp/liste-encheres.jsp").forward(request, response);
		}

		
	}

	private String saveFile(String appPath, Part part) throws IOException {
		appPath = appPath.replace('\\', '/');
		 // The directory to save uploaded file
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }
        
     // Creates the save directory if it does not exists
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String filePath=null;

        String fileName = extractFileName(part);
        System.out.println(fileName);
        String[] fn = fileName.split("(\\.)");
        fileName = fn[0];
        String ext = fn[(fn.length-1)];
        if(!ext.isEmpty()) {
        	//generate a unique file name
        	UUID uuid = UUID.randomUUID();
        	fileName = fileName + "_" + uuid.toString() + "." + ext ;
        	if (fileName != null && fileName.length() > 0) {
        		filePath = fullSavePath + File.separator + fileName;
        		System.out.println("Write attachment to file: " + filePath);
        		// Write to file
        		part.write(filePath);
        	}
        }
        return fileName;
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
	}

}

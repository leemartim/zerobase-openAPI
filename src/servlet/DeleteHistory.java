package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WifiDAO;
import service.WifiService;
import service.WifiServiceImpl;
import vo.DataHistoryVO;

/**
 * Servlet implementation class DeleteHistory
 */
@WebServlet("/deleteHistory")
public class DeleteHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteHistory() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8"); //제이슨 인코딩
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		DataHistoryVO vo = new DataHistoryVO(); 
		WifiDAO dao = new WifiDAO();
		WifiService service = new WifiServiceImpl(dao);
		service.deleteHistoryBack(id);
	}

}

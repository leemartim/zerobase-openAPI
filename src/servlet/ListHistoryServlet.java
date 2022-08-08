package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WifiDAO;
import service.WifiService;
import service.WifiServiceImpl;
import vo.DataHistoryVO;
import vo.WifiDataVO;

/**
 * Servlet implementation class ListHistoryServlet
 */
@WebServlet("/ListHistoryServlet")
public class ListHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListHistoryServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		DataHistoryVO vo = new DataHistoryVO(); 
		WifiDAO dao = new WifiDAO();
		WifiService service = new WifiServiceImpl(dao);
		
		List<DataHistoryVO> list = service.selectDataHistoryBackList();
		
		request.setAttribute("list", list);
		String page = "/history.jsp";
		
		getServletContext().
		getRequestDispatcher(page).
		forward(request, response);
		
	}


}

package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.WifiDAO;
import service.WifiService;
import service.WifiServiceImpl;
import vo.DataHistoryVO;
import vo.WifiDataVO;

/**
 * Servlet implementation class ListWifiServlet
 */
@WebServlet("/ListWifiServlet")
public class ListWifiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListWifiServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		System.out.println("doPost");
		double x = Double.parseDouble(request.getParameter("x"));
		double y = Double.parseDouble(request.getParameter("y"));
		
		WifiDataVO vo = new WifiDataVO(); 
		WifiDAO dao = new WifiDAO();
		WifiService service = new WifiServiceImpl(dao);

		Gson gson = new Gson();
		
		List<WifiDataVO> list = service.selectWifiList(x, y);
		System.out.println(list.toString()); 
		DataHistoryVO hvo = new DataHistoryVO(); //히스토리 등록
		hvo.setXCoordinate(x);
		hvo.setYCoordinate(y);
		System.out.println(hvo.toString()); 
		service.insertWifiHistory(hvo); //로그 정보 담기
		request.setAttribute("x", x); // ${data} 
		request.setAttribute("y", y);  // ("key", value)
		request.setAttribute("list", list);
		String page = "/index.jsp";
		
		getServletContext().
		getRequestDispatcher(page).
		forward(request, response);
		
	}

}

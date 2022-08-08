package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WifiDAO;
import service.WifiService;
import service.WifiServiceImpl;
import vo.WifiDataVO;

@WebServlet("/deleteWifiServlet")
public class DeleteWifiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteWifiServlet() {
    }

    
    //포스트 방식으로 데이터넣음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8"); //제이슨 인코딩
		request.setCharacterEncoding("utf-8");
		
		WifiDAO dao = new WifiDAO();
		WifiService services = new WifiServiceImpl(dao);
		services.deleteWifi();
	}

}

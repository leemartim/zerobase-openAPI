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

/**
 * Servlet implementation class WifiServlet
 */
@WebServlet("/InsertWifiServlet")
public class InsertWifiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertWifiServlet() {
    }

    //포스트 방식으로 데이터넣음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8"); //제이슨 인코딩
		request.setCharacterEncoding("utf-8");
		WifiDataVO vo = new WifiDataVO(); 
		
		String manageNum = request.getParameter("manageNum");
		String borough = request.getParameter("borough");
		String agency = request.getParameter("agency");
		String streetAddress = request.getParameter("streetAddress");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		String typeNet = request.getParameter("typeNet");
		String service = request.getParameter("service");
		String wifiConnection = request.getParameter("wifiConnection");
		String year = request.getParameter("year");
		//String year = "2022";
		String installLocation = request.getParameter("installLocation");
		double xCoordinate = Double.parseDouble(request.getParameter("xCoordinate"));
		double yCoordinate = Double.parseDouble(request.getParameter("yCoordinate"));
		String date = request.getParameter("date");
		vo.setManageNum(manageNum);
		vo.setBorough(borough);
		vo.setAgency(agency);
		vo.setStreetAddress(streetAddress);
		vo.setAddress(address);
		vo.setType(type);
		vo.setTypeNet(typeNet);
		vo.setService(service);
		vo.setInstallLocation(installLocation);
		vo.setWifiConnection(wifiConnection);
		vo.setYear(year);
		
		//vo.setInstallation(installation);
		vo.setXCoordinate(xCoordinate);
		vo.setYCoordinate(yCoordinate);
		vo.setDate(date);
		
		System.out.println(vo.toString());
		WifiDAO dao = new WifiDAO();
		WifiService services = new WifiServiceImpl(dao);
		services.insertWifi(vo); //Open Api 와이파이 정보 가져오기
		
	}

}

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.DataHistoryVO;
import vo.WifiDataVO;

public class WifiDAO {

	ResultSet rs =null;
	Connection con = null;
	PreparedStatement ps = null;
	
	public void insertWifi(WifiDataVO vo) {// 등록
		
		String sql = "INSERT INTO wifi_data";
			   sql += "( manage_num, borough, street_address, address, year, type, agency, service, type_net";
			   sql += ", install_location, wifi_connection, x_coordinate, y_coordinate, date)";
			   sql += " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? ";
			   sql += ", ? , ? ,? ,? , ?)";
			   int row = 0;
			      try {
			    	  con = JDBCUtil.getConnection();
			    	  ps = con.prepareStatement(sql);
			    	  
			          ps.setString(1, vo.getManageNum());
			          ps.setString(2,  vo.getBorough());
			          ps.setString(3, vo.getStreetAddress());
			          ps.setString(4, vo.getAddress());
			          ps.setString(5, vo.getYear());
			          ps.setString(6, vo.getType());
			          ps.setString(7, vo.getAgency());
			          ps.setString(8, vo.getService());
			          ps.setString(9, vo.getTypeNet());
			          ps.setString(10, vo.getInstallLocation());
			          ps.setString(11, vo.getWifiConnection());
			          ps.setDouble(12, vo.getXCoordinate());
			          ps.setDouble(13, vo.getYCoordinate());
			          ps.setString(14, vo.getDate());
			    	  
			          row = ps.executeUpdate();
			          
			          if(row == 0) {
			        	  throw new Exception("등록실패");
			          }
			      } catch (Exception e) {
			    	  e.printStackTrace();
				} finally {
					JDBCUtil.close(con, ps, null);
				}
	}
	
	public void deleteWifi() { //삭제
		
		String sql = "DELETE FROM wifi_data";
			    
			      try {
			    	  
			    	  con = JDBCUtil.getConnection();
			    	  ps = con.prepareStatement(sql);
			    	  
			    	  ps.executeUpdate();
			      } catch (Exception e) {
			    	  e.printStackTrace();
				} finally {
					JDBCUtil.close(con, ps, null);
				}
	}

	public List<WifiDataVO> selectWifiList(double x, double y) { //내 위치 데이터 가져오기
		
		List<WifiDataVO> list = new ArrayList<WifiDataVO>();
		String sql = " SELECT *,  ABS( x_coordinate - ?) AS Distance, ABS( y_coordinate - ?) AS sDistance FROM wifi_data ";
		sql += " ORDER BY sDistance , Distance  LIMIT 20; ";
		try {
		      con = JDBCUtil.getConnection();
	    	  ps = con.prepareStatement(sql);
	    	  ps.setDouble(1, x);
	    	  ps.setDouble(2, y);
	    	  
	    	  rs = ps.executeQuery();
	    	  
	    	  while(rs.next()) {
	    		  WifiDataVO vo = new WifiDataVO();
	    		  vo.setManageNum(rs.getString("manage_num"));
	    		  vo.setBorough(rs.getString("borough"));
	    		  vo.setStreetAddress(rs.getString("street_address"));
	    		  vo.setAddress(rs.getString("address"));
	    		  vo.setType(rs.getString("type"));
	    		  vo.setAgency(rs.getString("agency"));
	    		  vo.setService(rs.getString("service"));
	    		  vo.setTypeNet(rs.getString("type_net"));
	    		  vo.setInstallLocation(rs.getString("install_location"));
	    		  vo.setWifiConnection(rs.getString("wifi_connection"));
	    		  vo.setYear(rs.getString("year"));
	    		  vo.setXCoordinate(rs.getDouble("x_coordinate"));
	    		  vo.setYCoordinate(rs.getDouble("y_coordinate"));
	    		  vo.setDateT(rs.getTimestamp("date"));
	    		  list.add(vo);
	    	  }
	    	  
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } finally {
	    	  JDBCUtil.close(con, ps, rs);
	      }
		
		return list;
	}
	
public void insertWifiHistory(DataHistoryVO vo) { //히스토리 인설트
		
		System.out.println(vo.toString());
		String sql = " INSERT INTO data_history";
			   sql += " ( x_coordinate, y_coordinate, lookup_date)";
			   sql += " VALUES( ?, ?, now())";  
			   int row = 0;

			      try {
			    	  
			    	  con = JDBCUtil.getConnection();
			    	  ps = con.prepareStatement(sql);
			    	  
			          ps.setDouble(1,  vo.getXCoordinate());
			          ps.setDouble(2, vo.getYCoordinate());
			    	  
			          row = ps.executeUpdate();
			          
			          if(row == 0) {
			        	  throw new Exception("등록실패");
			          }
			      } catch (Exception e) {
			    	  e.printStackTrace();
				} finally {
					JDBCUtil.close(con, ps, null);
				}
	}

	public List<DataHistoryVO> selectDataHistoryBackList() { //내 위치 데이터 가져오기
		
		List<DataHistoryVO> list = new ArrayList<DataHistoryVO>();
		String sql = " SELECT * FROM data_history; ";
		try {
		      con = JDBCUtil.getConnection();
	    	  ps = con.prepareStatement(sql);
	    	  rs = ps.executeQuery();
	    	  
	    	  while(rs.next()) {
	    		  DataHistoryVO vo = new DataHistoryVO();
	    		  vo.setId(rs.getInt("id"));
	    		  vo.setXCoordinate(rs.getDouble("x_coordinate"));
	    		  vo.setYCoordinate(rs.getDouble("y_coordinate"));
	    		  vo.setLookupDate(rs.getTimestamp("lookup_date"));
	    		 
	    		  list.add(vo);
	    	  }
	    	  
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } finally {
	    	  JDBCUtil.close(con, ps, rs);
	      }
		
		return list;
	}
	
	public int deleteHistoryBack(int id) {
	      int row = 0;
	      
	      String sql = "Delete from data_history where id = ?";
	      
	      try {
	    	  con = JDBCUtil.getConnection();
	    	  ps = con.prepareStatement(sql);
	    	  ps.setInt(1, id);
	    	 
	    	  row = ps.executeUpdate();
	    	  
	    	  if(row == 0) {
	        	  throw new Exception("등록실패");
	          }
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } finally {
	    	  JDBCUtil.close(con, ps, null);
	      }
	      
	      return row;      
	   }
}

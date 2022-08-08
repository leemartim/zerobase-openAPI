package service;

import java.util.List;

import dao.WifiDAO;
import vo.DataHistoryVO;
import vo.WifiDataVO;


public class WifiServiceImpl implements WifiService {

	private WifiDAO dao = null;

	public WifiServiceImpl() { }
	
	public WifiServiceImpl(WifiDAO dao) {
		this.dao = dao;
	}
	public WifiDAO getDao() {
		return dao;
	}
	public void setDao(WifiDAO dao) {
		this.dao = dao;
	}

	@Override
	public void insertWifi(WifiDataVO vo) {
		dao.insertWifi(vo);
	}
	
	@Override
	public void deleteWifi() {
		dao.deleteWifi();
	}
	
	@Override
	public List<WifiDataVO> selectWifiList(double x, double y) {
		return dao.selectWifiList(x, y);
	}
	
	@Override
	public void insertWifiHistory(DataHistoryVO vo) {
		dao.insertWifiHistory(vo);
	}
	
	@Override
	public List<DataHistoryVO> selectDataHistoryBackList() {
		return dao.selectDataHistoryBackList();
	}
	
	@Override
	public void deleteHistoryBack(int id) {
		dao.deleteHistoryBack(id);
	}
}

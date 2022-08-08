package service;

import java.util.List;

import vo.DataHistoryVO;
import vo.WifiDataVO;

public interface WifiService {

	//와이파이 데이터 등록
	void insertWifi(WifiDataVO vo);
	
	//와이파이 등록시 데이터 삭제
	void deleteWifi();

	//가까운 데이터출력
	List<WifiDataVO> selectWifiList(double x, double y);
	
	//로그 남기기
	void insertWifiHistory(DataHistoryVO vo);
	
	//히스토리 리스트
	List<DataHistoryVO> selectDataHistoryBackList();
	
	//히스토리 삭제
	void deleteHistoryBack(int id);
}

package vo;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
public class WifiDataVO {

	private int id;
	private double distance; //거리
	private String manageNum; //관리번호
	private String borough; //자치구
	private String streetAddress; //주소
	private String address; //상세
	private String location;  // 미사용**********
	private String type; //타입 ->커뮤니티(행정)
	private String agency; //상수도 사업본부
	private String service; //공공wifi
	private String typeNet; //서울시
	private String installation; //설치기관
	private String installLocation; //실내
	private String wifiConnection; //수도사업 자가망
	private String year; //추가 -> 연도
	private double xCoordinate; // Lat
	private double yCoordinate; // Lnt
	private String date; //데이터 넣을때
	private Timestamp dateT; //리턴 받을때 타임스템프
	
}

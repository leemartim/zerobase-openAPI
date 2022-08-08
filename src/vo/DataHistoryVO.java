package vo;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
public class DataHistoryVO {
	
	private int id;
	private double xCoordinate;
	private double yCoordinate; 
	private Timestamp lookupDate;
	
}

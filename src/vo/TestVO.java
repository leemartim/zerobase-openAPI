package vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
public class TestVO {

	private int a;
	private int b;
	
	
	public static void main(String args[]) {
		TestVO t = new TestVO();
		
		t.setA(5);
		
		
		System.out.println(t.toString());
		}
}

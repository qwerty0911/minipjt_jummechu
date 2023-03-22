package aProject.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor
public class RestVO {
	private int id;
	private String name;
	private String className;
	private String locationName;
	private int class_id;
	private int location_id;
	private String url;
	
	@Override
	public String toString() {
		return name + "\n" + className + "\t" + locationName+ "\n" + url;
	}
	
	
}

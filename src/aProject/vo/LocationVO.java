package aProject.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor
public class LocationVO {
	private int id;
	private String name;
	
	
	@Override
	public String toString() {
		return id + "," + name+" | ";
	}
	
}

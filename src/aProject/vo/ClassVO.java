package aProject.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor
public class ClassVO {
	private int id;
	private String name;
	
	@Override
	public String toString() {
		return id + "," + name+" |";
	}
	
}

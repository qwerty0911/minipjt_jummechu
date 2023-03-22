package aProject.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor
@ToString
public class UserVO {
	private String userName;
	private String password;
}

package aProject.model;

import java.util.List;

import aProject.vo.ReviewVO;
import aProject.vo.UserVO;

public class UserService {
	
	
	UserDAO userDAO = new UserDAO();
	
	public String registUser(UserVO user) {
		int result = userDAO.registUser(user);
		return result>0?"회원가입 성공":"오류";
	}
	
	
}

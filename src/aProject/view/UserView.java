package aProject.view;

import java.util.List;

import aProject.vo.ReviewVO;
import aProject.vo.UserVO;

public class UserView {

	public static void print(UserVO user) {
		
		if(user!=null)System.out.println(user+"님 환영합니다.");
		else print("회원가입에 실패했습니다.");
	}
	
	public static void print(String message) {
		
		System.out.println("알림!!] "+message);
	}
}

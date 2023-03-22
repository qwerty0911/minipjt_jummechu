package aProject.view;

import java.util.List;

import aProject.vo.RestVO;

public class RestView {
	public static void print(List<RestVO> restList) {
			
		System.out.println("\nList");
		for(RestVO rest:restList) {
			System.out.println("--------------------------------------");
			System.out.println(rest);
		}
	}
		
		
	public static void print(RestVO rest) {
		
		
		if(rest!=null)System.out.println(rest);
		else print("해당하는 가게는 없습니다..");
	}
		
		
	public static void print(String message) {
		
		System.out.println("알림!!] "+message);
	}
		
	
}

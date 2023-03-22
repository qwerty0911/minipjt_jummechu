package aProject.view;

import java.util.List;

import aProject.vo.RestVO;
import aProject.vo.ReviewVO;

public class ReviewView {
	
	public static void print(List<ReviewVO> reviewList) {
		
		
		for(ReviewVO review:reviewList) {
			System.out.println(review);
		}
	}
	
	public static void print(ReviewVO review) {
		System.out.println("===============================");
		if(review!=null)System.out.println(review);
		else print("리뷰가 없습니다.");
	}
	
	public static void print(String message) {
		
		System.out.println("알림!!] "+message);
	}
	
}

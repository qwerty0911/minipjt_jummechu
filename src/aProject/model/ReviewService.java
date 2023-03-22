package aProject.model;

import java.util.List;
import aProject.vo.ReviewVO;

public class ReviewService {
	
	ReviewDAO reviewDAO = new ReviewDAO();
	
	public List<ReviewVO> findReviewById(int reviewid) {
		return reviewDAO.findReviewById(reviewid);
	}
	
	public String insertNewReview(ReviewVO review, int id) {
		int result = reviewDAO.insertNewReview(review, id);
		return result>0?"입력 성공":"입력 실패";
	}

}

package aProject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sinhan.dbutil.MySqlUtil;
import com.sinhan.dbutil.OracleUtil;

import aProject.vo.RestVO;
import aProject.vo.ReviewVO;

public class ReviewDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst; //?지원함
	ResultSet rs;
	int resultCount; //insert, update, delete 건수
	
	public List<ReviewVO> findReviewById(int Id) {
		List<ReviewVO> reviewList = new ArrayList<>();
		String sql = "SELECT * FROM reviews  r JOIN users u on(r.user_id = u.id)"
				+ "where restaurant_id ="+Id;
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReviewVO review= makeReview(rs);
				reviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return reviewList;
	}
	
	public int insertNewReview(ReviewVO review, int id) {
		String query = 
				"""
				insert into reviews(content,score,user_id,restaurant_id,create_at)
				values (?,?,?,?,sysdate())
				""";
		
		
		conn = MySqlUtil.getConnection();
		try {
			pst = conn.prepareStatement(query);

			
			pst.setString(1,review.getContent());
			pst.setInt(2,review.getScore());
			pst.setInt(3,id);
			pst.setInt(4,review.getRestaurant_id());
			resultCount = pst.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}
	
	
	private ReviewVO makeReview(ResultSet rs) throws SQLException {
		ReviewVO review = new ReviewVO();
		review.setId(rs.getInt("id"));
		review.setContent(rs.getString("content"));
		review.setRestaurant_id(rs.getInt("restaurant_id"));
		review.setScore(rs.getInt("score"));
		review.setUsername(rs.getString("user_name"));
		
		return review;
	}
}

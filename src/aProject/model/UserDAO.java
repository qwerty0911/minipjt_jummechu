package aProject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sinhan.dbutil.MySqlUtil;
import com.sinhan.dbutil.OracleUtil;

import aProject.vo.ReviewVO;
import aProject.vo.UserVO;

public class UserDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst; //?지원함
	ResultSet rs;
	int resultCount; //insert, update, delete 건수
	public int registUser(UserVO user) {
		String query = 
				"""
				insert into users(user_name,join_date,password) 
				values(?,sysdate(),?)
				""";
		
		
		conn = MySqlUtil.getConnection();
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,user.getUserName());
			pst.setString(2,user.getPassword());
			resultCount = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}
}

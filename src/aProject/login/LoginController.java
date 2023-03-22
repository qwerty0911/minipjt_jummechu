package aProject.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sinhan.dbutil.MySqlUtil;

import aProject.vo.UserVO;

public class LoginController {
	
	public static String findPassword(String username) {
		Connection conn;
		Statement st=null;
		PreparedStatement pst; //?지원함
		ResultSet rs = null;
		
		String sql = "select password from users where user_name='"+username+"'";
		conn = MySqlUtil.getConnection();
		String password = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return password;
	}
	
	public static boolean isUserExists(String username) {
		Connection conn;
		Statement st=null;
		PreparedStatement pst; //?지원함
		ResultSet rs = null;
		
		String sql = "select password from users where user_name='"+username+"'";
		conn = MySqlUtil.getConnection();
		String password = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return false;
	}
	
	public static int extractUserId(String username) {
		Connection conn;
		Statement st=null;
		PreparedStatement pst; //?지원함
		ResultSet rs = null;
		
		String sql = "select id from users where user_name='"+username+"'";
		conn = MySqlUtil.getConnection();
		int id = 0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				id = rs.getInt("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return id;
	}
	
	public static UserVO loginUser() {
		UserVO userVO = new UserVO();
		return userVO;
		
	}
	
	
}

package aProject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sinhan.dbutil.MySqlUtil;

import aProject.vo.ClassVO;
import aProject.vo.LocationVO;

public class LocationDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst; //?지원함
	ResultSet rs;
	int resultCount; //insert, update, delete 건수
	
	
	//
	public List<LocationVO> findAll() {
		String sql = """
				select * from locations
				""";
		List<LocationVO> locationList = new ArrayList<>();
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				LocationVO location= new LocationVO();
				location.setId(rs.getInt("id"));
				location.setName(rs.getString("name"));
				
				
				locationList.add(location);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		
		return locationList;
	}
}

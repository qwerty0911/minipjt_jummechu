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
import aProject.vo.RestVO;

public class ClassDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst; //?지원함
	ResultSet rs;
	int resultCount; //insert, update, delete 건수
	
	
	//
	public List<ClassVO> findAll() {
		String sql = """
				select * from classes
				""";
		List<ClassVO> classList = new ArrayList<>();
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				ClassVO cl= new ClassVO();
				cl.setId(rs.getInt("id"));
				cl.setName(rs.getString("name"));
				
				
				classList.add(cl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		
		return classList;
	}
}

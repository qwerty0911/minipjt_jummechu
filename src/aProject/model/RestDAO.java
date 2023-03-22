package aProject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sinhan.dbutil.MySqlUtil;
import com.sinhan.dbutil.OracleUtil;

import aProject.vo.ClassVO;
import aProject.vo.LocationVO;
import aProject.vo.RestVO;
import aProject.vo.ReviewVO;

public class RestDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst; //?지원함
	ResultSet rs;
	int resultCount; //insert, update, delete 건수
	
	
	//
	public List<RestVO> findAll() {
		String sql = """
				select r.id id,r.name restname, l.name localname, c.name classname, r.url url 
				from restaurants r 
				join locations l on(r.local_id = l.id)
				join classes c on(r.class_id = c.id)
				""";
		List<RestVO> restList = new ArrayList<>();
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				RestVO rest = makeRest(rs);
				restList.add(rest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return restList;
	}
	
	public List<RestVO> findAllInLocation(int locId) {
		String sql = """
				select r.id id,r.name restname, l.name localname, c.name classname, r.url url 
				from restaurants r 
				join locations l on(r.local_id = l.id)
				join classes c on(r.class_id = c.id)
				where r.local_id="""+locId;
		List<RestVO> restList = new ArrayList<>();
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				RestVO rest = makeRest(rs);
				restList.add(rest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return restList;
	}
	
	public List<RestVO> findAllInClass(int clId) {
		String sql = """
				select r.id id,r.name restname, l.name localname, c.name classname, r.url url 
				from restaurants r 
				join locations l on(r.local_id = l.id)
				join classes c on(r.class_id = c.id)
				where r.local_id="""+clId;
		List<RestVO> restList = new ArrayList<>();
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				RestVO rest = makeRest(rs);
				restList.add(rest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return restList;
	}
	
	
	public RestVO findById(int restid) {
		RestVO restVO = null;
		String sql = "select r.id id,r.name restname, l.name localname, c.name classname, r.url url "
				+ "from restaurants r "
				+ "join locations l on(r.local_id = l.id)"
				+ "join classes c on(r.class_id = c.id)"
				+ "where r.id ="+restid;
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				restVO = makeRest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return restVO;
	}
	
	private RestVO makeRest(ResultSet rs) throws SQLException {
		RestVO rest = new RestVO();
		rest.setId(rs.getInt("id"));
		rest.setName(rs.getString("restname"));
		rest.setLocationName(rs.getString("localname"));
		rest.setClassName(rs.getString("classname"));
		rest.setUrl(rs.getString("url"));
		
		return rest;
	}
	
	public int insertNewRest(RestVO rest, ClassVO cl, LocationVO lo) {
		String query = 
				"""
				insert into restaurants(name,local_id,class_id,url)
				values (?,?,?,?)
				""";
		
		
		conn = MySqlUtil.getConnection();
		try {
			pst = conn.prepareStatement(query);

			
			pst.setString(1,rest.getName());
			pst.setInt(2,lo.getId());
			pst.setInt(3,cl.getId());
			pst.setString(4,rest.getUrl());
			resultCount = pst.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}
	
	public int insertNewRestaurant(RestVO rest) {
		String query = 
				"""
				insert into(
					name,
					local_id,
					class_id,
					url) Restaurants
				values('?',?,?,'?')
				""";
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,rest.getName());
			pst.setInt(2,rest.getLocation_id());
			pst.setInt(3,rest.getClass_id());
			pst.setString(4, rest.getUrl());
			resultCount = pst.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}

	public RestVO findByName(String restName) {
		RestVO restVO = null;
		String sql = "select r.id id,r.name restname, l.name localname, c.name classname, r.url url "
				+ "from restaurants r "
				+ "join locations l on(r.local_id = l.id)"
				+ "join classes c on(r.class_id = c.id)"
				+ "where r.name ='"+restName+"'";
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				restVO = makeRest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return restVO;
	}
	
	public int extractIdByName(String restName) {
		int id=0;
		String sql = "select id from restaurants where name='"+restName+"'";
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				id= rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return id;
	}
	
	public int extractRandomIdInTotal() {
		int id=0;
		String sql = "SELECT id FROM restaurants ORDER BY RAND() limit 1";
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				id= rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return id;
	}

	public int extractRandomIdInLocation(int locId) {
		int id=0;
		String sql = "SELECT id FROM restaurants where local_id = "+locId+" ORDER BY RAND() limit 1";
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				id= rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return id;
	}

	public int extractRandomIdInClass(int clId) {
		int id=0;
		String sql = "SELECT id FROM restaurants where class_id = "+clId+" ORDER BY RAND() limit 1";
		conn = MySqlUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				id= rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySqlUtil.dbDisconnect(rs, st, conn);
		}
		return id;
	}

	

	
}

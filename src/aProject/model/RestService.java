package aProject.model;

import java.util.List;

import aProject.vo.ClassVO;
import aProject.vo.LocationVO;
import aProject.vo.RestVO;

public class RestService {
	RestDAO restDAO = new RestDAO();
	
	public List<RestVO> findAll() {
		return restDAO.findAll();
	}
	
	public RestVO findById(int restid) {
		return restDAO.findById(restid);
	}
	
	public RestVO findByName(String restName) {
		return restDAO.findByName(restName);
	}
	
	public String insertNewRest(RestVO rest, ClassVO cl, LocationVO lo) {
		int result = restDAO.insertNewRest(rest,cl,lo);
		return result>0?"입력 성공":"입력 실패";
	}
	
	public int extractIdByName(String restName) {
		return restDAO.extractIdByName(restName);
	}
	
	public int extractRandomIdInTotal() {
		return restDAO.extractRandomIdInTotal();
	}

	public int extractRandomIdInLocation(int locId) {
		
		return restDAO.extractRandomIdInLocation(locId);
	}

	public int extractRandomIdInClass(int clId) {
		return restDAO.extractRandomIdInClass(clId);
	}
	
	public List<RestVO> findAllInLocation(int locId){
		return restDAO.findAllInLocation(locId);
	}

	public List<RestVO> findAllInClass(int clId) {
		return restDAO.findAllInClass(clId);
	}
}

package aProject.model;

import java.util.ArrayList;
import java.util.List;

import aProject.vo.LocationVO;

public class LocationService {
	
	LocationDAO LocationDAO = new LocationDAO();
	
	public List<LocationVO> findAll(){
		return LocationDAO.findAll();
		
	}
}

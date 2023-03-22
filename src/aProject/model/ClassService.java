package aProject.model;

import java.util.List;

import aProject.vo.ClassVO;

public class ClassService {
	
	ClassDAO classDAO = new ClassDAO();
	
	public List<ClassVO> findAll(){
		
		return classDAO.findAll();
		
	}
}

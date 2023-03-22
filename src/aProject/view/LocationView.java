package aProject.view;

import java.util.List;
import aProject.vo.LocationVO;

public class LocationView {
	
public static void print(List<LocationVO> locationList) {
		
		for(LocationVO lo:locationList) {
			System.out.print(lo);
		}
	}
}

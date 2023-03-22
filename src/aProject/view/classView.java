package aProject.view;

import java.util.List;

import aProject.vo.ClassVO;

public class classView {
	
	public static void print(List<ClassVO> classList) {
		
		for(ClassVO cl:classList) {
			System.out.print(cl);
		}
	}
		
}

package aProject.vo;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor
public class ReviewVO {
	private int id;
	private String content;
	private int score;
	private String username;
	private int user_id;
	private int restaurant_id;
	@Override
	public String toString() {
		
		String stars="";
		for(int i=0;i<score;i++)stars+="★";
		
		
		return ""+stars+"\t | "+username+"\t|"+content;
	}
	
	

}

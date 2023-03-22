package aProject.main;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;
import com.mysql.cj.sasl.ScramSha256SaslClient;

import aProject.login.LoginController;
import aProject.model.ClassService;
import aProject.model.LocationService;
import aProject.model.RestService;
import aProject.model.ReviewService;
import aProject.model.UserService;
import aProject.view.LocationView;
import aProject.view.RestView;
import aProject.view.ReviewView;
import aProject.view.UserView;
import aProject.view.classView;
import aProject.vo.ClassVO;
import aProject.vo.LocationVO;
import aProject.vo.RestVO;
import aProject.vo.ReviewVO;
import aProject.vo.UserVO;
import utils.SHA256;

public class MainApp {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		RestService restService = new RestService();
		ReviewService reviewService = new ReviewService();
		LocationService locationService = new LocationService();
		ClassService classService = new ClassService();
		UserService userService = new UserService();
		int loginId = 0;
		String loginUser = null;


		while (true) {

			if (loginId != 0) System.out.println("로그인 id : " + loginUser);
			else  System.out.println("guest");
			System.out.println("\n----------------------------------------------------------");
			System.out.print("1.식당 리스트 보기|");
			System.out.print("2.리뷰 보기|");
			System.out.print("3.식당 추가|");
			System.out.print("7.렌덤추천|");
			

			if (loginId == 0) {
				System.out.print("9.로그인|");
				System.out.print("0.회원가입|");
			}
			else System.out.print("8.로그아웃|");
			System.out.println("\n----------------------------------------------------------");
			System.out.println();
			System.out.print("선택>");

			String job = scanner.nextLine();
			if (job.equals("exit"))
				break;
			switch (job) {
			case "1":{
				
				System.out.println("1.전체 | 2.음식 | 3.지역");
				System.out.print("선택>");
				int c7select;
				c7select = scanner.nextInt();

				
				switch (c7select) {
				case 1: {
					
					RestView.print(restService.findAll());
					break;
				}
				case 2: {
					classView.print(classService.findAll());
					System.out.println();
					System.out.print("음식 종류 선택>");
					int clId = scanner.nextInt();
					
					//
					RestView.print(restService.findAllInClass(clId));
					break;
				}
				case 3: {
					LocationView.print(locationService.findAll());
					System.out.println();
					System.out.print("지역 선택>");
					int locId = scanner.nextInt();
					
					//
					RestView.print(restService.findAllInLocation(locId));
					break;
				}
				default:
					break;
				}
				break;}
				//수정중인부분

			case "2": {
				System.out.print("식당 이름>");
				String restName = scanner.nextLine();
				RestView.print(restService.findByName(restName));
				
				int restid = restService.extractIdByName(restName);
				ReviewView.print(reviewService.findReviewById(restid));
				
				if(loginId != 0) System.out.print("1.리뷰 쓰기|");
				else break;
				System.out.print("2.나가기|");
				int job2 = scanner.nextInt();
				if(job2==1) {
					ReviewVO review = makeReview(scanner, loginId, restid);
					ReviewView.print(reviewService.insertNewReview(review, loginId));
				}
				
				break;
			}

			case "3": {

				RestVO rest = makeRest(scanner);

				LocationView.print(locationService.findAll());
				System.out.println("지역 선택");
				int locId = scanner.nextInt();
				LocationVO lo = new LocationVO();
				lo.setId(locId);

				classView.print(classService.findAll());
				System.out.println("음식 종류 선택");
				int classId = scanner.nextInt();
				ClassVO cl = new ClassVO();
				cl.setId(classId);

				RestView.print(restService.insertNewRest(rest, cl, lo));

				break;
			}

			case "7":{
				
				System.out.println("1.전체 | 2.음식 | 3.지역");
				System.out.print("선택>");
				int c7select;
				c7select = scanner.nextInt();

				
				switch (c7select) {
				case 1: {
					
					int randVal = restService.extractRandomIdInTotal();
					RestView.print(restService.findById(randVal));
					break;
				}
				case 2: {
					classView.print(classService.findAll());
					System.out.println();
					System.out.print("음식 종류 선택>");
					int clId = scanner.nextInt();
					
					int randVal = restService.extractRandomIdInClass(clId);
					RestView.print(restService.findById(randVal));
					break;
				}
				case 3: {
					LocationView.print(locationService.findAll());
					System.out.println();
					System.out.print("지역 선택>");
					int locId = scanner.nextInt();
					
					int randVal = restService.extractRandomIdInLocation(locId);
					RestView.print(restService.findById(randVal));
					break;
				}
				default:
					break;
				}
				
				

				break;
			}
			case "8":

				loginId = 0;
				loginUser = null;

				break;

			case "9":{
				SHA256 sha256 = new SHA256();
				String cryptogram = null;
				System.out.println("아이디>");
				String username = scanner.next();
				if (LoginController.isUserExists(username)) {
					System.out.println("비밀번호>");
					String inputPw = scanner.next();
					try {
						cryptogram = sha256.encrypt(inputPw);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					if (cryptogram.equals(LoginController.findPassword(username)) && inputPw != null) {
						System.out.println("로그인 성공");
						loginUser = username;
						loginId = LoginController.extractUserId(username);
						break;
					} else
						System.out.println("비밀번호가 올바르지 않다.");
				} else
					System.out.println("존재하지 않는 아이디");

				break;}

			case "0":{

				UserVO user = makeUser(scanner);
				UserView.print(userService.registUser(user));

				break;}

			default:
				break;
			}
		}
		System.out.println("수고하셨습니다.");
	}

	private static UserVO makeUser(Scanner scanner) {
		SHA256 sha256 = new SHA256();
		
		System.out.print("아이디>");
		String id = scanner.next();
		System.out.print("비밀번호>");
		String pw = scanner.next();
		String cryptogram = null;
		try {
			cryptogram = sha256.encrypt(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserVO user = new UserVO(id, cryptogram);

		return user;
	}

	private static ReviewVO makeReview(Scanner scanner, int userid, int restid) {

		
		System.out.print("점수 (0~5)>");
		int score = scanner.nextInt(); scanner.nextLine();
		if (score > 5 || score < 0) {
			System.out.println("잘못된 입력");
			return null;
		}

		System.out.println("리뷰를 작성하세요>");
		String content = scanner.nextLine();

		ReviewVO review = new ReviewVO();

		review.setContent(content);
		review.setRestaurant_id(restid);
		review.setScore(score);
		review.setUser_id(userid);

		return review;

	}

	private static RestVO makeRest(Scanner scanner) {

		System.out.print("추가할 식당의 이름>");
		String restName = scanner.nextLine();

		System.out.println("URL을 입력하세요>");
		String url = scanner.next();

		RestVO rest = new RestVO();
		rest.setName(restName);
		rest.setUrl(url);

		return rest;
	}

}

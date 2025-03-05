package sample;

import java.util.ArrayList;
import java.util.Scanner;

//책임 : 회원을 관리
public class UserAdmin {
		private UserDAO userdao = new UserDAO();
		UserAdmin(){
			Scanner s = new Scanner(System.in);
			boolean f = true;
			while(f) {
				System.out.println("1.등록  2.전체보기");
				int a = s.nextInt();
				s.nextLine();
				switch(a) {
				case 1: add();
					break;
				case 2: allList();
					break;
				default : f=false;
					break;
				}
			}
		}
		private void add() {
			Scanner s = new Scanner(System.in);
			// 사용자 추가
			// 회원의 이름과 아이디를 입력 받는다.
			String name = s.nextLine();
			String id = s.nextLine();
			
			// 회원 객체를 만든다
			UserDTO user = new UserDTO(); //DTO에 넘겨주기 위해 객쳄나듬
			
			// 회원 객체에 입력받는 이름과 아이디를 매핑한다.
			user.setName(name);
			user.setId(id);
			
			// 회원 객체 데이터베이스에 저장한다.
			userdao.insert(user); //UserDAO참조해서 insert메서드에 user인자 호출
			
			
		}
		private void allList() {
			// 전체보기 select * from user1; 말고 여러명이 들어가니 리스트로
			ArrayList<UserDTO> u = userdao.selectAll(); // selectAll ←를 모두 가져와라
			for (UserDTO uu : u) { //
				uu.prt();
			}
		}
		
		
}

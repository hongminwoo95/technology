package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

// 책임 : 데이터베이스에 CRUD작업 수행
// 생성자를 통해서 클래스 로드를 하고 인설트작업을 정의했음 인설트작업에서는 커넥션을 커리문을 만들고 커리문을 연결하고 맵핑하고 실행하는 작업
public class UserDAO {

	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // --> 퍼블릭 커넥션 함수명 주소넘김
	                                       //DBMS주소:포트번호:데이터베이스 이름
	
	UserDAO(){
		init();
	}
	
	private void init() {
		try {
			Class.forName(driver);
			//라이브러리를 로드하는 의미.
			//이 라이브러리는 오라클에 CURD를 위한 클래스들이 있음
			System.out.println("클래스 load 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // ← 예외처리된 위치를 불러주는 기능
		}
	}
	                // ↓ UserDTO의 주소를 u로 받음 (46,47행에서 씀)
	public void insert(UserDTO u) { //유저1이라는 메서드에 튜플을 삽입한다는 뜻 정의
		try {
			//connection 을 가져오는 과정                                    //커넥션 (url)에 연결 오라클 id,pw 입력
			Connection conn = DriverManager.getConnection(url,"system","1111"); // ←연결하기(해당 오라클에 로그인)
			System.out.println("연결성공");
			
			
			String sql = "insert into user1 values (?,?)"; //← 쿼리문정의(테이블의 형태)
			PreparedStatement stmt = conn.prepareStatement(sql); // ← statement 생성 (?,?) 있을때 사용 오라클용 문구
			// ↑ PreparedStatement 참조하여  conn(로그인)-오라클을 참조하여 sql(쿼리문양식)을 stmt변수에 대입
			      // ↑ 미리 쿼리문을 생성하여 진행함
			
			// PreparedStatement    //conn을 참조하여 sql에 넣어라
			stmt.setString(1, u.getName()); // ← stmt 만들어진 쿼리문(테이블형태)에 1번컬럼에 UserDTO u의 name 넣기
			stmt.setString(2, u.getId()); // ← stmt 만들어진 쿼리문(테이블형태)에 2번컬럼에 UserDTO u의 id넣기
			     // ↑ 만들어놓은 쿼리문에 쿼리별 내용을 u참조하여 해당 멤버변수 주소 불러옴
		    	 // ↑ 추가할때 각각추가
		
			// 실행하라는 코드↓ 전송코드
			           // ↓ 쿼리문을 참조하여
			int result = stmt.executeUpdate(); // ← 완성된 오라클구조를 int형 result변수에 넣기
			                    // ↑ executeUpdate 반환타입 int -> 이유 : 쿼리문 1번행,2번행 등 넣기위해
			System.out.println(result+"건 삽입"); 
			
			//연결해제-자원반납
			stmt.close();
			conn.close();
		} catch (Exception e) {
			
		}
	}              //  ↓를 참조하여 메서드생성
	public ArrayList<UserDTO> selectAll(){ // ← 리턴값을 UserDTO 참조 
		ArrayList<UserDTO> ulist = new ArrayList<>();
		try {
			// 연결설정, 쿼리만들기, 매핑, 쿼리 실행, 리턴값 받기
			Connection conn = DriverManager.getConnection(url,"system","1111"); // ←연결하기(로그인)
			System.out.println("연결성공");
			
			
			String sql = "select * from user1"; // ← 쿼리만들기
			                        // ↓ sql을 참조하기 위한 주소만 생성
			Statement stmt = conn.createStatement(); // ←(?,?)없을때 써야함 오라클용 문구
			                       // ↓ sql을 ResultSet할때 rs변수를 사용하여 출력할때마다 생성함
			ResultSet rs = stmt.executeQuery(sql); // ←셀렉트할때 셀렉트는 표형식 이기때문에 ResultSet형식으로 받아야함
			
			while(rs.next()) { // ← rs는 생성된 쿼리문을 순차적으로 
				UserDTO u = new UserDTO(); // UsersDTO주소 객체 생성
				u.setId(rs.getString("id")); // ← "id"는 컬럼명
				// ↑ 참조한 rs(쿼리문)의 getString자료 "id"를 출력함 
				u.setName(rs.getString("name")); // ← 컬럼명
				// ↑ 참조한 rs(쿼리문)의 getString자료 "name"를 출력함 
				ulist.add(u); // ← 가져와서 u를 ulist에 넣어라
		//        ↑ 리스트를 참조하여 u의 주소를 리턴받는다
		}
		} catch (Exception e) {
			
		}
		
		return ulist;
	}

	
	
}

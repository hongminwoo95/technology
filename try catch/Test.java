package teyTest;

public class Test {

	public void abc()  {
		System.out.println("a");
		int[] aa = new int[3];
		try { // 예외 : 실행중 나타는 오류이다 이 오류가 오작동이 되는 것을 방지하기 위해서
			  // try구문을 사용
			  // try 구문은 try catch finally가 있는데
			  // try 구문은 예외를 감지한다. 예외가 발생하면 catch 에서
			  // 처리해준다 예외가 발생하면 아래 코드는 실행하지 않는다
			  // finally구문은 try catch문을 빠져나갈때 출력된다
			  //예외처리방법은 try직접처리와 trows로 떠넘기는방법이있다
			  // try에서 예외가 발생한다면 catch로 예외를 넘기게되고 finally가 실행된다
			System.out.println(aa[2]); //예외가 발생하면 11,12행 하단 try문 코드는 실행을 중지함
			System.out.println(aa[4]); // 배열범위에서 예외를 처리
			Test t=null; // 널포인트에서 예외를 처리
			t.kkk(); // 예외
		}catch (NullPointerException e) {
			System.out.println("널포인트");
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("배열범위");
		}catch (Exception e) { 
			System.out.println("예외");
		} finally {
			System.out.println("try 구문 종료");
		}
		System.out.println("b");
		System.out.println("c");
	}
	
	public void kkk() {
		System.out.println("kkk");
	}
}

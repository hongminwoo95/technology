package sample;
// 책임 :회원 한명
public class UserDTO {

	private String name;
	private String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void prt() {
		System.out.println("id : "+this.id);
		System.out.println("name : "+this.name);
	}
}

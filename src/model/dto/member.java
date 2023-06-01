package model.dto;

public class member {
	private String id;
	private String pw;
	private String name;
	private String address;
	private String tel;
	int point;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "member [id=" + id + ", pw=" + pw + ", name=" + name
				+ ", address=" + address + ", tel=" + tel + ", point=" + point
				+ "]";
	}

	public member(String id, String pw, String name, String address, String tel) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	public member(String id, String pw, String name, String address,
			String tel, int point) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.point = point;
	}

	public member(String id, int point) {
		this.id = id;
		this.point = point;
	}

	public member(String id) {
		this.id = id;
	}

	public member() {

	}

}

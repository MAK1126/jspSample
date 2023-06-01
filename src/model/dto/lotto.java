package model.dto;

public class lotto {

	private String code;
	private String id;
	private String week;
	private String number;
	private int receipt;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getReceipt() {
		return receipt;
	}

	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "구매내역 : 코드[" + code + "], 구매번호[" + number + "]";
	}

	public lotto(String id) {
		this.id = id;
	}
	
	public lotto(String code,int receipt){
		this.code = code;
		this.receipt = receipt;
	}

	public lotto(String id, String number) {
		this.id = id;
		this.number = number;
	}
	
	public lotto(String id, String week, String number, int receipt) {
		this.id = id;
		this.week = week;
		this.number = number;
		this.receipt = receipt;
	}
	
	public lotto(String code, String id, String week, String number) {
		this.code = code;
		this.id = id;
		this.week = week;
		this.number = number;
	}

	public lotto(String code, String id, String week, String number, int receipt) {
		this.code = code;
		this.id = id;
		this.week = week;
		this.number = number;
		this.receipt = receipt;
	}

}

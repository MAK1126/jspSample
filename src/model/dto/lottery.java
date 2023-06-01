package model.dto;

public class lottery {

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
		return "lottery [code=" + code + ", id=" + id + ", week=" + week
				+ ", number=" + number + "]";
	}
	
	public lottery(String id) {
		this.id = id;
	}
	
	public lottery(String code,int receipt){
		this.code = code;
		this.receipt = receipt;
	}

	public lottery(String id, String number) {
		this.id = id;
		this.number = number;
	}
	
	public lottery(String id, String week, String number, int receipt) {
		this.id = id;
		this.week = week;
		this.number = number;
		this.receipt = receipt;
	}
	
	public lottery(String code, String id, String week, String number) {
		this.code = code;
		this.id = id;
		this.week = week;
		this.number = number;
	}

	public lottery(String code, String id, String week, String number, int receipt) {
		this.code = code;
		this.id = id;
		this.week = week;
		this.number = number;
		this.receipt = receipt;
	}
	
}

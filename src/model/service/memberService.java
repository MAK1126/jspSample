package model.service;

import model.dao.memberDAO;
import model.dto.member;

public class memberService {
	memberDAO dao;

	public void setDao(memberDAO dao) {
		this.dao = dao;
	}
	
	//회원가입
	public member checkReg(member member){
		member result = dao.memReg(member);
		return member;
	}
	
	//로그인
	public int checkLogin(String id, String pw) {
		int result = dao.memLogin(id, pw);
		return result;
	}

	//충전
	public member checkCharge(member member){
		member result = dao.pointCharge(member);
		return result;
	}
	
	//차감
	public member checkdeductPoint(member member){
		member result = dao.deductPoint(member);
		return result;
	}
	
	//이름 조회
	public String checkName(member member){
		String result = dao.memNameInquiry(member);
		return result;
	}
	
	//포인트 조회
	public int checkPoint(member member){
		int result = dao.memPointInquiry(member);
		return result;
	}
	
	//아이디 중복확인
	public int checkisDuplicated(String id){
		int result = dao.isDuplicated(id);
		return result;
	}
	
}

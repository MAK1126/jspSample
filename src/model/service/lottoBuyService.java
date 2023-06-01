package model.service;

import java.util.ArrayList;

import model.dao.lottoBuyDAO;
import model.dto.lotto;

public class lottoBuyService {

	lottoBuyDAO dao;
	
	public void setDao(lottoBuyDAO dao) {
		this.dao = dao;
	}
	
	//구매내역 추가
	public lotto checkInsert(lotto lotto){
		lotto result = dao.buyListInsert(lotto);
		return result;
	}
	
	//복권 구매개수
	public int checkCount(String num){
		int result = dao.buyCount(num);
		return result;
	}
	
	//분할
	public String[] checkDivision(String num){
		String[] result = dao.division(num);
		return result;
	}
	
	//구매내역 조회
	public ArrayList<lotto> checkInquiry(lotto lotto){
		ArrayList<lotto> result = dao.buyListInquiry(lotto);
		return result;
	}
	
	//당첨금 수령 여부 업데이트
	public lotto checkUpdate(lotto lotto){
		lotto result = dao.receiptUpdate(lotto);
		return result;
	}
}

package model.service;

import java.util.ArrayList;

import model.dao.lotteryBuyDAO;
import model.dto.lottery;

public class lotteryBuyService {

	lotteryBuyDAO dao;

	public void setDao(lotteryBuyDAO dao) {
		this.dao = dao;
	}

	//구매내역 추가
	public lottery checkInsert(lottery lottery){
		lottery result = dao.buyListInsert(lottery);
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
	public ArrayList<lottery> checkInquiry(lottery lottery){
		ArrayList<lottery> result = dao.buyListInquiry(lottery);
		return result;
	}
	
	//당첨금 수령 여부 업데이트
	public lottery checkUpdate(lottery lottery){
		lottery result = dao.receiptUpdate(lottery);
		return result;
	}
	
}

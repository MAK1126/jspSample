package model.service;

import model.dao.lotteryPrizeDAO;

public class lotteryPrizeService {
	
	lotteryPrizeDAO dao;
	
	public void setDao(lotteryPrizeDAO dao) {
		this.dao = dao;
	}

	public String checkPrizeNum(String week){
		String result = dao.prizeNumInquiry(week);
		return result;
	}
	
	public int[] checkChange(String num){
		int[] result = dao.change(num);
		return result;
	}
	
	public String checkPrize(String num, String week){
		String result = dao.prizeInquiry(num, week);
		return result;
	}
}

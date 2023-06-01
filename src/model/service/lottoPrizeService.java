package model.service;

import model.dao.lottoPrizeDAO;


public class lottoPrizeService {
	
	lottoPrizeDAO dao;
	
	public void setDao(lottoPrizeDAO dao) {
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

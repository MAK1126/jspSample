package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.lottery;

public class lotteryPrizeDAO {
	/* 연금복권 DAO */

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "system";
	String password = "1234";
	Connection con;

	/* DB연결 */
	private void dbcon() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	/* 당첨번호 조회 */
	public String prizeNumInquiry(String week) {
		dbcon();
		String sql = "select prize_number from f_lottery_prize where week=?";
		String prizeNum = "";
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, week);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				prizeNum = rs.getString(1);
			}
			
			rs.close();
			pst.close();
			con.close();

		} catch (SQLException e) {
		}

		return prizeNum;
	}

	/* 변환 String = "1,2,3,4,5,6,7" -> int[] = {1,2,3,4,5,6,7}로 변환 */
	public int[] change(String num) {
		
		String[] chNum = new String[7];
		int[] intNum = new int[7];
				
		chNum = num.split(",");
		
		for (int i = 0; i < intNum.length; i++) {
			intNum[i] = Integer.parseInt(chNum[i]);
		}
		
		return intNum;
	}

	/* 당첨 여부 확인 */
	public String prizeInquiry(String num, String week) {
		dbcon();

		lotteryPrizeDAO lotteryPrizeDao = new lotteryPrizeDAO();
		String prize = "";

		int[] buyNum = lotteryPrizeDao.change(num);

		String prizeNum_ = lotteryPrizeDao.prizeNumInquiry(week);

		int[] prizeNum = lotteryPrizeDao.change(prizeNum_);

		String check = "";

		for (int i = 0; i < prizeNum.length; i++) {
			if (buyNum[i] == prizeNum[i]) {
				check = check + '1';
			} else {
				check = check + '0';
			}
		}

		if (check.equals("1111111")) {
			prize = "1등";
		} else if (check.equals("0111111")) {
			prize = "2등";
		} else if (check.equals("0011111") || check.equals("1011111")) {
			prize = "3등";
		} else if (check.equals("0001111") || check.equals("1001111")) {
			prize = "4등";
		} else if (check.equals("0000111") || check.equals("1000111")) {
			prize = "5등";
		} else if (check.equals("0000011") || check.equals("1000011")) {
			prize = "6등";
		} else if (check.equals("0000001") | check.equals("1000001")) {
			prize = "7등";
		} else {
			prize = "꽝";
		}

		return prize;
	}
}

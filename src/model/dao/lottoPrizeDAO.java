package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class lottoPrizeDAO {

	// 복권 DAO//

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "system";
	String password = "1234";
	Connection con;

	// DB연결//
	private void dbcon() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con != null)
				;
		} catch (ClassNotFoundException | SQLException e) {
		}

	}

	public String prizeNumInquiry(String week) {
		dbcon();
		String sql = "select prize_number from f_lotto_prize where week=?";
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

	// 구매번호 String 을 int 로 변환 하는 로직//
	public int[] change(String num) {

		String[] chNum = new String[6];
		int[] intNum = new int[6];
				
		chNum = num.split(",");
		
		for (int i = 0; i < intNum.length; i++) {
			intNum[i] = Integer.parseInt(chNum[i]);
		}
		
		return intNum;
		
		/*String[] arr = new String[6];
		String a = "";

		int[] arr2 = new int[6];

		int cnt = 0;

		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) != ',') {
				a = a + number.charAt(i);
				arr[cnt] = a;
			} else {
				a = "";
				cnt++;
			}
		}

		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = Integer.parseInt(arr[i]);
		}

		return arr2;*/
	}

	/* 당첨 여부 확인  */
	public String prizeInquiry(String num, String week) {
		dbcon();

		lottoPrizeDAO lottoPrizeDao = new lottoPrizeDAO();
		String prize = "";

		String prize_ = lottoPrizeDao.prizeNumInquiry(week);
		int[] prizeNum = lottoPrizeDao.change(prize_);
		
		int[] buyNum = lottoPrizeDao.change(num);;

		// 맞춘개수
		int cnt = 0;

		for (int i = 0; i < prizeNum.length; i++) {
			for (int j = 0; j < buyNum.length; j++) {
				if (buyNum[j] == prizeNum[i]) {
					cnt++;
				}
			}
		}

		if (cnt == 6) {
			prize = "1등";
		} else if (cnt == 5) {
			prize = "2등";
		} else if (cnt == 4) {
			prize = "3등";
		} else if (cnt == 3) {
			prize = "4등";
		} else if (cnt == 2) {
			prize = "5등";
		} else {
			prize = "꽝";
		}

		return prize;

	}

}

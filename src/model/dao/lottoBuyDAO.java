package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.lotto;

public class lottoBuyDAO {

	// 복권 구매내역 DAO//

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
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}

	// 구매내역추가//
	public lotto buyListInsert(lotto lotto) {

		dbcon();
		String sql = "insert into f_lotto_buy(code,id,week,buy_number,receipt) values('B'||lotto_seq.nextval,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, lotto.getId());
			pst.setString(2, lotto.getWeek());
			pst.setString(3, lotto.getNumber());
			pst.setInt(4, lotto.getReceipt());
			pst.executeUpdate();

			pst.close();
			con.close();

		} catch (SQLException e) {
		}
		
		System.out.println("구매완료");

		return lotto;

	}

	/* 몇개 샀는지 카운팅 */
	public int buyCount(String num) {

		/*int buyCount = 0;
		for (int i = 0; i < num.length(); i++) {
			if (num.charAt(i) == '|') {
				buyCount++;
			}
		}
		return buyCount;*/
		
	    String[] numbers = num.split("\\|"); 
	    return numbers.length;
	}

	/* 분할 */
	public String[] division(String num) {
		/*String buyNum = "";
		String[] buyList = new String[5];

		int cnt = 0;

		for (int i = 0; i < num.length(); i++) {
			if (num.charAt(i) == '|') {
				buyList[cnt] = buyNum;
				buyNum = "";
				cnt++;
			} else if (num.charAt(i) != '|') {
				buyNum = buyNum + num.charAt(i);
			}
		}

		return buyList;*/
		
		String[] buyList = num.split("\\|");
		return buyList;
	}

	// 구매내역 조회//
	public ArrayList<lotto> buyListInquiry(lotto lotto) {
		dbcon();
		String sql = "select * from f_lotto_buy where id=?";

		ArrayList<lotto> BuyList = new ArrayList<>();

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, lotto.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String code = rs.getString(1);
				String id = rs.getString(2);
				String week = rs.getString(3);
				String number = rs.getString(4);
				int receipt = rs.getInt(5);

				lotto lot = new lotto(code, id, week, number, receipt);
				BuyList.add(lot);
			}
			pst.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
		}
		return BuyList;
	}
	
	/* 당첨금 수령 여부 업데이트 */
	public lotto receiptUpdate(lotto lotto){
		dbcon();
		String sql = "update f_lotto_buy set receipt=? where code=?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, lotto.getReceipt());
			pst.setString(2, lotto.getCode());

			pst.executeUpdate();
			pst.close();
			con.close();

		} catch (SQLException e) {
		}
		
		return lotto;
		
	}

}

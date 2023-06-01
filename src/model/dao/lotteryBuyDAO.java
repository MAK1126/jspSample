package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.lottery;

public class lotteryBuyDAO {

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

	/* 구매 내역 추가 */
	public lottery buyListInsert(lottery lottery) {

		dbcon();
		String sql = "insert into f_lottery_buy(code,id,week,buy_number,receipt) values('A'||lottery_seq.nextval,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, lottery.getId());
			pst.setString(2, lottery.getWeek());
			pst.setString(3, lottery.getNumber());
			pst.setInt(4, lottery.getReceipt());
			pst.executeUpdate();

			pst.close();
			con.close();
		} catch (SQLException e) {
		}
		
		return lottery;
	}

	/* 복권 구매 개수 카운팅 */
	public int buyCount(String num) {
	    String[] numbers = num.split("\\|"); 
	    return numbers.length;
	}

	/* 분할 */
	public String[] division(String num) {
		String[] buyList = num.split("\\|");
		return buyList;
	}

	/* 구매 내역 조회 */
	public ArrayList<lottery> buyListInquiry(lottery lottery) {
		dbcon();
		String sql = "select * from f_lottery_buy where id=?";

		ArrayList<lottery> BuyList = new ArrayList<>();

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, lottery.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String code = rs.getString(1);
				String id = rs.getString(2);
				String week = rs.getString(3);
				String number = rs.getString(4);
				int receipt = rs.getInt(5);

				lottery lot = new lottery(code, id, week, number,receipt);
				BuyList.add(lot);
			}

			rs.close();
			pst.close();
			con.close();

		} catch (SQLException e) {
		}
		return BuyList;

	}
	
	/* 당첨금 수령 여부 업데이트 */
	public lottery receiptUpdate(lottery lottery){
		dbcon();
		String sql = "update f_lottery_buy set receipt=? where code=?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, lottery.getReceipt());
			pst.setString(2, lottery.getCode());

			pst.executeUpdate();
			pst.close();
			con.close();

		} catch (SQLException e) {
		}

		return lottery;
		
	}
}

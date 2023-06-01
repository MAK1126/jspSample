package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.member;

public class memberDAO {

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

	/* 회원가입 함수 */
	public member memReg(member member) {

		dbcon();
		String sql = "insert into f_member values(?,?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, member.getId());
			pst.setString(2, member.getPw());
			pst.setString(3, member.getName());
			pst.setString(4, member.getAddress());
			pst.setString(5, member.getTel());
			pst.setInt(6, member.getPoint());
			pst.executeUpdate();

			pst.close();
			con.close();
		} catch (SQLException e) {
		}
		
		return member;

	}

	/* 로그인 함수 */
	public int memLogin(String id, String pw) {

		dbcon();
		String sql = "select * from f_member where id=?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				if (rs.getString(2).equals(pw)) {
					return 1;
				} else if (!rs.getString(2).equals(pw)) {
					return 2;
				}
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
		}

		return 3;
	}

	/* 포인트 충전 */
	public member pointCharge(member member) {
		dbcon();
		String sql = "update f_member set point=? where id=?";

		String sql2 = "select point from f_member where id=?";

		int point = 0;

		try {
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, member.getId());
			ResultSet rs = pst2.executeQuery();

			if (rs.next()) {
				point = rs.getInt(1);
			}

			pst2.close();
			rs.close();

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, point + member.getPoint());
			pst.setString(2, member.getId());

			pst.executeUpdate();
			pst.close();
			con.close();

		} catch (SQLException e) {
		}

		return member;

	}

	/* 구매시 포인트 감소 */
	public member deductPoint(member member) {

		dbcon();

		String sql = "update f_member set point=? where id=?";
		String sql2 = "select point from f_member where id=?";

		int point = 0;

		try {
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, member.getId());
			ResultSet rs = pst2.executeQuery();

			if (rs.next()) {
				point = rs.getInt(1);
			}

			pst2.close();
			rs.close();

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, point - member.getPoint());
			pst.setString(2, member.getId());

			pst.executeUpdate();
			pst.close();
			con.close();

		} catch (SQLException e) {
		}

		return member;
	}

	/* 회원이름 조회 */
	public String memNameInquiry(member member) {
		dbcon();

		String sql = "select name from f_member where id=?";
		String name = "";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, member.getId());
			ResultSet rs = pst.executeQuery();

			
			if(rs.next()){
				name = rs.getString(1);
			}

			pst.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
		}

		return name;
	}

	/* 회원 포인트 조회 */
	public int memPointInquiry(member member) {
		dbcon();

		String sql = "select point from f_member where id=?";

		int point = 0;

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, member.getId());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				point = rs.getInt(1);
			}

			pst.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
		}

		return point;
	}

	//아이디 중복 확인
	public int isDuplicated(String id) {

		dbcon();
		String sql = "select id from f_member";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (id.equals(rs.getString(1))) {
					return 2;
				}
			}

			pst.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
		}
		
		return 1;

	}

}

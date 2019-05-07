package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;

import javax.sql.DataSource;

public class User {
	public Connection conn;
	public Vector<String> bNames;
	public Vector<Integer> bAccountNos;
	public Vector<Integer> ids;
	public Vector<Integer> fromAccounts;
	public Vector<Integer> toAccounts;
	public Vector<Double> amounts;
	public Vector<Timestamp> timestamps;

	public User(DataSource ds) throws Exception {
		conn = ds.getConnection();
	}

	public Integer checkUser(String email, String password) throws Exception {
		String sql = "select ACCOUNT_NO from USERS where EMAIL=? and PASSWORD=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		Integer account_no = null;
		while (rs.next()) {
			account_no = rs.getInt(1);
		}
		return account_no;
	}

	public boolean createUser(String name, String gender, String dob,
			String mobile_no, String email, String password, String address)
			throws Exception {
		String sql = "select * from USERS where EMAIL=? or MOBILE_NO=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, mobile_no);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			System.out.println("Record found");
			return false;
		}
		System.out.println("No records found");
		sql = "insert into USERS(NAME,GENDER,DOB,MOBILE_NO,EMAIL,PASSWORD,ADDRESS)"
				+ " values(?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, gender);
		stmt.setString(3, dob);
		stmt.setString(4, mobile_no);
		stmt.setString(5, email);
		stmt.setString(6, password);
		stmt.setString(7, address);
		int i = stmt.executeUpdate();
		boolean flag = false;
		if (i == 1) {
			flag = true;
		}
		return flag;
	}

	public double fetchBalance(Integer account_no) throws Exception {
		String sql = "select BALANCE from USERS where ACCOUNT_NO=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, account_no);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		double balance = rs.getDouble(1);
		return balance;
	}

	public void fetchTransactions(Integer account_no) throws Exception {
		String sql = "select * from TRANSACTIONS "
				+ "where FROM_ACCOUNT=? or TO_ACCOUNT=?  order by timestamp desc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, account_no);
		stmt.setInt(2, account_no);
		ResultSet rs = stmt.executeQuery();
		ids = new Vector<Integer>();
		fromAccounts = new Vector<Integer>();
		toAccounts = new Vector<Integer>();
		amounts = new Vector<Double>();
		timestamps = new Vector<Timestamp>();
		while (rs.next()) {
			ids.add(rs.getInt(1));
			fromAccounts.add(rs.getInt(2));
			toAccounts.add(rs.getInt(3));
			amounts.add(rs.getDouble(4));
			timestamps.add(rs.getTimestamp(5));
		}
	}

	public boolean isAccountExist(Integer account_no) throws Exception {
		String sql = "select ACCOUNT_NO from USERS where ACCOUNT_NO=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, account_no);
		ResultSet rs = stmt.executeQuery();
		boolean flag = false;
		while (rs.next()) {
			flag = true;
		}
		return flag;
	}

	public boolean isMoneyEnough(Integer account_no, Double amount)
			throws Exception {
		String sql = "select ACCOUNT_NO from USERS "
				+ "where ACCOUNT_NO=? and BALANCE>=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, account_no);
		stmt.setDouble(2, amount);
		ResultSet rs = stmt.executeQuery();
		boolean flag = false;
		while (rs.next()) {
			flag = true;
		}
		return flag;
	}

	public void insertTransaction(Integer from_account, Integer to_account,
			Double amount) throws Exception {
		String sql = "insert into TRANSACTIONS(FROM_ACCOUNT,TO_ACCOUNT,AMOUNT) "
				+ "values(?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, from_account);
		stmt.setInt(2, to_account);
		stmt.setDouble(3, amount);
		int count = stmt.executeUpdate();
		if (count == 1) {
			System.out.println("Transaction added");
		}
	}

	public void debit(Integer account_no, Double amount) throws Exception {
		String sql = "update USERS set BALANCE=BALANCE-? "
				+ "where ACCOUNT_NO=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setDouble(1, amount);
		stmt.setInt(2, account_no);
		int count = stmt.executeUpdate();
		if (count == 1) {
			System.out.println("Debit Success");
		}
	}

	public void credit(Integer account_no, Double amount) throws Exception {
		String sql = "update USERS set BALANCE=BALANCE+? "
				+ "where ACCOUNT_NO=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setDouble(1, amount);
		stmt.setInt(2, account_no);
		int count = stmt.executeUpdate();
		if (count == 1) {
			System.out.println("Credit Success");
		}
	}

	public boolean transfer(Integer from_account, Integer to_account,
			Double amount) throws Exception {
		
		return true;
	}

	public boolean isBenifitaryExist(Integer accountNo, Integer bAccountNo)
			throws Exception {
		String sql = "select ACCOUNT_NO from BENIFITARY_ACCOUNTS "
				+ "where USER_ID=? and ACCOUNT_NO=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, accountNo);
		stmt.setInt(2, bAccountNo);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return false;
		}
		return true;
	}

	public boolean addBenifitary(Integer accountNo, String name,
			Integer bAccountNo) throws Exception {
		String sql = "insert into BENIFITARY_ACCOUNTS(USER_ID,NAME,ACCOUNT_NO)"
				+ "values(?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, accountNo);
		stmt.setString(2, name);
		stmt.setInt(3, bAccountNo);
		int count = stmt.executeUpdate();
		if (count == 1) {
			System.out.println("Benifitary Added");
			return true;
		}
		return false;
	}

	public void fetchBenifitaryAccounts(Integer accountNo) throws Exception {
		String sql = "select NAME,ACCOUNT_NO from BENIFITARY_ACCOUNTS where USER_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, accountNo);
		ResultSet rs = stmt.executeQuery();
		bNames = new Vector<String>();
		bAccountNos = new Vector<Integer>();
		while (rs.next()) {
			bNames.add(rs.getString(1));
			bAccountNos.add(rs.getInt(2));
		}
	}

	public void close() throws Exception {
		conn.close();
	}
}

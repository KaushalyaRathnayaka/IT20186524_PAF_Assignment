package com;

import java.sql.*;

public class Billing {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readBillings() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table class='table table-hover'><tr><th>Billing Type</th><th>Account Number</th><th>Units</th>"
					+ "<th>Price Per Unit</th><th>Date</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from billing";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String billingtype = rs.getString("billingtype");
				String account_number = rs.getString("account_number");
				String totalprice = rs.getString("totalprice");
				String remark = rs.getString("remark");
				String date = rs.getString("date");
				// Add into the html table
				output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + id
						+ "'>" + billingtype + "</td>";
				output += "<td>" + account_number + "</td>";
				output += "<td>" + totalprice + "</td>";
				output += "<td>" + remark + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ id + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Billings.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertBillings(String name, String account_number, String totalprice, String remark,
			String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into billing(`id`,`billingtype`,`account_number`,`totalprice`,`remark`,`date`)"
					+ "values(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			System.out.println(date);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, account_number);
			preparedStmt.setString(4, totalprice);
			preparedStmt.setString(5, remark);
			preparedStmt.setString(6, date);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse = readBillings();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Users.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBillings(String id,String billingtype, String account_number, String totalprice, String remark,
			String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE billing SET billingtype=?,account_number=?,totalprice=?,remark=?,date=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
		
			preparedStmt.setString(1, billingtype);
			preparedStmt.setString(2, account_number);
			preparedStmt.setString(3, totalprice);
			preparedStmt.setString(4, remark);
			preparedStmt.setString(5, date);
			preparedStmt.setInt(6, Integer.parseInt(id));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse =readBillings();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBillings(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from billing where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse = readBillings();
			output = "{\"status\":\"success\", \"data\": \"" +newUse + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readBillings2() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table class='table table-hover'><tr><th>Billing Type</th><th>Account Number</th><th>Total Price</th>"
					+ "<th>Remark</th><th>Date</th><th>Remove</th></tr>";

			String query = "select * from billing";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String billingtype = rs.getString("billingtype");
				String account_number = rs.getString("account_number");
				String totalprice = rs.getString("totalprice");
				String remark = rs.getString("remark");
				String date = rs.getString("date");
				// Add into the html table
				output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + id
						+ "'>" + billingtype + "</td>";
				output += "<td>" + account_number + "</td>";
				output += "<td>" + totalprice + "</td>";
				output += "<td>" + remark + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				output += "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ id + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Billings.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
package com.karo.shop;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DBSocket {

	protected String database = null;

	Connection con = null;

	public DBSocket(String database) {
		this.database = database;
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			config.setEncoding(SQLiteConfig.Encoding.UTF8);
			con = DriverManager.getConnection("jdbc:sqlite:" + database, config.toProperties());
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.commit();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Returns a result set of a given query If an exception is thrown it should
	 * return null value
	 * 
	 * @param statement - Statement what must be executed on the table
	 * @return ResultSet of the statement
	 */
	public synchronized ResultSet execQuery(String statement) {
		try {
			return con.createStatement().executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * General database meta information
	 * 
	 * @return DatabaseMetaData
	 */
	public synchronized DatabaseMetaData getMeta() {
		try {
			return con.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets all tables in a given database
	 * 
	 * @return ResultSet
	 */
	public synchronized ResultSet getTables() {
		ResultSet rs = null;
		try {
			rs = getMeta().getTables(null, null, null, new String[] { "TABLE" });
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * Retrieves columns' structure in a specified table
	 * 
	 * @param tableName - name of the table
	 * @return ResultSet
	 */
	public synchronized ResultSet getColumns(String tableName) {
		ResultSet rs = null;
		try {
			rs = getMeta().getColumns(null, null, tableName, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Gets all records in a given table
	 * 
	 * @param tableName - name of the table which you want to get records from
	 * @return ResultSet
	 */
	public synchronized ResultSet getRecords(String tableName) {
		return execQuery("SELECT * from " + tableName);
	}

	public static void printResult(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int noOfCols = meta.getColumnCount();
		for (int i = 1; i <= (noOfCols * 23) - (noOfCols - 1); i++) {
			System.out.print(i == 1 || (i - 1) % 22 == 0 ? "+" : "-");
		}
		System.out.println();
		for (int i = 1; i <= noOfCols; i++) {
			System.out.print(String.format("| %-20s", meta.getColumnLabel(i)));
		}
		System.out.println("|");
		// OMFG IT ACTUALLY WORKS GENERICALLY
		for (int i = 1; i <= (noOfCols * 23) - (noOfCols - 1); i++) {
			System.out.print(i == 1 || (i - 1) % 22 == 0 ? "+" : "-");
		}
		System.out.println();
		int count = 0;
		while (rs.next()) {
			count++;
			for (int i = 1; i <= noOfCols; i++) {
				System.out.print((i == 1 ? "|" : "") + String.format(" %-20s|", rs.getObject(i)));
			}
			System.out.println();
		}
		for (int i = 1; i <= (noOfCols * 23) - (noOfCols - 1); i++) {
			System.out.print(i == 1 || (i - 1) % 22 == 0 ? "+" : "-");
		}
		System.out.println("\nResult(-s) in the set " + count + "\n");

	}
}

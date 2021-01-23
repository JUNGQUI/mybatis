package com.example.jk.mybatis.chapter01.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public SQLMapper () {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected Connection connect() throws SQLException {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE"
						, "mybatis"
						, "mybatis$");
			}

			return connection;
		} catch (SQLException e) {
			throw e;
		}
	}

	protected void release() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {

			}
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {

			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}
	}
}

package com.example.jk.mybatis.chapter01.step3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SQLMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private String configurationResource = "resources/jdbc/config-jdbc.properties";
	private Properties configuration = new Properties();

	private String sqlResource = "resources/jdbc/sql.properties";
	private Properties sql = new Properties();

	private void configurationAsProperties() throws IOException {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		InputStream inputStream = contextClassLoader.getResourceAsStream(configurationResource);

		try {
			configuration.load(inputStream);
		} catch (IOException e) {
			throw e;
		} finally {
			inputStream.close();
		}
	}

	private void sqlAsProperties() throws IOException {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		InputStream inputStream = contextClassLoader.getResourceAsStream(sqlResource);

		try {
			sql.load(inputStream);
		} catch (IOException e) {
			throw e;
		} finally {
			inputStream.close();
		}
	}

	protected String sqlById(String sqlId) {
		return sql.getProperty(sqlId);
	}

	public SQLMapper () {
		try {
			configurationAsProperties();
			sqlAsProperties();
			Class.forName(configuration.getProperty("driver"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Connection connect() throws SQLException {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(
						configuration.getProperty("url")
						, configuration.getProperty("username")
						, configuration.getProperty("password"));
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

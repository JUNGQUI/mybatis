package com.example.jk.mybatis.chapter01.step2;

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

	private String configurationResource = "resource/jdbc/config-jdbc.properties";
	private Properties configuration = new Properties();

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

	public SQLMapper () {
		try {
			configurationAsProperties();
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

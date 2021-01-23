package com.example.jk.mybatis.chapter01.step0;

import com.example.jk.mybatis.chapter01.domain.Shop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Application {
	public Shop view(List<Object> parameters) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Shop shop = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
					, "mybatis"
					, "mybatis$");

			preparedStatement = connection.prepareStatement(
					"SELECT SHOP_NO, SHOP_NAME, SHOPE_LOCATION, SHOPE_STATUS "
							+ "FROM SHOP "
							+ "WHERE SHOP_NO = ? AND AHOP_STATUS = ?");

			preparedStatement.setInt(1, (Integer) parameters.get(0));
			preparedStatement.setString(2, String.valueOf(parameters.get(1)));

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				shop = new Shop();

				shop.setShopNo(resultSet.getInt("SHOP_NO"));
				shop.setShopName(resultSet.getString("SHOP_NAME"));
				shop.setShopLocation(resultSet.getString(("SHOP_LOCATION")));
				shop.setShopStatus(resultSet.getString("SHOP_STATUS"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
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

		return shop;
	}
}

package com.example.jk.mybatis.step1;

import com.example.jk.mybatis.domain.Shop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModifiedApplication extends SQLMapper {
	public Shop view(List<Object> parameters) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Shop shop = null;

		try {
			preparedStatement = connect().prepareStatement(
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
			release();
		}

		return shop;
	}
}

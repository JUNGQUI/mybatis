package com.example.jk.mybatis.step0;

import com.example.jk.mybatis.domain.Shop;
import java.util.ArrayList;
import java.util.List;

public class Executor {
	public static void main(String[] args) {
		try {
			List<Object> parameters = new ArrayList<>();
			parameters.add(1);
			parameters.add("Y");

			Application application = new Application();
			Shop shop = application.view(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.example.jk.mybatis.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class Shop implements Serializable {
	private int shopNo;
	private String shopName;
	private String shopLocation;
	private String shopStatus;
}

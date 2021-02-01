package com.example.jk.mybatis.chapter03.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {
  private int shopNo;
  private String shopName;
  private String shopLocation;
  private String shopStatus;
}

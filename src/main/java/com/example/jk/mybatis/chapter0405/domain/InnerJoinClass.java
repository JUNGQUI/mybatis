package com.example.jk.mybatis.chapter0405.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerJoinClass {
  private String innerJoinId;
  private String innerJoinName;
}

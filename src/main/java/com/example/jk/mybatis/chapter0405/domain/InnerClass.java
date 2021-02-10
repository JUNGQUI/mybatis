package com.example.jk.mybatis.chapter0405.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerClass {
  private String innerId;
  private String innerName;

  private InnerJoinClass innerJoinClass;
}

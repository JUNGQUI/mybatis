package com.example.jk.mybatis.chapter0405;

import lombok.Data;

@Data
public class WrapperClassFilter {
  private String wrapperId;
  private String innerId;
  private String innerJoinId;

  private String wrapperName;
  private String innerName;
  private String innerJoinName;
}

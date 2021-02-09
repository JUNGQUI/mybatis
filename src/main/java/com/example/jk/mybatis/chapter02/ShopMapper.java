package com.example.jk.mybatis.chapter02;

import com.example.jk.mybatis.chapter03.domain.Shop;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopMapper {
  Map list();
  void insert(Shop shop);
}

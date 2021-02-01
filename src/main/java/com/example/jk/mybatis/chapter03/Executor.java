package com.example.jk.mybatis.chapter03;

import com.example.jk.mybatis.chapter03.domain.Shop;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Executor {
  private static SqlSessionFactory sqlSessionFactory;

  static {
    try {
      String resource = "mybatis/config-mybatis.xml";
      Reader reader = Resources.getResourceAsReader(resource);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public static void main(String[] args) {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    try {
      Shop shop = new Shop();
      shop.setShopName("JUNGKI - Bugger Story");
      shop.setShopLocation("pangyo, AlphaDom 6-2");
      shop.setShopStatus("Y");

      sqlSession.insert("org.mybatis.persistence.ShopMapper.insert", shop);

      sqlSession.commit();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      sqlSession.rollback();
    } finally {
      sqlSession.close();
    }

  }
}

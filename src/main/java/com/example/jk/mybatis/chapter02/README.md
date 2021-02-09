### mybatis, 기본

- mybatis config
- mybatis mapper
  
````xml
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.jk.mybatis.chapter02.ShopMapper">
<!-- 이름이 'list' 이면서 반환 타입이 Map 인 query, 결과가 자연스럽게 Map 으로 변환되서 전달된다.  -->
  <select id="list" resultType="java.util.Map">
    SELECT SHOP_NO, SHOP_NAME, SHOP_LOCATION, SHOP_STATUS
    FROM private_schema.SHOP
  </select>
<!-- 이름이 'insert' 인 함수와 mapping 된다. 그와 동시에 전달받는 parameter 는 chapter03 의 Shop 을 전달한다. -->
  <insert id="insert" parameterType="com.example.jk.mybatis.chapter03.domain.Shop">
    INSERT INTO private_schema.SHOP (SHOP_NAME, SHOP_LOCATION, SHOP_STATUS)
<!-- 위 parameter 를 고려하여 내부에 해당 이름의 column 을 통해 VALUES 에 mapping -->
    VALUES (#{shopName}, #{shopLocation}, #{shopStatus})
  </insert>
</mapper>
````

- Mapper Interface
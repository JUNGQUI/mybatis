- type alias

`약어` 를 지정함으로써 이후 xml 내에서 해당 지정 값을 alias 로 간편하게 호출이 가능해진다.

```xml
<typeAliases>
  <typeAlias xmlns="http://mybatis.org/schema/mybatis-mapper"
    alias="shop" type="com.example.jk.mybatis.chapter03.domain.Shop"
  />
</typeAliases>
```

- mapper

query 와 실제 class 사이 연관 관계를 지어주는 (mapper) tag 로, query 결과를 해당 mapper class 로 전달함으로써
application layer(java 단) 에서 해당 class 를 통해 query 호출이 가능하게 된다.

이를 통해 java 개발 시 쿼리를 method 하나의 단위로 사용이 가능하며 mapper 내 parameter, result 등을 통해 자동 mapping
이 되어 return 값등을 class 로 반환 받아 사용이 가능하다.

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.jk.mybatis.chapter02.ShopMapper">
  <select id="list">
    <!-- SOME_QUERY... -->
  </select>
</mapper>
```

```xml
<insert id="insert" parameterType="com.example.jk.mybatis.chapter03.domain.Shop"
  useGeneratedKeys="true" keyProperty="shopNo">
    INSERT INTO private_schema.SHOP (SHOP_NAME, SHOP_LOCATION, SHOP_STATUS)
    VALUES (#{shopName}, #{shopLocation}, #{shopStatus})
</insert>
```

key-autogenerate 와 useGeneratedKeys, keyProperty 를 사용한다면 insert 당시 key 값이 자동 생성된 후에 저장을 요청한
Object 의 keyProperty 에 명시된 property 에 넣어서 반환해주기에, key 값을 사용하는 부분에서 query 적 이점을 가져갈 수 있다.

- resultMap

아래와 같은 type 이 있다고 할 때 타입을 지정하지 않고 그대로 SELECT 를 실행할 경우

```java
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
```

```xml
<select id="list">
  SELECT SHOP_NO as "shopNo"
  , SHOP_NAME as "shopName"
  , SHOP_LOCATION as "shopLocation"
  , SHOP_STATUS as "shopStatus"
  FROM private_schema.SHOP
</select>
```

이와 같이 alias 를 통해 해당 class 의 property 와 동일하게 맞춰줘야 하기에 번거로움이 있다.

이러한 쿼리가 하나라면 괜찮지만, n 개 일 경우 그 모든 query 에 대해 alias 로 지정해줘야 한다.

하지만 `resultMap` 을 사용한다면 매우 간단해진다.

```xml

<mapper namespace="com.example.jk.mybatis.chapter02.ShopMapper">
  <select id="list" resultMap="shopResultMap">
    SELECT SHOP_NO, SHOP_NAME, SHOP_LOCATION, SHOP_STATUS
    FROM private_schema.SHOP
  </select>

  <resultMap id="shopResultMap" type="com.example.jk.mybatis.chapter03.domain.Shop">
    <id column="SHOP_NO" property="shopNo"/>
    <result column="SHOP_NAME" property="shopName"/>
    <result column="SHOP_LOCATION" property="shopLocation"/>
    <result column="SHOP_STATUS" property="shopStatus"/>
  </resultMap>
</mapper>
```

이와 같이 xml 내에 해당 class 와 mapping 되는 resultMap 을 선언하고 이를 column 과 같이 정의해준 후 select 에서 사용 시 resultMap 을 통해
일반 쿼리를 class 에 mapping 할 수 있다.
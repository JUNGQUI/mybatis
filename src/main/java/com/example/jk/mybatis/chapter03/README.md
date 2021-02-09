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
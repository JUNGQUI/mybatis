xml 심화

- SELECT

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.jk.mybatis.chapter0405.AdvanceMapper">

  <resultMap id="InnerJoinClass" type="com.example.jk.mybatis.chapter0405.domain.InnerJoinClass">
    <id property="innerJoinId" column="inner_join_id" />
    <result property="innerJoinName" column="inner_join_name" />
  </resultMap>

  <resultMap id="InnerClass" type="com.example.jk.mybatis.chapter0405.domain.InnerClass">

    <id property="innerId" column="inner_id" />
    <result property="innerName" column="inner_name" />

    <association property="innerJoinClass" resultMap="InnerJoinClass" />
  </resultMap>

  <resultMap id="WrapperClass" type="com.example.jk.mybatis.chapter0405.domain.WrapperClass">
    <id property="id" column="wrapper_id" />
    <result property="name" column="wrapper_name" />

    <collection property="innerClassList" resultMap="InnerClass"/>
  </resultMap>

  <select id="findByEmpIdAndPeriod" resultMap="DailyWorkingTime">
    ...
  </select>

  
</mapper>
```

이와 같이 resultMap 을 지정하고 association, collection 을 이용해서 심화된 (join) 검색이 가능하다.
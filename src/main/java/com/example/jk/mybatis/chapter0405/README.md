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

  <select id="findByEmpIdAndPeriod" resultMap="WrapperClass">
    ...
  </select>

  
</mapper>
```

이와 같이 resultMap 을 지정하고 association, collection 을 이용해서 심화된 (join) 검색이 가능하다.

실제로 해당 mapper 를 xml 로 구성을 해두면, where 로 조건을 입력하는 부분이 있다.

parameter 가 1개만 있다면 별도의 변수명 설정 없이 xml 에서 자동으로 해당 데이터를 변수로 인식해 파싱 후 사용하지만,
같은 타입의 여러가지 변수를 동시에 사용한다면 파싱 당시 어떤 값에 어떤 값을 대입해야 할지 알아서 대응이 안된다.

```xml
<select id="findAllByWrapperId" resultMap="WrapperClass">
    SELECT *
    FROM WRAPPER_CLASS
    WHERE WRAPPER_ID = #{wrapperId}
</select>
```

```java
@Repository
public interface AdvanceMapper {
  public List<WrapperClass> findAllByWrapperId(@Param("wrapperId") String id);
}
```

위 함수는 실제로 1개만 사용했지만 `@Param` 을 이용해서 Mapper class 에서 wrapperId 라고 지정을 해주었고, xml 에서는 wrapperId 라는
변수를 통해 값을 주입 받고 있다.

```java
@Data
public class WrapperClassFilter {
  private String wrapperId;
  private String innerId;
  private String innerJoinId;

  private String wrapperName;
  private String innerName;
  private String innerJoinName;
}

// ...
public List<WrapperClass> findAll(WrapperClassFilter wrapperClassFilter);
```

위의 경우 여러 가지 조건을 하나의 Object 로 만들어서 일괄로 적용하는 부분이다. 먼저 filter 를 보면 실제로 where 절에 사용될 조건들이
명시되어 있다.

이 명시들을 이용해서 해당 조건을 적용 할지 안할지, 적용을 한다면 어떻게 적용을 할건지 (like, equal, between 등) 를 xml 에 작성한다.

```xml
<select id="findAll" resultMap="WrapperClass" parameterType="com.example.jk.mybatis.chapter0405.WrapperClassFilter">
  SELECT *
  FROM	WRAPPER_CLASS
  <include refid="WrapperClassWhere" />
</select>

<sql id="WrapperClassWhere">
  <where>
    <if test="null != wrapperId and '' != wrapperId">
      AND WRAPPER_ID = #{wrapperId}
    </if>
    <if test="null != wrapperId and '' != wrapperId">
      AND INNER_ID = #{innerId}
    </if>
    <if test="null != wrapperId and '' != wrapperId">
      AND INNER_JOIN_ID = #{innerJoinId}
    </if>

    <if test="null != wrapperName and '' != wrapperName">
      AND WRAPPER_NAME LIKE CONCAT('%',#{wrapperName},'%')
    </if>
    <if test="null != innerName and '' != innerName">
      AND INNER_NAME LIKE CONCAT('%',#{innerName},'%')
    </if>
    <if test="null != innerJoinName and '' != innerJoinName">
      AND INNER_JOIN_NAME LIKE CONCAT('%',#{innerJoinName},'%')
    </if>
  </where>
</sql>
```

앞서 말했듯이 <sql>내의 <where> 태그를 통해 조건이 있는지 확인해보고 name 의 경우 단순 equal 이 아닌 like 를 진행하는데
이렇게 적용 할 경우 CONCAT 으로 %를 앞뒤로 붙여주고 ID 값의 경우 유니크하다는 전제 하에 equal 을 통해 where 를 가져온다.

당연하게도 안에 AND 조건으로 취합하기에 모든 조건을 만족하는 것을 찾아낼 것이고 특정 조건이 없을 경우엔 아예 해당 조건이 활성화 되지 않기에
자연스럽게 query 가 실행 될 것이다.

이와 같이 조건에 대해 지정을 해서 사용하면 많은 변수를 사용할 수 있다.
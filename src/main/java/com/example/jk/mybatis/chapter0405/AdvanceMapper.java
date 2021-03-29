package com.example.jk.mybatis.chapter0405;

import com.example.jk.mybatis.chapter0405.domain.WrapperClass;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceMapper {
  public List<WrapperClass> findAllByWrapperId(@Param("wrapperId") String id);
  public List<WrapperClass> findAll(WrapperClassFilter wrapperClassFilter);
}

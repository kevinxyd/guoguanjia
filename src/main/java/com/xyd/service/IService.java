package com.xyd.service;

import com.xyd.entity.AppVersion;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface IService<T> {
    int deleteByPrimaryKey(Object o);

    int delete(T T);

    int insert(T T);

    int insertSelective(T T);

    boolean existsWithPrimaryKey(Object o);

    List<T> selectAll();

    T selectByPrimaryKey(Object o);

    int selectCount(T T);

    List<T> select(T T);

    T selectOne(T T);

    int updateByPrimaryKey(T T);

    int updateByPrimaryKeySelective(T T);

    int deleteByExample(Object o);

    List<T> selectByExample(Object o);

    int selectCountByExample(Object o);

    T selectOneByExample(Object o);

    int updateByExample(T T, Object o);

    int updateByExampleSelective(T T, Object o);

    List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    List<T> selectByRowBounds(T appVersion, RowBounds rowBounds);
}

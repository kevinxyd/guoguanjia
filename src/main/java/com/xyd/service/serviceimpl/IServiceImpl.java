package com.xyd.service.serviceimpl;

import com.xyd.service.IService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/18:27
 * @Description:
 */
public class IServiceImpl<T> implements IService<T> {

    @Autowired
    Mapper<T> mapper;

    @Override
    public int deleteByPrimaryKey(Object o) {
        return mapper.deleteByPrimaryKey(o);
    }

    @Override
    public int delete(T T) {
        return mapper.delete(T);
    }

    @Override
    public int insert(T T) {
        return mapper.delete(T);
    }

    @Override
    public int insertSelective(T T) {
        return mapper.insertSelective(T);
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return mapper.existsWithPrimaryKey(o);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectByPrimaryKey(Object o) {
        return mapper.selectByPrimaryKey(o);
    }

    @Override
    public int selectCount(T T) {
        return mapper.selectCount(T);
    }

    @Override
    public List<T> select(T T) {
        return mapper.select(T);
    }

    @Override
    public T selectOne(T T) {
        return selectOne(T);
    }

    @Override
    public int updateByPrimaryKey(T T) {
        return mapper.updateByPrimaryKey(T);
    }

    @Override
    public int updateByPrimaryKeySelective(T T) {
        return mapper.updateByPrimaryKeySelective(T);
    }

    @Override
    public int deleteByExample(Object o) {
        return mapper.deleteByExample(o);
    }

    @Override
    public List<T> selectByExample(Object o) {
        return mapper.selectByExample(o);
    }

    @Override
    public int selectCountByExample(Object o) {
        return mapper.selectCountByExample(o);
    }

    @Override
    public T selectOneByExample(Object o) {
        return null;
    }
    @Override
    public int updateByExample(T record, Object example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByExampleSelective(T record, Object example) {
        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public List<T> selectByRowBounds(T appVersion, RowBounds rowBounds) {
        return mapper.selectByRowBounds(appVersion,rowBounds);
    }

}
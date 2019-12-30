package com.xyd.mapper.provider;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/30/16:23
 * @Description:
 */
public class StatuteProvider {


    public String selectByCondition(Map<String,Object> condition){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from  " +
                " statute s " +
                "where " +
                " s.del_flag=0 ");
        if (!StringUtils.isEmpty(condition.get("type"))) {
            stringBuilder.append(" and s.type = #{type}");
        }
        return stringBuilder.toString();
    }
}
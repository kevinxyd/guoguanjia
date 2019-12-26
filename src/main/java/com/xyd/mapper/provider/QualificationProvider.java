package com.xyd.mapper.provider;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/25/15:27
 * @Description:
 */
public class QualificationProvider {


    public String selectByCondition(Map<String,Object> params) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select qu.*,uu.name uploadUser,cu.name checkUser from  qualification qu LEFT JOIN " +
                " sys_user uu on  qu.upload_user_id=uu.id LEFT JOIN  sys_user cu on  qu.check_user_id=cu.id where  qu.del_flag=0 ");

        if (params.containsKey("type")&& !StringUtils.isEmpty(params.get("type"))){
            stringBuilder.append(" and type = #{type}");
        }
        if (params.containsKey("check")&& !StringUtils.isEmpty(params.get("check"))){
            stringBuilder.append(" and `check` = #{check}");
        }
        if (params.containsKey("start")&& !StringUtils.isEmpty(params.get("start"))){
            stringBuilder.append(" and qu.create_date > #{start}");
        }
        if (params.containsKey("end")&& !StringUtils.isEmpty(params.get("end"))){
            stringBuilder.append(" and qu.create_date < #{end}");
        }
        return stringBuilder.toString();
    }

}
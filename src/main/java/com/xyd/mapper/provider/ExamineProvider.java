package com.xyd.mapper.provider;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/26/14:14
 * @Description:
 */
public class ExamineProvider {

    public String selectByCondition(Map<String,Object> condition){
        StringBuilder sb = new StringBuilder();
        sb.append("select ex.*,su.name userName,so.name officeName from  " +
                " examine ex , sys_user su , sys_office so  " +
                "where " +
                " ex.del_flag=0" +
                " and " +
                " ex.examine_user_id=su.id " +
                "and " +
                " su.office_id=so.id ");
        if(condition.containsKey("officeId")&&!StringUtils.isEmpty(condition.get("officeId"))){
            sb.append(" and so.id=#{officeId} ");
        }
        if(condition.containsKey("userName")&&!StringUtils.isEmpty(condition.get("userName"))){
            sb.append(" and su.name like concat('%',#{userName},'%') ");
        }
        if(condition.containsKey("type")&&!StringUtils.isEmpty(condition.get("type"))){
            sb.append(" and ex.type=#{type} ");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
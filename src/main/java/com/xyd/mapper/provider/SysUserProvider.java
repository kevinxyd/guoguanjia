package com.xyd.mapper.provider;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2020/01/06/17:51
 * @Description:
 */
public class SysUserProvider {
    public String selectByCondition(Map<String,Object> condition){
        StringBuilder sb = new StringBuilder();
        sb.append("select su.*,GROUP_CONCAT(sr.name ) roleName,so.`name` officeName from sys_user su left join sys_user_role sur on su.id = sur.user_id left join sys_role sr  on sr.id = sur.role_id left join sys_office so on su.office_id = so.id GROUP BY su.id ");
        /*if(condition.containsKey("officeId")&&!StringUtils.isEmpty(condition.get("officeId"))){
            sb.append(" and so.id=#{officeId} ");
        }*/
        if(condition.containsKey("userName")&&!StringUtils.isEmpty(condition.get("userName"))){
            sb.append(" and su.name like concat('%',#{userName},'%') ");
        }
        if(condition.containsKey("roleName")&&!StringUtils.isEmpty(condition.get("roleName"))){
            sb.append(" and sr.name=#{roleName} ");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
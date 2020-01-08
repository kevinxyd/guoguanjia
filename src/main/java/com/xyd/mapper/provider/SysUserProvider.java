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
        if(condition.containsKey("name")&&!StringUtils.isEmpty(condition.get("name")) ||
           condition.containsKey("roleName")&&!StringUtils.isEmpty(condition.get("roleName")) ||
           condition.containsKey("mobile")&&!StringUtils.isEmpty(condition.get("mobile"))){
            sb.append("select su.*,GROUP_CONCAT(sr.name ) roleName,so.`name` officeName " +
                    "from sys_user su,sys_user_role sur,sys_role sr,sys_office so " +
                    "where  su.id = sur.user_id " +
                    "and  sr.id = sur.role_id  " +
                    "and su.office_id = so.id   " );
            if(condition.containsKey("name")&&!StringUtils.isEmpty(condition.get("name"))){
                sb.append(" and su.name like concat('%',#{name},'%') ");
            }
            if(condition.containsKey("roleName")&&!StringUtils.isEmpty(condition.get("roleName"))){
                sb.append(" and sr.name like concat('%',#{roleName},'%')");
            }
            if(condition.containsKey("mobile")&&!StringUtils.isEmpty(condition.get("mobile"))){
                sb.append(" and su.mobile like concat('%',#{mobile},'%')");
            }
        } else {
            sb.append("select su.*,GROUP_CONCAT(sr.name ) roleName,so.`name` officeName from sys_user su left join sys_user_role sur on su.id = sur.user_id left join sys_role sr  on sr.id = sur.role_id left join sys_office so on su.office_id = so.id ");
        }
        sb.append(" GROUP BY su.id ");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
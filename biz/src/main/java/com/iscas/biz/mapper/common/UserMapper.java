package com.iscas.biz.mapper.common;

import com.iscas.biz.domain.common.User;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 */
@SuppressWarnings({"AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "rawtypes"})
@Repository
public interface UserMapper extends DynamicMapper<User> {

    void updatePwd(User user);

    @Select("select t1.user_id, t2.role_id, t3.role_name from user_info t1, user_role t2, role t3 where t1.user_id = t2.user_id and t2.role_id = t3.role_id")
    List<Map> selectUserRole();

    @Select("select t1.user_id, t2.org_id, t3.org_name from user_info t1, org_user t2, org t3 where t1.user_id = t2.user_id and t2.org_id = t3.org_id")
    List<Map> selectOrgUser();

    @Select("select t1.user_id, t1.user_name, t2.role_id, t3.role_name from user_info t1, user_role t2, role t3 where t1.user_id = t2.user_id and t2.role_id = t3.  role_id and t1.user_name =#{username}")
    List<Map> selectUserRoleByUsername(String username);

    @Select("select * from user_info t where t.user_name = #{username}")
    User selectByUserName(String username);

    @SuppressWarnings("UnusedReturnValue")
    @Insert("insert into user_info (user_name, user_pwd) values (#{user.userName}, #{user.userPwd})")
    @Options(useGeneratedKeys=true, keyProperty= "user.userId")
    int insertUser(@Param("user") User user);

    List<User> selectUserByIds(List<Object> ids);

    int saveOrUpdate(User user);


}
package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AdminUserMapper {
    //添加管理员账户
    int insert(AdminUser record);

    //选择性的添加管理员账户
    int insertSelective(AdminUser record);

    //根据管理员的用户名和密码查询管理员账号
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    //根据用户id查询管理员账号
    AdminUser selectByPrimaryKey(Integer adminUserId);

    //使用id选择性的更新管理员账号信息
    int updateByPrimaryKeySelective(AdminUser record);

    //使用id跟新管理员账号信息
    int updateByPrimaryKey(AdminUser record);
}
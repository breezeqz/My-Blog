package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BlogConfigMapper {
    //查询所有配置信息
    List<BlogConfig> selectAll();
    //根据配置名查询配置信息
    BlogConfig selectByPrimaryKey(String configName);
    //根据配置名选择性的修改配置信息
    int updateByPrimaryKeySelective(BlogConfig record);

}
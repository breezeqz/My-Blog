package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BlogLinkMapper {
    //根据友链id删除友链，实际上是把delete设置成1
    int deleteByPrimaryKey(Integer linkId);

    //增加友链
    int insert(BlogLink record);

    //选择性的增加友链
    int insertSelective(BlogLink record);

    //根据友链id查询
    BlogLink selectByPrimaryKey(Integer linkId);

    //根据友链id有选择性的修改数据
    int updateByPrimaryKeySelective(BlogLink record);

    //根据id更新
    int updateByPrimaryKey(BlogLink record);

    //分页查询所有友链
    List<BlogLink> findLinkList(PageQueryUtil pageUtil);

    //查询友链总数
    int getTotalLinks(PageQueryUtil pageUtil);

    //批量删除
    int deleteBatch(Integer[] ids);
}
package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogCategory;
import com.site.blog.my.core.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BlogCategoryMapper {
    //根据id删除博客类别，实际是把delete设置为1
    int deleteByPrimaryKey(Integer categoryId);

    //增加博客
    int insert(BlogCategory record);

    //选择性的增加博客类别
    int insertSelective(BlogCategory record);

    //根据id查找
    BlogCategory selectByPrimaryKey(Integer categoryId);

    //根据博客类别名查找
    BlogCategory selectByCategoryName(String categoryName);

    //根据id选择性的修改博客类别
    int updateByPrimaryKeySelective(BlogCategory record);

    //根据id修改博客类别
    int updateByPrimaryKey(BlogCategory record);

    //分页查询
    List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

    //使用列表存储id，根据列表中的id查询所有数据
    List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    //查询博客类别总数，pageUtil为null
    int getTotalCategories(PageQueryUtil pageUtil);

    //批量删除
    int deleteBatch(Integer[] ids);
}
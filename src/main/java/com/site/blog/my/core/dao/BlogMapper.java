package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.Blog;
import com.site.blog.my.core.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BlogMapper {
    //根据id删除博客
    int deleteByPrimaryKey(Long blogId);
    //增加博客
    int insert(Blog record);
    //选择性的增加博客
    int insertSelective(Blog record);
    //根据id查询博客
    Blog selectByPrimaryKey(Long blogId);
    //根据id选择性的修改博客
    int updateByPrimaryKeySelective(Blog record);
    //根据id修改博客所有属性值
    int updateByPrimaryKeyWithBLOBs(Blog record);
    //根据id修改博客除内容之外的属性值
    int updateByPrimaryKey(Blog record);
    //分页查询除内容信息的博客（条件查询）
    List<Blog> findBlogList(PageQueryUtil pageUtil);
    //根据type分页查询前limit条数据
    List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);
    //查询博客总数
    int getTotalBlogs(PageQueryUtil pageUtil);
    //批量删除
    int deleteBatch(Integer[] ids);
    //查询同一个标签的博客（不含内容）
    List<Blog> getBlogsPageByTagId(PageQueryUtil pageUtil);
    //查询同一个标签的博客数量
    int getTotalBlogsByTagId(PageQueryUtil pageUtil);
    //根据SubUrl查询博客
    Blog selectBySubUrl(String subUrl);
    //修改博客类别属性
    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);

}
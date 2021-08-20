package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface BlogCommentMapper {
    //增加评论
    int insert(BlogComment record);
    //选择性的增加评论
    int insertSelective(BlogComment record);

    //根据评论id删除评论，实际是把delete设置为1
    int deleteByPrimaryKey(Long commentId);



    //根据id选择性的修改评论
    int updateByPrimaryKeySelective(BlogComment record);
    //根据id修改评论
    int updateByPrimaryKey(BlogComment record);

    //根据id查询评论
    BlogComment selectByPrimaryKey(Long commentId);
    //分页查询评论
    List<BlogComment> findBlogCommentList(Map map);
    //查询所有评论的数量
    int getTotalBlogComments(Map map);
    //根据id数组修改评论状态
    int checkDone(Integer[] ids);
    //批量删除评论
    int deleteBatch(Integer[] ids);
}
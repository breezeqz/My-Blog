package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BlogTagRelationMapper {
    //根据id删除关联
    int deleteByPrimaryKey(Long relationId);
    //增加关联
    int insert(BlogTagRelation record);
    //有选择性的增加关联
    int insertSelective(BlogTagRelation record);
    //根据id查询关联
    BlogTagRelation selectByPrimaryKey(Long relationId);
    //根据blogid和tagid查询关联
    BlogTagRelation selectByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Integer tagId);
    //根据数组id查询去重后的关联
    List<Long> selectDistinctTagIds(Integer[] tagIds);
    //根据id有选择性的修改关联
    int updateByPrimaryKeySelective(BlogTagRelation record);
    //根据id修改关联
    int updateByPrimaryKey(BlogTagRelation record);
    //批量增加关联
    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);
    //根据blogid删除关联
    int deleteByBlogId(Long blogId);
}
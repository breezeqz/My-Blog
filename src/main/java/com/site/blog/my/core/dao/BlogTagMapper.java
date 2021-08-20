package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogTag;
import com.site.blog.my.core.entity.BlogTagCount;
import com.site.blog.my.core.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BlogTagMapper {
    //根据id删除标签，把is_delete设置为1
    int deleteByPrimaryKey(Integer tagId);
    //增加标签
    int insert(BlogTag record);
    //选择性的增加标签
    int insertSelective(BlogTag record);
    //根据标签id查询
    BlogTag selectByPrimaryKey(Integer tagId);
    //根据标签名查询
    BlogTag selectByTagName(String tagName);
    //根据id选择性的修改标签
    int updateByPrimaryKeySelective(BlogTag record);
    //根据id修改标签
    int updateByPrimaryKey(BlogTag record);
    //分页查询标签
    List<BlogTag> findTagList(PageQueryUtil pageUtil);
    //获取每个标签关联几个博客（有几个博客使用了某个标签）
    List<BlogTagCount> getTagCount();
    //获取标签总数
    int getTotalTags(PageQueryUtil pageUtil);
    //批量删除
    int deleteBatch(Integer[] ids);
    //批量增加
    int batchInsertBlogTag(List<BlogTag> tagList);
}
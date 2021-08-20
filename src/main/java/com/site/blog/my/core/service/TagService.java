package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.BlogTagCount;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;

public interface TagService {

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);
    //获取标签总数
    int getTotalTags();
    //增加标签
    Boolean saveTag(String tagName);
    //批量删除（不能删除和博客关联的标签）
    Boolean deleteBatch(Integer[] ids);
    //获取每个标签关联几个博客
    List<BlogTagCount> getBlogTagCountForIndex();
}

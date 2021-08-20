package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;
import java.util.Map;

public interface LinkService {
    //分页获取友链
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);
    //获取友链总数
    int getTotalLinks();
    //添加友链
    Boolean saveLink(BlogLink link);
    //使用id查询友链
    BlogLink selectById(Integer id);
    //修改友链
    Boolean updateLink(BlogLink tempLink);
    //批量删除友链
    Boolean deleteBatch(Integer[] ids);
    //将友链根据类型分组
    Map<Byte, List<BlogLink>> getLinksForLinkPage();
}

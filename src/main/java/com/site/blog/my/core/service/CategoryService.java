package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.BlogCategory;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);
    //获取类别数
    int getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    //修改类别
    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);
    //批量删除分类
    Boolean deleteBatch(Integer[] ids);
    //获取所有类别
    List<BlogCategory> getAllCategories();
}

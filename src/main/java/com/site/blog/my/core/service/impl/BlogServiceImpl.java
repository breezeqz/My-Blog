package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.controller.vo.BlogDetailVO;
import com.site.blog.my.core.controller.vo.BlogListVO;
import com.site.blog.my.core.controller.vo.SimpleBlogListVO;
import com.site.blog.my.core.dao.*;
import com.site.blog.my.core.entity.Blog;
import com.site.blog.my.core.entity.BlogCategory;
import com.site.blog.my.core.entity.BlogTag;
import com.site.blog.my.core.entity.BlogTagRelation;
import com.site.blog.my.core.service.BlogService;
import com.site.blog.my.core.util.MarkDownUtil;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import com.site.blog.my.core.util.PatternUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogCategoryMapper categoryMapper;
    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    @Transactional
    public String saveBlog(Blog blog) {
        //设置博客分类
        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blog.setBlogCategoryId(0);
            blog.setBlogCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blog.setBlogCategoryName(blogCategory.getCategoryName());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签数据
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        //保存文章
        if (blogMapper.insertSelective(blog) > 0) {
            //新增的tag对象
            List<BlogTag> tagListForInsert = new ArrayList<>();//保存该博客新增加的tag
            //所有的tag对象，用于建立关系数据
            List<BlogTag> allTagsList = new ArrayList<>();//保存该博客的所有tag
            for (int i = 0; i < tags.length; i++) {
                BlogTag tag = tagMapper.selectByTagName(tags[i]);
                if (tag == null) {
                    //不存在就新增
                    BlogTag tempTag = new BlogTag();
                    tempTag.setTagName(tags[i]);
                    tagListForInsert.add(tempTag);
                } else {
                    allTagsList.add(tag);
                }
            }
            //新增标签数据并修改分类排序值
            if (!CollectionUtils.isEmpty(tagListForInsert)) {
                tagMapper.batchInsertBlogTag(tagListForInsert);
            }
            categoryMapper.updateByPrimaryKeySelective(blogCategory);
            //存放关系对象
            List<BlogTagRelation> blogTagRelations = new ArrayList<>();
            //新增关系数据
            allTagsList.addAll(tagListForInsert);//将tagListForInsert中的元素添加到allTagsList尾部（存放所有标签）
            for (BlogTag tag : allTagsList) {
                BlogTagRelation blogTagRelation = new BlogTagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationMapper.batchInsert(blogTagRelations) > 0) {
                return "success";
            }
        }
        return "保存失败";
    }

    @Override
    public PageResult getBlogsPage(PageQueryUtil pageUtil) {
        List<Blog> blogList = blogMapper.findBlogList(pageUtil);
        int total = blogMapper.getTotalBlogs(pageUtil);
        PageResult pageResult = new PageResult(blogList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogMapper.deleteBatch(ids) > 0;
    }

    @Override
    public int getTotalBlogs() {
        return blogMapper.getTotalBlogs(null);
    }

    @Override
    public Blog getBlogById(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    @Transactional
    public String updateBlog(Blog blog) {
        Blog blogForUpdate = blogMapper.selectByPrimaryKey(blog.getBlogId());
        if (blogForUpdate == null) {
            return "数据不存在";
        }
        blogForUpdate.setBlogTitle(blog.getBlogTitle());
        blogForUpdate.setBlogSubUrl(blog.getBlogSubUrl());
        blogForUpdate.setBlogContent(blog.getBlogContent());
        blogForUpdate.setBlogCoverImage(blog.getBlogCoverImage());
        blogForUpdate.setBlogStatus(blog.getBlogStatus());
        blogForUpdate.setEnableComment(blog.getEnableComment());
        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blogForUpdate.setBlogCategoryId(0);
            blogForUpdate.setBlogCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blogForUpdate.setBlogCategoryName(blogCategory.getCategoryName());
            blogForUpdate.setBlogCategoryId(blogCategory.getCategoryId());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签数据
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        blogForUpdate.setBlogTags(blog.getBlogTags());
        //新增的tag对象
        List<BlogTag> tagListForInsert = new ArrayList<>();
        //所有的tag对象，用于建立关系数据
        List<BlogTag> allTagsList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            BlogTag tag = tagMapper.selectByTagName(tags[i]);
            if (tag == null) {
                //不存在就新增
                BlogTag tempTag = new BlogTag();
                tempTag.setTagName(tags[i]);
                tagListForInsert.add(tempTag);
            } else {
                allTagsList.add(tag);
            }
        }
        //新增标签数据不为空->新增标签数据
        if (!CollectionUtils.isEmpty(tagListForInsert)) {
            tagMapper.batchInsertBlogTag(tagListForInsert);
        }
        List<BlogTagRelation> blogTagRelations = new ArrayList<>();
        //新增关系数据
        allTagsList.addAll(tagListForInsert);
        for (BlogTag tag : allTagsList) {
            BlogTagRelation blogTagRelation = new BlogTagRelation();
            blogTagRelation.setBlogId(blog.getBlogId());
            blogTagRelation.setTagId(tag.getTagId());
            blogTagRelations.add(blogTagRelation);
        }
        //修改blog信息->修改分类排序值->删除原关系数据->保存新的关系数据
        categoryMapper.updateByPrimaryKeySelective(blogCategory);
        blogTagRelationMapper.deleteByBlogId(blog.getBlogId());
        blogTagRelationMapper.batchInsert(blogTagRelations);
        if (blogMapper.updateByPrimaryKeySelective(blogForUpdate) > 0) {
            return "success";
        }
        return "修改失败";
    }

    @Override
    public PageResult getBlogsForIndexPage(int page) {
        Map<String,Object> params = new HashMap<>();
        params.put("page", page);
        //每页8条
        params.put("limit", 8);
        params.put("blogStatus", 1);//过滤发布状态下的数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<Blog> blogList = blogMapper.findBlogList(pageUtil);//分页获取所有博客记录，该博客记录中没有内容
        //获取BlogListVO对象，该对象是Blog对象和BlogCategory对象的合并简化版，用于向前端传输数据，展示页面。
        List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
        int total = blogMapper.getTotalBlogs(pageUtil);//获取博客总数
        PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<SimpleBlogListVO> getBlogListForIndexPage(int type) {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        List<Blog> blogs = blogMapper.findBlogListByType(type, 9);
        if (!CollectionUtils.isEmpty(blogs)) {
            for (Blog blog : blogs) {
                SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
                BeanUtils.copyProperties(blog, simpleBlogListVO);//将blog中的属性赋值给simpleBlogListV0对象。
                simpleBlogListVOS.add(simpleBlogListVO);
            }
        }
        return simpleBlogListVOS;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        //不为空且状态为已发布
        BlogDetailVO blogDetailVO = getBlogDetailVO(blog);
        if (blogDetailVO != null) {
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageByTag(String tagName, int page) {
        if (PatternUtil.validKeyword(tagName)) {
            BlogTag tag = tagMapper.selectByTagName(tagName);
            if (tag != null && page > 0) {
                Map param = new HashMap();
                param.put("page", page);
                param.put("limit", 9);
                param.put("tagId", tag.getTagId());
                PageQueryUtil pageUtil = new PageQueryUtil(param);
                //查询同一个标签的博客
                List<Blog> blogList = blogMapper.getBlogsPageByTagId(pageUtil);
                //封装成前端展示对象
                List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
                int total = blogMapper.getTotalBlogsByTagId(pageUtil);
                PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageByCategory(String categoryName, int page) {
        if (PatternUtil.validKeyword(categoryName)) {
            BlogCategory blogCategory = categoryMapper.selectByCategoryName(categoryName);
            if ("默认分类".equals(categoryName) && blogCategory == null) {
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
            }
            if (blogCategory != null && page > 0) {
                Map param = new HashMap();
                param.put("page", page);
                param.put("limit", 9);
                param.put("blogCategoryId", blogCategory.getCategoryId());
                param.put("blogStatus", 1);//过滤发布状态下的数据
                PageQueryUtil pageUtil = new PageQueryUtil(param);
                List<Blog> blogList = blogMapper.findBlogList(pageUtil);
                List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
                int total = blogMapper.getTotalBlogs(pageUtil);
                PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageBySearch(String keyword, int page) {
        if (page > 0 && PatternUtil.validKeyword(keyword)) {
            Map param = new HashMap();
            param.put("page", page);
            param.put("limit", 9);
            param.put("keyword", keyword);//用于模糊查询
            param.put("blogStatus", 1);//过滤发布状态下的数据
            PageQueryUtil pageUtil = new PageQueryUtil(param);
            List<Blog> blogList = blogMapper.findBlogList(pageUtil);
            List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
            int total = blogMapper.getTotalBlogs(pageUtil);
            PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        return null;
    }

    @Override
    public BlogDetailVO getBlogDetailBySubUrl(String subUrl) {
        Blog blog = blogMapper.selectBySubUrl(subUrl);
        //不为空且状态为已发布
        BlogDetailVO blogDetailVO = getBlogDetailVO(blog);
        if (blogDetailVO != null) {
            return blogDetailVO;
        }
        return null;
    }

    /**
     *
     * 将Blog(博客)封装成BlogDetailVo(博客详情，更详细的博客属性)
     *
     * @param blog
     * @return
     */
    private BlogDetailVO getBlogDetailVO(Blog blog) {
        if (blog != null && blog.getBlogStatus() == 1) {
            //增加浏览量
            blog.setBlogViews(blog.getBlogViews() + 1);
            blogMapper.updateByPrimaryKey(blog);
            BlogDetailVO blogDetailVO = new BlogDetailVO();
            BeanUtils.copyProperties(blog, blogDetailVO);
            blogDetailVO.setBlogContent(MarkDownUtil.mdToHtml(blogDetailVO.getBlogContent()));
            BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
            if (blogCategory == null) {
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
                blogCategory.setCategoryName("默认分类");
                blogCategory.setCategoryIcon("/admin/dist/img/category/00.png");
            }
            //分类信息
            blogDetailVO.setBlogCategoryIcon(blogCategory.getCategoryIcon());
            if (!StringUtils.isEmpty(blog.getBlogTags())) {
                //标签设置
                List<String> tags = Arrays.asList(blog.getBlogTags().split(","));
                blogDetailVO.setBlogTags(tags);
            }
            //设置评论数
            Map params = new HashMap();
            params.put("blogId", blog.getBlogId());
            params.put("commentStatus", 1);//过滤审核通过的数据
            blogDetailVO.setCommentCount(blogCommentMapper.getTotalBlogComments(params));
            return blogDetailVO;
        }
        return null;
    }

    /**
     * 将博客列表（不含内容的博客）封装成前端展示的博客列表
     * @param blogList
     * @return
     */
    private List<BlogListVO> getBlogListVOsByBlogs(List<Blog> blogList) {
        List<BlogListVO> blogListVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(blogList)) {
            //将List<Blog>转换成List<Integer>,获取列表中的categoryid集合
            List<Integer> categoryIds = blogList.stream().map(Blog::getBlogCategoryId).collect(Collectors.toList());
            Map<Integer, String> blogCategoryMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(categoryIds)) {
                //使用博客类别的id列表集合获取博客类别
                List<BlogCategory> blogCategories = categoryMapper.selectByCategoryIds(categoryIds);
                if (!CollectionUtils.isEmpty(blogCategories)) {
                    //将博客类别记录的集合转换成map，该map的键位CategoryId，值为CategoryIcon
                    blogCategoryMap = blogCategories.stream().collect(Collectors.toMap(BlogCategory::getCategoryId, BlogCategory::getCategoryIcon, (key1, key2) -> key2));
                }
            }
            //设置图标
            for (Blog blog : blogList) {
                BlogListVO blogListVO = new BlogListVO();
                BeanUtils.copyProperties(blog, blogListVO);//进行对象之间的属性赋值
                if (blogCategoryMap.containsKey(blog.getBlogCategoryId())) {
                    blogListVO.setBlogCategoryIcon(blogCategoryMap.get(blog.getBlogCategoryId()));
                } else {
                    blogListVO.setBlogCategoryId(0);
                    blogListVO.setBlogCategoryName("默认分类");
                    blogListVO.setBlogCategoryIcon("/admin/dist/img/category/00.png");
                }
                blogListVOS.add(blogListVO);
            }
        }
        return blogListVOS;
    }

}

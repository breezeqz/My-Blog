package com.site.blog.my.core.controller.vo;

import java.util.Date;
import java.util.List;
//entity里的每一个字段，与数据库相对应
//dto里的每一个字段，是和你前台页面相对应
//vo用来转换从entity到dto，或者从dto到entity的中间的东西
public class BlogDetailVO {
//博客详情，融合了博客信息，分类信息，评论信息三个实体类

    //部分博客信息
    private Long blogId;

    private String blogTitle;

    private Integer blogCategoryId;

    private String blogCategoryName;

    private List<String> blogTags;

    private Long blogViews;

    private Byte enableComment;

    private Date createTime;

    private String blogContent;
    //部分博客分类信息
    private String blogCategoryIcon;

    private String blogCoverImage;
    //评论数量
    private Integer commentCount;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Integer getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(Integer blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getBlogCategoryIcon() {
        return blogCategoryIcon;
    }

    public void setBlogCategoryIcon(String blogCategoryIcon) {
        this.blogCategoryIcon = blogCategoryIcon;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public Long getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(Long blogViews) {
        this.blogViews = blogViews;
    }

    public List<String> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(List<String> blogTags) {
        this.blogTags = blogTags;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Byte getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Byte enableComment) {
        this.enableComment = enableComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBlogCoverImage() {
        return blogCoverImage;
    }

    public void setBlogCoverImage(String blogCoverImage) {
        this.blogCoverImage = blogCoverImage;
    }
}

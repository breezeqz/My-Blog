package com.site.blog.my.core.controller.blog;

import com.site.blog.my.core.controller.vo.BlogDetailVO;
import com.site.blog.my.core.entity.BlogComment;
import com.site.blog.my.core.entity.BlogLink;
import com.site.blog.my.core.service.*;
import com.site.blog.my.core.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class MyBlogController {

    //更换主题
//    public static String theme = "default";
//    public static String theme = "yummy-jekyll";
    public static String theme = "amaze";
    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;
    @Resource
    private LinkService linkService;
    @Resource
    private CommentService commentService;
    @Resource
    private ConfigService configService;
    @Resource
    private CategoryService categoryService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(Model model) {
        return this.page(model, 1);
    }

    /**
     * 首页 分页数据
     *
     * @return
     */
    // Model、HttpServletRequest、HttpSession都可以用来共享数据，只是作用域不同。
    // model和request在一次请求中共享数据，而seesion在一次会话的多次请求间共享数据。一次会话是指浏览器打开页面直到关闭。
    @GetMapping({"/page/{pageNum}"})
    public String page(Model model, @PathVariable("pageNum") int pageNum) {
        PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
        if (blogPageResult == null) {
            return "error/error_404";
        }
        model.addAttribute("blogPageResult", blogPageResult);
        model.addAttribute("newBlogs", blogService.getBlogListForIndexPage(1));//最新发布9条博客
        model.addAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));//最多点击9条博客
        model.addAttribute("hotTags", tagService.getBlogTagCountForIndex());//使用最多的20个标签
        model.addAttribute("pageName", "首页");//页名
        model.addAttribute("configurations", configService.getAllConfigs());//所有配置
        return "blog/" + theme + "/index";
    }

    /**
     * Categories页面(包括分类数据和标签数据)(yummy-jekyll专有)
     *
     * @return
     */
    @GetMapping({"/categories"})
    public String categories(HttpServletRequest request) {
        request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());//使用最多的20个标签
        request.setAttribute("categories", categoryService.getAllCategories());//所有分类
        request.setAttribute("pageName", "分类页面");//页名
        request.setAttribute("configurations", configService.getAllConfigs());//所有配置
        return "blog/" + theme + "/category";
    }

    /**
     * 博客详情页
     *
     * @return
     */
    @GetMapping({"/blog/{blogId}", "/article/{blogId}"})
    public String detail(HttpServletRequest request, @PathVariable("blogId") Long blogId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {
        BlogDetailVO blogDetailVO = blogService.getBlogDetail(blogId);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);//博客详情
            request.setAttribute("commentPageResult", commentService.getCommentPageByBlogIdAndPageNum(blogId, commentPage));//获取该博客的第一页评论
        }
        request.setAttribute("pageName", "详情");//页名
        request.setAttribute("configurations", configService.getAllConfigs());//配置
        return "blog/" + theme + "/detail";
    }

    /**
     * 标签列表页
     *
     * @return
     */
    @GetMapping({"/tag/{tagName}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName) {
        return tag(request, tagName, 1);
    }

    /**
     * 标签列表页
     *
     * @return
     */
    @GetMapping({"/tag/{tagName}/{page}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageByTag(tagName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "标签");
        request.setAttribute("pageUrl", "tag");
        request.setAttribute("keyword", tagName);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    /**
     * 分类列表页
     *
     * @return
     */
    @GetMapping({"/category/{categoryName}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName) {
        return category(request, categoryName, 1);
    }

    /**
     * 分类列表页
     *
     * @return
     */
    @GetMapping({"/category/{categoryName}/{page}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageByCategory(categoryName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "分类");
        request.setAttribute("pageUrl", "category");
        request.setAttribute("keyword", categoryName);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, keyword, 1);
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}/{page}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageBySearch(keyword, page);//模糊查询
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "搜索");
        request.setAttribute("pageUrl", "search");
        request.setAttribute("keyword", keyword);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/list";
    }


    /**
     * 友情链接页
     *
     * @return
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Byte, List<BlogLink>> linkMap = linkService.getLinksForLinkPage();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey((byte) 0)) {
                request.setAttribute("favoriteLinks", linkMap.get((byte) 0));
            }
            if (linkMap.containsKey((byte) 1)) {
                request.setAttribute("recommendLinks", linkMap.get((byte) 1));
            }
            if (linkMap.containsKey((byte) 2)) {
                request.setAttribute("personalLinks", linkMap.get((byte) 2));
            }
        }
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/link";
    }

    /**
     * 评论操作
     */
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, HttpSession session,
                          @RequestParam Long blogId, @RequestParam String verifyCode,
                          @RequestParam String commentator, @RequestParam String email,
                          @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (StringUtils.isEmpty(verifyCode)) {
            return ResultGenerator.genFailResult("验证码不能为空");
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (!verifyCode.equals(kaptchaCode)) {
            return ResultGenerator.genFailResult("验证码错误");
        }
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (null == blogId || blogId < 0) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (StringUtils.isEmpty(commentator)) {
            return ResultGenerator.genFailResult("请输入称呼");
        }
        if (StringUtils.isEmpty(email)) {
            return ResultGenerator.genFailResult("请输入邮箱地址");
        }
        if (!PatternUtil.isEmail(email)) {
            return ResultGenerator.genFailResult("请输入正确的邮箱地址");
        }
        if (StringUtils.isEmpty(commentBody)) {
            return ResultGenerator.genFailResult("请输入评论内容");
        }
        if (commentBody.trim().length() > 200) {
            return ResultGenerator.genFailResult("评论内容过长");
        }
        BlogComment comment = new BlogComment();
        comment.setBlogId(blogId);
        comment.setCommentator(MyBlogUtils.cleanString(commentator));
        comment.setEmail(email);
        if (PatternUtil.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }
        comment.setCommentBody(MyBlogUtils.cleanString(commentBody));
        return ResultGenerator.genSuccessResult(commentService.addComment(comment));
    }

    /**
     * 关于页面 以及其他配置了subUrl的文章页
     *
     * @return
     */
    @GetMapping({"/{subUrl}"})
    public String detail(HttpServletRequest request, @PathVariable("subUrl") String subUrl) {
        BlogDetailVO blogDetailVO = blogService.getBlogDetailBySubUrl(subUrl);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);
            request.setAttribute("pageName", subUrl);
            request.setAttribute("configurations", configService.getAllConfigs());
            return "blog/" + theme + "/detail";
        } else {
            return "error/error_400";
        }
    }
}

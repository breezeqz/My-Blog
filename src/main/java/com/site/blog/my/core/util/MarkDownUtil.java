package com.site.blog.my.core.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class MarkDownUtil {
    /**
     * 转换md格式为html
     *
     * @param markdownString
     * @return
     */
    public static String mdToHtml(String markdownString) {
        //StringUtils.isEmpty()可以判断字符串是否为null、长度是否为0;
        if (StringUtils.isEmpty(markdownString)) {
            return "";
        }
        //表格扩展
        java.util.List<Extension> extensions = Arrays.asList(TablesExtension.create());
        //解析器
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdownString);
        //html渲染器
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String content = renderer.render(document);
        return content;
    }
}

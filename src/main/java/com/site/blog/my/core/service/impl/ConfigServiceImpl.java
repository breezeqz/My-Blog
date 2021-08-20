package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.BlogConfigMapper;
import com.site.blog.my.core.entity.BlogConfig;
import com.site.blog.my.core.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private BlogConfigMapper configMapper;

    public static final String websiteName = "personal blog";
    public static final String websiteDescription = "personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建";
    public static final String websiteLogo = "/admin/dist/img/logo2.png";
    public static final String websiteIcon = "/admin/dist/img/favicon.png";

    public static final String yourAvatar = "/admin/dist/img/13.png";
    public static final String yourEmail = "123456789@qq.com";
    public static final String yourName = "zq";

    public static final String footerAbout = "your personal blog. have fun.";
    public static final String footerICP = "豫ICP备 xxxxxx-x号";
    public static final String footerCopyRight = "@2018 zq";
    public static final String footerPoweredBy = "personal blog";
    public static final String footerPoweredByURL = "##";
    //更新配置
    @Override
    public int updateConfig(String configName, String configValue) {
        BlogConfig blogConfig = configMapper.selectByPrimaryKey(configName);
        if (blogConfig != null) {
            blogConfig.setConfigValue(configValue);
            blogConfig.setUpdateTime(new Date());
            return configMapper.updateByPrimaryKeySelective(blogConfig);
        }
        return 0;
    }
    //获取所有配置项，封装成map
    @Override
    public Map<String, String> getAllConfigs() {
        List<BlogConfig> blogConfigs = configMapper.selectAll();
        //普通方式
        HashMap<String, String> hm = new HashMap<>();
        for (BlogConfig blogConfig : blogConfigs) {
            String configName = blogConfig.getConfigName();
            String configValue = blogConfig.getConfigValue();
            if ("websiteName".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("websiteDescription".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("websiteLogo".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("websiteIcon".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("yourAvatar".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("yourEmail".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("yourName".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("footerAbout".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("footerICP".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("footerCopyRight".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("footerPoweredBy".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
            if ("footerPoweredByURL".equals(configName) && !(StringUtils.isEmpty(configValue))) {
                hm.put(configName, configValue);
            }
        }
        return hm;
//        流方式
//        Map<String, String> configMap = blogConfigs.stream().collect(Collectors.toMap(BlogConfig::getConfigName, BlogConfig::getConfigValue));
//        for (Map.Entry<String, String> config : configMap.entrySet()) {
//            if ("websiteName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteName);
//            }
//            if ("websiteDescription".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteDescription);
//            }
//            if ("websiteLogo".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteLogo);
//            }
//            if ("websiteIcon".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(websiteIcon);
//            }
//            if ("yourAvatar".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(yourAvatar);
//            }
//            if ("yourEmail".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(yourEmail);
//            }
//            if ("yourName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(yourName);
//            }
//            if ("footerAbout".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerAbout);
//            }
//            if ("footerICP".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerICP);
//            }
//            if ("footerCopyRight".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerCopyRight);
//            }
//            if ("footerPoweredBy".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerPoweredBy);
//            }
//            if ("footerPoweredByURL".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
//                config.setValue(footerPoweredByURL);
//            }
//        }
//        return configMap;
    }
}

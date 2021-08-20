package com.site.blog.my.core.config;

import java.io.File;

public class Constants {
    public static final String FILE_UPLOAD_DIC;
    static{
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            FILE_UPLOAD_DIC = new File("").getAbsolutePath()+File.separator+"upload"+File.separator;
        }
        else{
            FILE_UPLOAD_DIC = "/usr/local/upload/";
        }
    }
//    public final static String FILE_UPLOAD_DIC = "/usr/local/project/upload/";//上传文件的默认url前缀，根据部署设置自行修改
//    public final static String FILE_UPLOAS_DIC_WIN = new File("").getAbsolutePath()+File.separator+"upload"+File.separator;
}

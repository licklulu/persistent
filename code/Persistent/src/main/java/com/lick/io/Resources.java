package com.lick.io;

import java.io.InputStream;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/4 23:11
 */
public class Resources {
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}

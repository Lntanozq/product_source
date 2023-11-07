package com.briup.framework.mapper.extend;

import java.util.List;
import java.util.Map;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/28-03-28-16:46
 * @Description：文件操作 mapper
 */
public interface FileMapper {
    /**
     * 获取数据库所有表的图片路径
     * t_img 1.jpg 2.jpg
     * f_img 1.jpg 2.jpg
     */
    List<String> getPathsInDB();
}

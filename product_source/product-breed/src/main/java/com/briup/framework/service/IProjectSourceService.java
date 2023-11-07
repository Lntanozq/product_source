package com.briup.framework.service;

import com.briup.framework.bean.extend.ProductSource;

/**
 * @author pgc
 * @Description: 溯源业务层
 * @date 2022/4/11 14:08
 */
public interface IProjectSourceService {
    //生成图片
    void updateAnimalImg(String animalId,String fileImg);
    ProductSource query(String animalId);
}

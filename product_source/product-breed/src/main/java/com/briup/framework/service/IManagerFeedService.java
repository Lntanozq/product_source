package com.briup.framework.service;

import com.briup.framework.bean.ManagerFeed;
import com.briup.framework.bean.dto.ManagerFeedDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-18:59
 * @Description：饲料业务 接口规范
 * @version：1.0
 */
public interface IManagerFeedService {
    // 查询所有
    List<ManagerFeed> queryAll();
    // 条件搜索饲料
    PageInfo<ManagerFeed> searchFeed(int page, int pageSize, String fName, String fSupplier, String fType);
    // 查询饲料
    List<ManagerFeed> query();
    // 单个删除饲料
    void deleteFeed(Integer id);
    // 批量删除饲料
    void batchDeleteFeed(List<Integer> ids);

    // 添加或者修改饲料
    void saveOrUpdate(ManagerFeedDto feed);
    // 查询所有
}

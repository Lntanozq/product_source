package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerFeed;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-19:39
 * @Description：饲料管理接口规范 拓展
 * @version：1.0
 */
public interface ManagerFeedExtendMapper {
    // 根据饲料名称查找饲料
    ManagerFeed findFeedByName(String name);
    // 批量删除饲料
    void batchDeleteFeed(List<Integer> ids);
    // 条件搜索饲料
    List<ManagerFeed> findByCondition(@Param("fName") String fName, @Param("fSupplier") String fSupplier,@Param("fType") String fType);
    // 查询所有饲料
    List<ManagerFeed> queryAll();
}

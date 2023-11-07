package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerFeed;
import com.briup.framework.bean.dto.ManagerFeedDto;
import com.briup.framework.mapper.ManagerFeedMapper;
import com.briup.framework.mapper.extend.ManagerFeedExtendMapper;
import com.briup.framework.service.IManagerFeedService;
import com.briup.framework.util.EntityUtils;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.briup.framework.utils.web.ResponseCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-19:27
 * @Description：饲料管理业务 具体实现
 * @version：1.0
 */
@Service
public class ManagerFeedServiceImpl implements IManagerFeedService {
    @Autowired
    private ManagerFeedMapper managerFeedMapper;
    @Autowired
    private ManagerFeedExtendMapper managerFeedExtendMapper;

    /**
     * 添加或者修改饲料
     *
     * @param feed 饲料实体
     */
    @Override
    public void saveOrUpdate(ManagerFeedDto feed) {
        String name = feed.getfName();
        Integer id = feed.getfId();
        if (ObjectUtils.isEmpty(id)) {
            // 没有传id 插入
            checkName(feed.getfName());
            feed.setfDelete(0);
            ManagerFeed managerFeed = EntityUtils.entity2VM(feed, ManagerFeed.class);
            managerFeedMapper.insert(managerFeed);
        } else {
            // 更新
            ManagerFeed oldFeed = managerFeedMapper.selectByPrimaryKey(feed.getfId());
            // 如果名字改了
            if (!oldFeed.getfName().equals(name)) {
                // 如果修改了名称 比对是否数据库已有该名称
                checkName(name);
            }
            ManagerFeed managerFeed = EntityUtils.entity2VM(feed, ManagerFeed.class);
            managerFeedMapper.updateByPrimaryKeySelective(managerFeed);
        }
    }


    @Override
    public List<ManagerFeed> queryAll() {
        return managerFeedExtendMapper.queryAll();
    }

    /**
     * 分页条件搜搜
     *
     * @param page      当前页
     * @param pageSize  每页数量
     * @param fName     名字
     * @param fSupplier 供应商
     * @param fType     类型
     * @return
     */
    @Override
    public PageInfo<ManagerFeed> searchFeed(int page, int pageSize, String fName, String fSupplier, String fType) {
        PageHelper.startPage(page, pageSize);
        List<ManagerFeed> list = managerFeedExtendMapper.findByCondition(fName, fSupplier, fType);
        return new PageInfo<>(list);
    }

    @Override
    public List<ManagerFeed> query() {
        return managerFeedExtendMapper.queryAll();
    }

    /**
     * 删除某饲料(逻辑删除)
     *
     * @param id 饲料id
     */
    @Override
    public void deleteFeed(Integer id) {
        ManagerFeed feed = new ManagerFeed();
        feed.setfId(id);
        feed.setfDelete(1);
        int row = managerFeedMapper.updateByPrimaryKeySelective(feed);
        if (row < 1) {
            throw new BriupFrameworkException("参数有误", ResponseCode.DATA_NOT_FOUND);
        }
    }

    /**
     * 批量删除饲料(逻辑删除)
     *
     * @param ids 饲料id集合
     */
    @Override
    public void batchDeleteFeed(List<Integer> ids) {
        managerFeedExtendMapper.batchDeleteFeed(ids);
    }

    private void checkName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new BriupFrameworkException("名称不能为空", ResponseCode.DATA_WRONG);
        }
        ManagerFeed feedFromDB = managerFeedExtendMapper.findFeedByName(name);
        if (!Objects.isNull(feedFromDB)) {
            throw new BriupFrameworkException("名称已存在", ResponseCode.DATA_EXISTED);
        }
    }
}

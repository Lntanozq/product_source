package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.briup.framework.mapper.ManagerFenceHouseMapper;
import com.briup.framework.mapper.extend.ManagerFenceHouseExtendMapper;
import com.briup.framework.service.IManagerFenceHouseService;
import com.briup.framework.util.UUIDUtil;
import com.briup.framework.utils.common.DateTimeUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: product_source
 * @description: 栏舍管理
 * @author: pgc
 * @create:2022-03-23
 **/
@Service
public class ManagerFenceHouseServiceImpl implements IManagerFenceHouseService {
    @Autowired
    private ManagerFenceHouseMapper managerFenceHouseMapper;
    @Autowired
    private ManagerFenceHouseExtendMapper managerFenceHouseExtendMapper;
    @Override
    public void saveOrUpdate(ManagerFenceHouse managerFenceHouse) {
     if (managerFenceHouse.getFhId()!=null&&!managerFenceHouse.getFhId().equals("")){
         //更新
         int count=managerFenceHouseMapper.updateByPrimaryKeySelective(managerFenceHouse);
         if (count==0){
             throw new BriupFrameworkException("更新失败");
         }
     }else {
         //插入
         managerFenceHouse.setFhId(UUIDUtil.getUUID());
         managerFenceHouse.setFhDelete(0);
         //managerFenceHouse.setFhTime(DateTimeUtil.DateToStr(new Date()));
         int count=managerFenceHouseMapper.insertSelective(managerFenceHouse);
         if (count==0){
             throw new BriupFrameworkException("插入失败");
         }
     }
    }

    @Override
    public void deleteById(String fhId) {
        ManagerFenceHouse managerFenceHouse = managerFenceHouseMapper.selectByPrimaryKey(fhId);
        if (managerFenceHouse==null){
            throw  new BriupFrameworkException("删除失败");
        }
        managerFenceHouseExtendMapper.updateById(fhId);
    }

    @Override
    public PageInfo<ManagerFenceHouseExtend> query(int page, int pageSize, String fhName) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<ManagerFenceHouseExtend> list=managerFenceHouseExtendMapper.query(fhName);
        PageInfo<ManagerFenceHouseExtend> pageManagerFenceHouse= new PageInfo<>(list);
        return pageManagerFenceHouse;
    }

    @Override
    public List<ManagerFenceHouse> queryAll() {
        List<ManagerFenceHouse> list=managerFenceHouseExtendMapper.queryAll();
        return list;
    }


    @Override
    public ManagerFenceHouseExtend selectById(String fhId) {
        return managerFenceHouseExtendMapper.selectById(fhId);
    }
}

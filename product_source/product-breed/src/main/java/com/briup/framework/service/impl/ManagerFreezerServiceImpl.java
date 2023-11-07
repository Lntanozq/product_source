package com.briup.framework.service.impl;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.ManagerFreezer;
import com.briup.framework.bean.extend.ManagerFreezerExtend;
import com.briup.framework.mapper.ManagerFreezerMapper;
import com.briup.framework.mapper.extend.ManagerFreezerExtendMapper;
import com.briup.framework.service.IManagerFreezerService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product_source
 * @description: 冷库管理业务层
 * @author: pgc
 * @create:2022-03-29
 **/
@Service
public class ManagerFreezerServiceImpl implements IManagerFreezerService {
    @Autowired
    private ManagerFreezerMapper managerFreezerMapper;
    @Autowired
    private ManagerFreezerExtendMapper managerFreezerExtendMapper;
    @Override
    public void saveOrUpdate(ManagerFreezer managerFreezer) {
        if (managerFreezer.getFzId()!=null){
            int count=managerFreezerMapper.updateByPrimaryKeySelective(managerFreezer);
            if (count==0){
                throw new BriupFrameworkException("更新失败");
            }
        }else {
            managerFreezer.setFzDelete(0);
            managerFreezer.setFzQuantity(0);
            int count=managerFreezerMapper.insertSelective(managerFreezer);
            if (count==0){
                throw new BriupFrameworkException("插入失败");
            }
        }

    }

    @Override
    public List<BaseAccount> queryFreezer() {
        return managerFreezerExtendMapper.queryFreezer();
    }

    @Override
    public PageInfo<ManagerFreezerExtend> query(int page, int pageSize, String fzName, String fzNum, String freezerId) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<ManagerFreezerExtend> list=managerFreezerExtendMapper.query(fzName,fzNum,freezerId);
        PageInfo<ManagerFreezerExtend> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<ManagerFreezer> queryAll() {
        return managerFreezerExtendMapper.queryAll();
    }

    @Override
    public void deleteById(int fzId) {
        ManagerFreezer managerFreezer = managerFreezerMapper.selectByPrimaryKey(fzId);
        if (managerFreezer==null){
            throw  new ArithmeticException("删除失败");
        }
        managerFreezerExtendMapper.updateById(fzId);
    }
}

package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.ManagerAnimalExtend;
import com.briup.framework.mapper.ManagerAnimalMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.ManagerHurdlesExtendMapper;
import com.briup.framework.service.IManagerAnimalService;
import com.briup.framework.util.UUIDUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product_source
 * @description: 动物管理业务类
 * @author: pgc
 * @create:2022-03-28
 **/
@Service
public class ManagerAnimalServiceImpl implements IManagerAnimalService {
    @Autowired
    private ManagerAnimalMapper managerAnimalMapper;
    @Autowired
    private ManagerAnimalExtendMapper managerAnimalExtendMapper;
    @Autowired
    private ManagerHurdlesExtendMapper managerHurdlesExtendMapper;
    @Override
    public void saveOrUpdate(ManagerAnimal managerAnimal) {
        if (managerAnimal.getaAnimalId()!=null&&!managerAnimal.getaAnimalId().equals("")){
            int count=managerAnimalMapper.updateByPrimaryKeySelective(managerAnimal);
            if (count==0){
                throw new BriupFrameworkException("更新失败");
            }
        }else {
            //插入前根据栏圈id查看该栏圈是否已满，如果未满则更新以存量
            int max = managerHurdlesExtendMapper.selectByIdMax(managerAnimal.getaHurdlesId());
            int saved = managerHurdlesExtendMapper.selectByIdSaved(managerAnimal.getaHurdlesId());
            if (saved>=max){
                throw new BriupFrameworkException("插入失败,该栏圈已存满");
            }
            //插入
            managerAnimal.setaAnimalId(UUIDUtil.getUUID());
            managerAnimal.setaBackup2("0");
            int count=managerAnimalMapper.insertSelective(managerAnimal);
            if (count==0){
                throw new BriupFrameworkException("插入失败");
            }
            managerHurdlesExtendMapper.updateByIdSaved(managerAnimal.getaHurdlesId());
        }
    }

    @Override
    public void deleteById(String aId) {
        ManagerAnimal managerAnimal = managerAnimalMapper.selectByPrimaryKey(aId);
        if (managerAnimal==null){
            throw new BriupFrameworkException("删除失败");
        }
        managerAnimalExtendMapper.updateById(aId);
    }

    @Override
    public PageInfo<ManagerAnimalExtend> query(int page, int pageSize, String batchNumber, String hurdlesNumber, String aHealthy, String aGender) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<ManagerAnimalExtend> list=managerAnimalExtendMapper.query(batchNumber,hurdlesNumber,aHealthy,aGender);
        PageInfo<ManagerAnimalExtend> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public ManagerAnimalExtend findByAnimalId(String animalId) {
        return managerAnimalExtendMapper.findByAnimalId(animalId);
    }
}

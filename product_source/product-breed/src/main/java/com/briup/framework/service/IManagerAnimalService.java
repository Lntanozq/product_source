package com.briup.framework.service;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.ManagerAnimalExtend;
import com.github.pagehelper.PageInfo;

/*
动物管理
 */
public interface IManagerAnimalService {
    //保存或更新
    void saveOrUpdate(ManagerAnimal managerAnimal);
    //通过id删除
    void deleteById(String aId);
    //多条件分页查询
    PageInfo<ManagerAnimalExtend> query(int page, int pageSize, String batchNumber, String hurdlesNumber,String aHealthy, String aGender);
    //获取动物详情
    ManagerAnimalExtend findByAnimalId(String animalId);

}

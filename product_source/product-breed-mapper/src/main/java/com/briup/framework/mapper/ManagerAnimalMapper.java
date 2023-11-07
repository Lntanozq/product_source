package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerAnimal;

public interface ManagerAnimalMapper {
    int deleteByPrimaryKey(String aAnimalId);

    int insert(ManagerAnimal record);

    int insertSelective(ManagerAnimal record);

    ManagerAnimal selectByPrimaryKey(String aAnimalId);

    int updateByPrimaryKeySelective(ManagerAnimal record);

    int updateByPrimaryKey(ManagerAnimal record);
}
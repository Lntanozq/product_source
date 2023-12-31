/*
 * @Description: 栏舍管理后台接口API
 * @Author: Ronda
 * @Date: 2022-03-24 09:54:47
 * @LastEditors: Ronda
 * @LastEditTime: 2022-03-29 16:37:40
 */

import { get, postJSON, del } from '@/utils/request'

/**
 * @description: 根据id删除栏舍
 * @param {*} fhId
 * @return {*}
 */
export function delById(fhId) {
  return del(`/fenceHouse/deleteById/${fhId}`)
}

/**
 * @description: 批量删除栏舍
 * @param {*}
 * @return {*}
 */
export function deleteByIdAll(fhIds) {
  return postJSON(`/fenceHouse/deleteByIdAll`, fhIds)
}

/**
 * @description: 分页并根据条件查询栏舍
 * @param {*} data
 * @return {*}
 */
export function findByOption(data) {
  const obj = { ...data }
  for (const key in obj) {
    if (!obj[key]) {
      delete obj[key]
    }
  }
  return get(`/fenceHouse/query`, obj)
}

/**
 * @description: 查询所有栏舍信息
 * @param {*}
 * @return {*}
 */
export function findAll() {
  return get(`/fenceHouse/queryAll`)
}

/**
 * @description: 保存或更新栏舍
 * @param {*} data
 * @return {*}
 */
export function saveOrUpdate(data) {
  return postJSON(`/fenceHouse/saveOrUpdate`, data)
}

/**
 * @description: 根据id查询栏舍信息及其下属的栏圈信息
 * @param {*} data
 * @return {*}
 */
export function findById(fhId) {
  return get(`/fenceHouse/selectById`, { fhId })
}

/*
 * @Description: 用户token和权限
 * @Author: Ronda
 * @Date: 2020-08-04 16:47:38
 * @LastEditors: Ronda
 * @LastEditTime: 2022-03-15 15:11:22
 */
import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const AuthsKey = 'Admin-Auths'

// token操作
export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

// 权限树
export function getAuths() {
  // 不存cookie是因为大小超了限制
  return JSON.parse(localStorage.getItem(AuthsKey))
}

export function setAuths(auths) {
  return localStorage.setItem(AuthsKey, JSON.stringify(auths))
}

export function removeAuths() {
  return localStorage.removeItem(AuthsKey)
}

import request from '@/utils/request'

// 查询优秀学生名单列表
export function listExcellent(query) {
  return request({
    url: '/bishe/excellent/list',
    method: 'get',
    params: query
  })
}

// 查询优秀学生名单详细
export function getExcellent(courseId) {
  return request({
    url: '/bishe/excellent/' + courseId,
    method: 'get'
  })
}

// 新增优秀学生名单
export function addExcellent(data) {
  return request({
    url: '/bishe/excellent',
    method: 'post',
    data: data
  })
}

// 修改优秀学生名单
export function updateExcellent(data) {
  return request({
    url: '/bishe/excellent',
    method: 'put',
    data: data
  })
}

// 删除优秀学生名单
export function delExcellent(courseId) {
  return request({
    url: '/bishe/excellent/' + courseId,
    method: 'delete'
  })
}

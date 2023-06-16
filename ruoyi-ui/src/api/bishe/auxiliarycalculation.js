import request from '@/utils/request'

// 查询辅助计算表列表
export function listAuxiliarycalculation(query) {
  return request({
    url: '/bishe/auxiliarycalculation/list',
    method: 'get',
    params: query
  })
}

// 查询辅助计算表详细
export function getAuxiliarycalculation(courseId) {
  return request({
    url: '/bishe/auxiliarycalculation/' + courseId,
    method: 'get'
  })
}

// 新增辅助计算表
export function addAuxiliarycalculation(data) {
  return request({
    url: '/bishe/auxiliarycalculation',
    method: 'post',
    data: data
  })
}

// 修改辅助计算表
export function updateAuxiliarycalculation(data) {
  return request({
    url: '/bishe/auxiliarycalculation',
    method: 'put',
    data: data
  })
}

// 删除辅助计算表
export function delAuxiliarycalculation(courseId) {
  return request({
    url: '/bishe/auxiliarycalculation/' + courseId,
    method: 'delete'
  })
}

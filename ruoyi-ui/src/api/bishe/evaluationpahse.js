import request from '@/utils/request'

// 查询评价环节分配列表
export function listEvaluationpahse(query) {
  return request({
    url: '/bishe/evaluationpahse/list',
    method: 'get',
    params: query
  })
}

// 查询评价环节分配详细
export function getEvaluationpahse(courseId) {
  return request({
    url: '/bishe/evaluationpahse/' + courseId,
    method: 'get'
  })
}

// 新增评价环节分配
export function addEvaluationpahse(data) {
  return request({
    url: '/bishe/evaluationpahse',
    method: 'post',
    data: data
  })
}

// 修改评价环节分配
export function updateEvaluationpahse(data) {
  return request({
    url: '/bishe/evaluationpahse',
    method: 'put',
    data: data
  })
}

// 删除评价环节分配
export function delEvaluationpahse(courseId) {
  return request({
    url: '/bishe/evaluationpahse/' + courseId,
    method: 'delete'
  })
}

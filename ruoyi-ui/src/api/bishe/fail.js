import request from '@/utils/request'

// 查询不及格学生名单列表
export function listFail(query) {
  return request({
    url: '/bishe/fail/list',
    method: 'get',
    params: query
  })
}

// 查询不及格学生名单详细
export function getFail(courseId) {
  return request({
    url: '/bishe/fail/' + courseId,
    method: 'get'
  })
}

// 新增不及格学生名单
export function addFail(data) {
  return request({
    url: '/bishe/fail',
    method: 'post',
    data: data
  })
}

// 修改不及格学生名单
export function updateFail(data) {
  return request({
    url: '/bishe/fail',
    method: 'put',
    data: data
  })
}

// 删除不及格学生名单
export function delFail(courseId) {
  return request({
    url: '/bishe/fail/' + courseId,
    method: 'delete'
  })
}

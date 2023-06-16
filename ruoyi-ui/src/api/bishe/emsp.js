import request from '@/utils/request'

// 查询评价方式及成绩比例列表
export function listEmsp(query) {
  return request({
    url: '/bishe/emsp/list',
    method: 'get',
    params: query
  })
}

// 查询评价方式及成绩比例详细
export function getEmsp(courseId,courseObjectives) {
  const data = {
    courseId,
    courseObjectives
  }
  return request({
    url: '/bishe/emsp/find',
    method: 'post',
    params:data
  })
}

// 新增评价方式及成绩比例
export function addEmsp(data) {
  return request({
    url: '/bishe/emsp',
    method: 'post',
    data: data
  })
}

// 修改评价方式及成绩比例
export function updateEmsp(data) {
  return request({
    url: '/bishe/emsp',
    method: 'put',
    data: data
  })
}

// 删除评价方式及成绩比例
export function delEmsp(courseId, courseObjectives) {
  const data = {
    courseId,
    courseObjectives
  }
  return request({
    url: '/bishe/emsp/delete',
    method: 'post',
    params: data
  })
}

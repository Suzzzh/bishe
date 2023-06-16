import request from '@/utils/request'

// 查询总课程表列表
export function listCourse(query) {
  return request({
    url: '/bishe/course/list',
    method: 'get',
    params: query
  })
}

// 查询总课程表详细
export function getCourse(courseId) {
  return request({
    url: '/bishe/course/' + courseId,
    method: 'get'
  })
}

// 新增总课程表
export function addCourse(data) {
  return request({
    url: '/bishe/course',
    method: 'post',
    data: data
  })
}

// 修改总课程表
export function updateCourse(data) {
  return request({
    url: '/bishe/course',
    method: 'put',
    data: data
  })
}

// 删除总课程表
export function delCourse(courseId) {
  return request({
    url: '/bishe/course/' + courseId,
    method: 'delete'
  })
}
// 生成质量报告
export function getZhiliang(courseId) {
  return request({
    url: '/bishe/course/zhiliang/' + courseId,
    method: 'get'
  })
}

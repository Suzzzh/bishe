import request from '@/utils/request'

// 查询课程目标达成度表列表
export function listCoursegoalachievement(query) {
  return request({
    url: '/bishe/coursegoalachievement/list',
    method: 'get',
    params: query
  })
}

// 查询课程目标达成度表详细
export function getCoursegoalachievement(courseId) {
  return request({
    url: '/bishe/coursegoalachievement/' + courseId,
    method: 'get'
  })
}

// 新增课程目标达成度表
export function addCoursegoalachievement(data) {
  return request({
    url: '/bishe/coursegoalachievement',
    method: 'post',
    data: data
  })
}

// 修改课程目标达成度表
export function updateCoursegoalachievement(data) {
  return request({
    url: '/bishe/coursegoalachievement',
    method: 'put',
    data: data
  })
}

// 删除课程目标达成度表
export function delCoursegoalachievement(courseId, courseObjectives) {
  const data = {
    courseId,
    courseObjectives
  }
  return request({
    url: '/bishe/coursegoalachievement/delete',
    method: 'post',
    params: data
  })
}

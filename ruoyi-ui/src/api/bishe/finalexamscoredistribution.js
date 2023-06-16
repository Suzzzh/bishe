import request from '@/utils/request'

// 查询期末考试各题支撑目标分数分配列表
export function listFinalexamscoredistribution(query) {
  return request({
    url: '/bishe/finalexamscoredistribution/list',
    method: 'get',
    params: query
  })
}

// 新增期末考试各题支撑目标分数分配
export function addFinalexamscoredistribution(data) {
  return request({
    url: '/bishe/finalexamscoredistribution',
    method: 'post',
    data: data
  })
}

// 修改期末考试各题支撑目标分数分配
export function updateFinalexamscoredistribution(data) {
  return request({
    url: '/bishe/finalexamscoredistribution',
    method: 'put',
    data: data
  })
}


// 查询期末考试各题支撑目标分数分配详细
export function getFinalexamscoredistribution(courseId, courseObjectives) {
  const data = {
    courseId,
    courseObjectives
  }
  return request({
    url: '/bishe/finalexamscoredistribution/find',
    method: 'post',
    params: data
  })
}

// 删除期末考试各题支撑目标分数分配
export function delFinalexamscoredistribution(courseId, courseObjectives) {
  const data = {
    courseId,
    courseObjectives
  }
  return request({
    url: '/bishe/finalexamscoredistribution/delete',
    method: 'post',
    params: data
  })
}


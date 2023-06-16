import request from '@/utils/request'

// 查询输入学生成绩列表
export function listInputscore(query) {
  return request({
    url: '/bishe/inputscore/list',
    method: 'get',
    params: query
  })
}

// 查询输入学生成绩详细
export function getInputscore(id,num) {
  const data = {
    id,
    num
  }
  return request({
    url: '/bishe/inputscore/find',
    method: 'post',
    params:data
  })
}

// 新增输入学生成绩
export function addInputscore(data) {
  return request({
    url: '/bishe/inputscore',
    method: 'post',
    data: data
  })
}

// 修改输入学生成绩
export function updateInputscore(data) {
  return request({
    url: '/bishe/inputscore',
    method: 'put',
    data: data
  })
}

// 删除输入学生成绩
export function delInputscore(id,num) {
  const data = {
    id,
    num
  }
  return request({
    url: '/bishe/inputscore/delete',
    method: 'post',
    params: data
  })
}




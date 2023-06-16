import request from '@/utils/request'

// 查询课程文件信息列表
export function listDocument(query) {
  return request({
    url: '/bishe/document/list',
    method: 'get',
    params: query
  })
}

// 查询课程文件信息详细
export function getDocument(id) {
  return request({
    url: '/bishe/document/' + id,
    method: 'get'
  })
}

// 新增课程文件信息
export function addDocument(data) {
  return request({
    url: '/bishe/document',
    method: 'post',
    data: data
  })
}

// 修改课程文件信息
export function updateDocument(data) {
  return request({
    url: '/bishe/document',
    method: 'put',
    data: data
  })
}

// 删除课程文件信息
export function delDocument(id) {
  return request({
    url: '/bishe/document/' + id,
    method: 'delete'
  })
}

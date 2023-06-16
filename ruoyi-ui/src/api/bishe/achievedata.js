import request from '@/utils/request'

// 查询生成达成数据列表
export function listAchievedata(query) {
  return request({
    url: '/bishe/achievedata/list',
    method: 'get',
    params: query
  })
}

// 查询生成达成数据详细
export function getAchievedata(id) {
  return request({
    url: '/bishe/achievedata/' + id,
    method: 'get'
  })
}

// 新增生成达成数据
export function addAchievedata(data) {
  return request({
    url: '/bishe/achievedata',
    method: 'post',
    data: data
  })
}

// 修改生成达成数据
export function updateAchievedata(data) {
  return request({
    url: '/bishe/achievedata',
    method: 'put',
    data: data
  })
}

// 删除生成达成数据
export function delAchievedata(id,num) {
  const data = {
    id,
    num
  }
  return request({
    url: '/bishe/achievedata/delete',
    method: 'post',
    params: data
  })
}

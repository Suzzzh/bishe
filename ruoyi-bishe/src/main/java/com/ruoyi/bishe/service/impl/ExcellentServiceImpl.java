package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.ExcellentMapper;
import com.ruoyi.bishe.domain.Excellent;
import com.ruoyi.bishe.service.IExcellentService;

/**
 * 优秀学生名单Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
@Service
public class ExcellentServiceImpl implements IExcellentService 
{
    @Autowired
    private ExcellentMapper excellentMapper;

    /**
     * 查询优秀学生名单
     * 
     * @param courseId 优秀学生名单主键
     * @return 优秀学生名单
     */
    @Override
    public Excellent selectExcellentByCourseId(Integer courseId)
    {
        return excellentMapper.selectExcellentByCourseId(courseId);
    }

    /**
     * 查询优秀学生名单列表
     * 
     * @param excellent 优秀学生名单
     * @return 优秀学生名单
     */
    @Override
    public List<Excellent> selectExcellentList(Excellent excellent)
    {
        return excellentMapper.selectExcellentList(excellent);
    }

    /**
     * 新增优秀学生名单
     * 
     * @param excellent 优秀学生名单
     * @return 结果
     */
    @Override
    public int insertExcellent(Excellent excellent)
    {
        return excellentMapper.insertExcellent(excellent);
    }

    /**
     * 修改优秀学生名单
     * 
     * @param excellent 优秀学生名单
     * @return 结果
     */
    @Override
    public int updateExcellent(Excellent excellent)
    {
        return excellentMapper.updateExcellent(excellent);
    }

    /**
     * 批量删除优秀学生名单
     * 
     * @param courseIds 需要删除的优秀学生名单主键
     * @return 结果
     */
    @Override
    public int deleteExcellentByCourseIds(Integer[] courseIds)
    {
        return excellentMapper.deleteExcellentByCourseIds(courseIds);
    }

    /**
     * 删除优秀学生名单信息
     * 
     * @param courseId 优秀学生名单主键
     * @return 结果
     */
    @Override
    public int deleteExcellentByCourseId(Integer courseId)
    {
        return excellentMapper.deleteExcellentByCourseId(courseId);
    }

    public List<Excellent> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList){
        return excellentMapper.selectEvaluationpahseByCourseIdList(courseIdList);
    }
}

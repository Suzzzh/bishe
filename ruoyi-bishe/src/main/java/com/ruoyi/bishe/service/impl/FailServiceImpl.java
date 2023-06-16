package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.FailMapper;
import com.ruoyi.bishe.domain.Fail;
import com.ruoyi.bishe.service.IFailService;

/**
 * 不及格学生名单Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
@Service
public class FailServiceImpl implements IFailService 
{
    @Autowired
    private FailMapper failMapper;

    /**
     * 查询不及格学生名单
     * 
     * @param courseId 不及格学生名单主键
     * @return 不及格学生名单
     */
    @Override
    public Fail selectFailByCourseId(Integer courseId)
    {
        return failMapper.selectFailByCourseId(courseId);
    }

    /**
     * 查询不及格学生名单列表
     * 
     * @param fail 不及格学生名单
     * @return 不及格学生名单
     */
    @Override
    public List<Fail> selectFailList(Fail fail)
    {
        return failMapper.selectFailList(fail);
    }

    /**
     * 新增不及格学生名单
     * 
     * @param fail 不及格学生名单
     * @return 结果
     */
    @Override
    public int insertFail(Fail fail)
    {
        return failMapper.insertFail(fail);
    }

    /**
     * 修改不及格学生名单
     * 
     * @param fail 不及格学生名单
     * @return 结果
     */
    @Override
    public int updateFail(Fail fail)
    {
        return failMapper.updateFail(fail);
    }

    /**
     * 批量删除不及格学生名单
     * 
     * @param courseIds 需要删除的不及格学生名单主键
     * @return 结果
     */
    @Override
    public int deleteFailByCourseIds(Integer[] courseIds)
    {
        return failMapper.deleteFailByCourseIds(courseIds);
    }

    /**
     * 删除不及格学生名单信息
     * 
     * @param courseId 不及格学生名单主键
     * @return 结果
     */
    @Override
    public int deleteFailByCourseId(Integer courseId)
    {
        return failMapper.deleteFailByCourseId(courseId);
    }

    @Override
    public List<Fail> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList){
        return  failMapper.selectEvaluationpahseByCourseIdList(courseIdList);
    }
}

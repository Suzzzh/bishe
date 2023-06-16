package com.ruoyi.bishe.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.EvaluationpahseMapper;
import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.service.IEvaluationpahseService;

/**
 * 评价环节分配Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
@Service
public class EvaluationpahseServiceImpl implements IEvaluationpahseService 
{
    @Autowired
    private EvaluationpahseMapper evaluationpahseMapper;

    /**
     * 查询评价环节分配
     * 
     * @param courseId 评价环节分配主键
     * @return 评价环节分配
     */
    @Override
    public Evaluationpahse selectEvaluationpahseByCourseId(Integer courseId)
    {
        return evaluationpahseMapper.selectEvaluationpahseByCourseId(courseId);
    }

    /**
     * 查询评价环节分配列表
     * 
     * @param evaluationpahse 评价环节分配
     * @return 评价环节分配
     */
    @Override
    public List<Evaluationpahse> selectEvaluationpahseList(Evaluationpahse evaluationpahse)
    {
        return evaluationpahseMapper.selectEvaluationpahseList(evaluationpahse);
    }

    /**
     * 新增评价环节分配
     * 
     * @param evaluationpahse 评价环节分配
     * @return 结果
     */
    @Override
    public int insertEvaluationpahse(Evaluationpahse evaluationpahse)
    {
        return evaluationpahseMapper.insertEvaluationpahse(evaluationpahse);
    }

    /**
     * 修改评价环节分配
     * 
     * @param evaluationpahse 评价环节分配
     * @return 结果
     */
    @Override
    public int updateEvaluationpahse(Evaluationpahse evaluationpahse)
    {
        return evaluationpahseMapper.updateEvaluationpahse(evaluationpahse);
    }

    /**
     * 批量删除评价环节分配
     * 
     * @param courseIds 需要删除的评价环节分配主键
     * @return 结果
     */
    @Override
    public int deleteEvaluationpahseByCourseIds(Integer[] courseIds)
    {
        return evaluationpahseMapper.deleteEvaluationpahseByCourseIds(courseIds);
    }

    /**
     * 删除评价环节分配信息
     * 
     * @param courseId 评价环节分配主键
     * @return 结果
     */
    @Override
    public int deleteEvaluationpahseByCourseId(Integer courseId)
    {
        return evaluationpahseMapper.deleteEvaluationpahseByCourseId(courseId);
    }

    public List<Evaluationpahse> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList) {

        return evaluationpahseMapper.selectEvaluationpahseByCourseIdList(courseIdList);
    }
}

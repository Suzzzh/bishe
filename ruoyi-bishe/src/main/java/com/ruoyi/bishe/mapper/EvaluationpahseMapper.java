package com.ruoyi.bishe.mapper;

import java.util.List;
import com.ruoyi.bishe.domain.Evaluationpahse;

/**
 * 评价环节分配Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public interface EvaluationpahseMapper 
{
    /**
     * 查询评价环节分配
     * 
     * @param courseId 评价环节分配主键
     * @return 评价环节分配
     */
    public Evaluationpahse selectEvaluationpahseByCourseId(Integer courseId);

    /**
     * 查询评价环节分配列表
     * 
     * @param evaluationpahse 评价环节分配
     * @return 评价环节分配集合
     */
    public List<Evaluationpahse> selectEvaluationpahseList(Evaluationpahse evaluationpahse);

    /**
     * 新增评价环节分配
     * 
     * @param evaluationpahse 评价环节分配
     * @return 结果
     */
    public int insertEvaluationpahse(Evaluationpahse evaluationpahse);

    /**
     * 修改评价环节分配
     * 
     * @param evaluationpahse 评价环节分配
     * @return 结果
     */
    public int updateEvaluationpahse(Evaluationpahse evaluationpahse);

    /**
     * 删除评价环节分配
     * 
     * @param courseId 评价环节分配主键
     * @return 结果
     */
    public int deleteEvaluationpahseByCourseId(Integer courseId);

    /**
     * 批量删除评价环节分配
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEvaluationpahseByCourseIds(Integer[] courseIds);


    public List<Evaluationpahse> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList);
}

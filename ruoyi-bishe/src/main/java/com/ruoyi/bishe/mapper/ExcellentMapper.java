package com.ruoyi.bishe.mapper;

import java.util.List;
import com.ruoyi.bishe.domain.Excellent;

/**
 * 优秀学生名单Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
public interface ExcellentMapper 
{
    /**
     * 查询优秀学生名单
     * 
     * @param courseId 优秀学生名单主键
     * @return 优秀学生名单
     */
    public Excellent selectExcellentByCourseId(Integer courseId);

    /**
     * 查询优秀学生名单列表
     * 
     * @param excellent 优秀学生名单
     * @return 优秀学生名单集合
     */
    public List<Excellent> selectExcellentList(Excellent excellent);

    /**
     * 新增优秀学生名单
     * 
     * @param excellent 优秀学生名单
     * @return 结果
     */
    public int insertExcellent(Excellent excellent);

    /**
     * 修改优秀学生名单
     * 
     * @param excellent 优秀学生名单
     * @return 结果
     */
    public int updateExcellent(Excellent excellent);

    /**
     * 删除优秀学生名单
     * 
     * @param courseId 优秀学生名单主键
     * @return 结果
     */
    public int deleteExcellentByCourseId(Integer courseId);

    /**
     * 批量删除优秀学生名单
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExcellentByCourseIds(Integer[] courseIds);

    public List<Excellent> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList);
}

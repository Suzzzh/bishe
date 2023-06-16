package com.ruoyi.bishe.service;

import java.util.List;
import com.ruoyi.bishe.domain.Fail;

/**
 * 不及格学生名单Service接口
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
public interface IFailService 
{
    /**
     * 查询不及格学生名单
     * 
     * @param courseId 不及格学生名单主键
     * @return 不及格学生名单
     */
    public Fail selectFailByCourseId(Integer courseId);

    /**
     * 查询不及格学生名单列表
     * 
     * @param fail 不及格学生名单
     * @return 不及格学生名单集合
     */
    public List<Fail> selectFailList(Fail fail);

    /**
     * 新增不及格学生名单
     * 
     * @param fail 不及格学生名单
     * @return 结果
     */
    public int insertFail(Fail fail);

    /**
     * 修改不及格学生名单
     * 
     * @param fail 不及格学生名单
     * @return 结果
     */
    public int updateFail(Fail fail);

    /**
     * 批量删除不及格学生名单
     * 
     * @param courseIds 需要删除的不及格学生名单主键集合
     * @return 结果
     */
    public int deleteFailByCourseIds(Integer[] courseIds);

    /**
     * 删除不及格学生名单信息
     * 
     * @param courseId 不及格学生名单主键
     * @return 结果
     */
    public int deleteFailByCourseId(Integer courseId);

    public List<Fail> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList);
}

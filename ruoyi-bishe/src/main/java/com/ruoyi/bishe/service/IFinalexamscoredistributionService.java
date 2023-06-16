package com.ruoyi.bishe.service;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.domain.Finalexamscoredistribution;

/**
 * 期末考试各题支撑目标分数分配Service接口
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public interface IFinalexamscoredistributionService 
{
    /**
     * 查询期末考试各题支撑目标分数分配
     * 
     * @param courseId 期末考试各题支撑目标分数分配主键
     * @return 期末考试各题支撑目标分数分配
     */
    public Finalexamscoredistribution selectFinalexamscoredistributionByCourseId(Integer courseId,String courseObjectives);

    /**
     * 查询期末考试各题支撑目标分数分配列表
     * 
     * @param finalexamscoredistribution 期末考试各题支撑目标分数分配
     * @return 期末考试各题支撑目标分数分配集合
     */
    public List<Finalexamscoredistribution> selectFinalexamscoredistributionList(Finalexamscoredistribution finalexamscoredistribution);

    /**
     * 新增期末考试各题支撑目标分数分配
     * 
     * @param finalexamscoredistribution 期末考试各题支撑目标分数分配
     * @return 结果
     */
    public int insertFinalexamscoredistribution(Finalexamscoredistribution finalexamscoredistribution);

    /**
     * 修改期末考试各题支撑目标分数分配
     * 
     * @param finalexamscoredistribution 期末考试各题支撑目标分数分配
     * @return 结果
     */
    public int updateFinalexamscoredistribution(Finalexamscoredistribution finalexamscoredistribution);

    /**
     * 批量删除期末考试各题支撑目标分数分配
     * 
     * @param courseIds 需要删除的期末考试各题支撑目标分数分配主键集合
     * @return 结果
     */
    public int deleteFinalexamscoredistributionByCourseIds(Integer[] courseIds);

    /**
     * 删除期末考试各题支撑目标分数分配信息
     * 
     * @param courseId 期末考试各题支撑目标分数分配主键
     * @return 结果
     */
    public int deleteFinalexamscoredistributionByCourseIdAndObj(Integer courseId,String courseObjectives);

    /**
     * 根据课号和课程目标来查询期末考试各题支撑目标分数
     * @param courseId  课号
     * @param courseObjectives  课程目标
     * @return
     */
    public Finalexamscoredistribution selectMaxScoByCourseIdandObjectives(Integer courseId, String courseObjectives);


    public BigDecimal countFirQue(Integer courseId);
    public BigDecimal countSecQue(Integer courseId);
    public BigDecimal countThiQue(Integer courseId);
    public BigDecimal countFouQue(Integer courseId);
    public BigDecimal countFifQue(Integer courseId);
    public BigDecimal countSixQue(Integer courseId);
    public BigDecimal countSevQue(Integer courseId);
    public BigDecimal countEigQue(Integer courseId);
    public BigDecimal countNinQue(Integer courseId);
    public BigDecimal countTenQue(Integer courseId);

    public List<Finalexamscoredistribution> selectFinalexamscoredistributionByCourseIdList(List<Integer> courseIdList);
}

package com.ruoyi.bishe.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.FinalexamscoredistributionMapper;
import com.ruoyi.bishe.domain.Finalexamscoredistribution;
import com.ruoyi.bishe.service.IFinalexamscoredistributionService;

/**
 * 期末考试各题支撑目标分数分配Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
@Service
public class FinalexamscoredistributionServiceImpl implements IFinalexamscoredistributionService 
{
    @Autowired
    private FinalexamscoredistributionMapper finalexamscoredistributionMapper;

    /**
     * 查询期末考试各题支撑目标分数分配
     * 
     * @param courseId 期末考试各题支撑目标分数分配主键
     * @return 期末考试各题支撑目标分数分配
     */
    @Override
    public Finalexamscoredistribution selectFinalexamscoredistributionByCourseId(Integer courseId, String courseObjectives)
    {
        return finalexamscoredistributionMapper.selectFinalexamscoredistributionByCourseId(courseId,courseObjectives);
    }



    /**
     * 查询期末考试各题支撑目标分数分配列表
     * 
     * @param finalexamscoredistribution 期末考试各题支撑目标分数分配
     * @return 期末考试各题支撑目标分数分配
     */
    @Override
    public List<Finalexamscoredistribution> selectFinalexamscoredistributionList(Finalexamscoredistribution finalexamscoredistribution)
    {
        return finalexamscoredistributionMapper.selectFinalexamscoredistributionList(finalexamscoredistribution);
    }

    /**
     * 新增期末考试各题支撑目标分数分配
     * 
     * @param finalexamscoredistribution 期末考试各题支撑目标分数分配
     * @return 结果
     */
    @Override
    public int insertFinalexamscoredistribution(Finalexamscoredistribution finalexamscoredistribution)
    {
        return finalexamscoredistributionMapper.insertFinalexamscoredistribution(finalexamscoredistribution);
    }

    /**
     * 修改期末考试各题支撑目标分数分配
     * 
     * @param finalexamscoredistribution 期末考试各题支撑目标分数分配
     * @return 结果
     */
    @Override
    public int updateFinalexamscoredistribution(Finalexamscoredistribution finalexamscoredistribution)
    {

        return finalexamscoredistributionMapper.updateFinalexamscoredistribution(finalexamscoredistribution);
    }

    /**
     * 批量删除期末考试各题支撑目标分数分配
     * 
     * @param courseIds 需要删除的期末考试各题支撑目标分数分配主键
     * @return 结果
     */
    @Override
    public int deleteFinalexamscoredistributionByCourseIds(Integer[] courseIds)
    {
        return finalexamscoredistributionMapper.deleteFinalexamscoredistributionByCourseIds(courseIds);
    }

    /**
     * 删除期末考试各题支撑目标分数分配信息
     * 
     * @param courseId 期末考试各题支撑目标分数分配主键
     * @return 结果
     */
    @Override
    public int deleteFinalexamscoredistributionByCourseIdAndObj(Integer courseId,String courseObjectives)
    {
        return finalexamscoredistributionMapper.deleteFinalexamscoredistributionByCourseId(courseId,courseObjectives);
    }

    @Override
    public Finalexamscoredistribution selectMaxScoByCourseIdandObjectives(Integer courseId, String courseObjectives) {

        return finalexamscoredistributionMapper.selectFinalexamscoredistributionByCourseId(courseId,courseObjectives);
    }
    @Override
    public BigDecimal countFirQue(Integer courseId){
        return finalexamscoredistributionMapper.countFirQue(courseId);
    }

    @Override
    public BigDecimal countSecQue(Integer courseId) {
        return finalexamscoredistributionMapper.countSecQue(courseId);
    }

    @Override
    public BigDecimal countThiQue(Integer courseId) {
        return finalexamscoredistributionMapper.countThiQue(courseId);
    }

    @Override
    public BigDecimal countFouQue(Integer courseId) {
        return finalexamscoredistributionMapper.countFouQue(courseId);
    }

    @Override
    public BigDecimal countFifQue(Integer courseId) {
        return finalexamscoredistributionMapper.countFifQue(courseId);
    }

    @Override
    public BigDecimal countSixQue(Integer courseId) {
        return finalexamscoredistributionMapper.countSixQue(courseId);
    }

    @Override
    public BigDecimal countSevQue(Integer courseId) {
        return finalexamscoredistributionMapper.countSevQue(courseId);
    }

    @Override
    public BigDecimal countEigQue(Integer courseId) {
        return finalexamscoredistributionMapper.countEigQue(courseId);
    }

    @Override
    public BigDecimal countNinQue(Integer courseId) {
        return finalexamscoredistributionMapper.countNinQue(courseId);
    }

    @Override
    public BigDecimal countTenQue(Integer courseId) {
        return finalexamscoredistributionMapper.countTenQue(courseId);
    }

    public List<Finalexamscoredistribution> selectFinalexamscoredistributionByCourseIdList(List<Integer> courseIdList){
        return finalexamscoredistributionMapper.selectFinalexamscoredistributionByCourseIdList(courseIdList);
    }
}

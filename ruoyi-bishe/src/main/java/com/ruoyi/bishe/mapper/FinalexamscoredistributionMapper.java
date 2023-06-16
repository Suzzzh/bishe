package com.ruoyi.bishe.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.bishe.domain.Finalexamscoredistribution;
import org.apache.ibatis.annotations.Param;

/**
 * 期末考试各题支撑目标分数分配Mapper接口
 *
 * @author suzhonghui
 * @date 2023-03-22
 */
public interface FinalexamscoredistributionMapper {
    /**
     * 查询期末考试各题支撑目标分数分配
     *
     * @param courseId 期末考试各题支撑目标分数分配主键
     * @return 期末考试各题支撑目标分数分配
     */
    public Finalexamscoredistribution selectFinalexamscoredistributionByCourseId(@Param("courseId") Integer courseId,@Param("courseObjectives")String courseObjectives);

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
     * 删除期末考试各题支撑目标分数分配
     *
     * @param courseId 期末考试各题支撑目标分数分配主键
     * @return 结果
     */
    public int deleteFinalexamscoredistributionByCourseId(@Param("courseId") Integer courseId, @Param("courseObjectives") String courseObjectives);

    /**
     * 批量删除期末考试各题支撑目标分数分配
     *
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinalexamscoredistributionByCourseIds(Integer[] courseIds);

    /**
     * 根据id和课程目标来查询期末考试各题支撑目标分数分配
     *
     * @param courseId         课号
     * @param courseObjectives 课程目标
     * @return
     */
    public Finalexamscoredistribution selectMaxScoByCourseIdandObjectives(@Param("courseId") Integer courseId, @Param("courseObjectives") String courseObjectives);


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

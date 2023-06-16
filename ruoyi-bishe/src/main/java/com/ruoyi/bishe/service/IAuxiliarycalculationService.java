package com.ruoyi.bishe.service;

import java.util.List;
import com.ruoyi.bishe.domain.Auxiliarycalculation;

/**
 * 辅助计算表Service接口
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public interface IAuxiliarycalculationService 
{
    /**
     * 查询辅助计算表
     * 
     * @param courseId 辅助计算表主键
     * @return 辅助计算表
     */
    public Auxiliarycalculation selectAuxiliarycalculationByCourseId(Integer courseId);

    /**
     * 查询辅助计算表列表
     * 
     * @param auxiliarycalculation 辅助计算表
     * @return 辅助计算表集合
     */
    public List<Auxiliarycalculation> selectAuxiliarycalculationList(Auxiliarycalculation auxiliarycalculation);

    /**
     * 新增辅助计算表
     * 
     * @param auxiliarycalculation 辅助计算表
     * @return 结果
     */
    public int insertAuxiliarycalculation(Auxiliarycalculation auxiliarycalculation);

    /**
     * 修改辅助计算表
     * 
     * @param auxiliarycalculation 辅助计算表
     * @return 结果
     */
    public int updateAuxiliarycalculation(Auxiliarycalculation auxiliarycalculation);

    /**
     * 批量删除辅助计算表
     * 
     * @param courseIds 需要删除的辅助计算表主键集合
     * @return 结果
     */
    public int deleteAuxiliarycalculationByCourseIds(Integer[] courseIds);

    /**
     * 删除辅助计算表信息
     * 
     * @param courseId 辅助计算表主键
     * @return 结果
     */
    public int deleteAuxiliarycalculationByCourseId(Integer courseId);

    public List<Auxiliarycalculation> selectAuxiliarycalculationByCourseIdList(List<Integer> courseIdList);
}

package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.AuxiliarycalculationMapper;
import com.ruoyi.bishe.domain.Auxiliarycalculation;
import com.ruoyi.bishe.service.IAuxiliarycalculationService;

/**
 * 辅助计算表Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
@Service
public class AuxiliarycalculationServiceImpl implements IAuxiliarycalculationService 
{
    @Autowired
    private AuxiliarycalculationMapper auxiliarycalculationMapper;

    /**
     * 查询辅助计算表
     * 
     * @param courseId 辅助计算表主键
     * @return 辅助计算表
     */
    @Override
    public Auxiliarycalculation selectAuxiliarycalculationByCourseId(Integer courseId)
    {
        return auxiliarycalculationMapper.selectAuxiliarycalculationByCourseId(courseId);
    }

    /**
     * 查询辅助计算表列表
     * 
     * @param auxiliarycalculation 辅助计算表
     * @return 辅助计算表
     */
    @Override
    public List<Auxiliarycalculation> selectAuxiliarycalculationList(Auxiliarycalculation auxiliarycalculation)
    {
        return auxiliarycalculationMapper.selectAuxiliarycalculationList(auxiliarycalculation);
    }

    /**
     * 新增辅助计算表
     * 
     * @param auxiliarycalculation 辅助计算表
     * @return 结果
     */
    @Override
    public int insertAuxiliarycalculation(Auxiliarycalculation auxiliarycalculation)
    {
        return auxiliarycalculationMapper.insertAuxiliarycalculation(auxiliarycalculation);
    }

    /**
     * 修改辅助计算表
     * 
     * @param auxiliarycalculation 辅助计算表
     * @return 结果
     */
    @Override
    public int updateAuxiliarycalculation(Auxiliarycalculation auxiliarycalculation)
    {
        return auxiliarycalculationMapper.updateAuxiliarycalculation(auxiliarycalculation);
    }

    /**
     * 批量删除辅助计算表
     * 
     * @param courseIds 需要删除的辅助计算表主键
     * @return 结果
     */
    @Override
    public int deleteAuxiliarycalculationByCourseIds(Integer[] courseIds)
    {
        return auxiliarycalculationMapper.deleteAuxiliarycalculationByCourseIds(courseIds);
    }

    /**
     * 删除辅助计算表信息
     * 
     * @param courseId 辅助计算表主键
     * @return 结果
     */
    @Override
    public int deleteAuxiliarycalculationByCourseId(Integer courseId)
    {
        return auxiliarycalculationMapper.deleteAuxiliarycalculationByCourseId(courseId);
    }
    @Override
    public List<Auxiliarycalculation> selectAuxiliarycalculationByCourseIdList(List<Integer> courseIdList) {

        return auxiliarycalculationMapper.selectAuxiliarycalculationByCourseIdList(courseIdList);
    }
}

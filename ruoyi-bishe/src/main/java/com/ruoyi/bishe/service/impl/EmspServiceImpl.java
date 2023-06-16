package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.EmspMapper;
import com.ruoyi.bishe.domain.Emsp;
import com.ruoyi.bishe.service.IEmspService;

/**
 * 评价方式及成绩比例Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
@Service
public class EmspServiceImpl implements IEmspService 
{
    @Autowired
    private EmspMapper emspMapper;

    /**
     * 查询评价方式及成绩比例
     * 
     * @param courseId 评价方式及成绩比例主键
     * @return 评价方式及成绩比例
     */
    @Override
    public Emsp selectEmspByCourseId(Integer courseId, String courseObjectives)
    {
        return emspMapper.selectEmspByCourseId(courseId,courseObjectives);
    }

    /**
     * 查询评价方式及成绩比例列表
     * 
     * @param emsp 评价方式及成绩比例
     * @return 评价方式及成绩比例
     */
    @Override
    public List<Emsp> selectEmspList(Emsp emsp)
    {
        return emspMapper.selectEmspList(emsp);
    }

    /**
     * 新增评价方式及成绩比例
     * 
     * @param emsp 评价方式及成绩比例
     * @return 结果
     */
    @Override
    public int insertEmsp(Emsp emsp)
    {
        return emspMapper.insertEmsp(emsp);
    }

    /**
     * 修改评价方式及成绩比例
     * 
     * @param emsp 评价方式及成绩比例
     * @return 结果
     */
    @Override
    public int updateEmsp(Emsp emsp)
    {
        return emspMapper.updateEmsp(emsp);
    }

    /**
     * 批量删除评价方式及成绩比例
     * 
     * @param courseIds 需要删除的评价方式及成绩比例主键
     * @return 结果
     */
    @Override
    public int deleteEmspByCourseIds(Integer[] courseIds)
    {
        return emspMapper.deleteEmspByCourseIds(courseIds);
    }

    /**
     * 删除评价方式及成绩比例信息
     * 
     * @param courseId 评价方式及成绩比例主键
     * @return 结果
     */
    @Override
    public int deleteEmspByCourseId(Integer courseId)
    {
        return emspMapper.deleteEmspByCourseId(courseId);
    }

    @Override
    public int selectCount(Integer courseId) {
        return emspMapper.selectCount(courseId);
    }
    @Override
    public int deleteEmspByCourseIdAndObj(Integer courseId, String courseObjectives){
        return emspMapper.deleteEmspByCourseIdAndObj(courseId,courseObjectives);
    }
    @Override
    public List<Emsp> selectEmspByCourseIdList(List<Integer> courseIdList) {
        return emspMapper.selectEmspByCourseIdList(courseIdList);
    }
}

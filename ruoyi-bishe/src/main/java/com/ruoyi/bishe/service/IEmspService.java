package com.ruoyi.bishe.service;

import java.util.List;
import com.ruoyi.bishe.domain.Emsp;

/**
 * 评价方式及成绩比例Service接口
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public interface IEmspService 
{
    /**
     * 查询评价方式及成绩比例
     * 
     * @param courseId 评价方式及成绩比例主键
     * @return 评价方式及成绩比例
     */
    public Emsp selectEmspByCourseId(Integer courseId, String courseObjectives);

    /**
     * 查询评价方式及成绩比例列表
     * 
     * @param emsp 评价方式及成绩比例
     * @return 评价方式及成绩比例集合
     */
    public List<Emsp> selectEmspList(Emsp emsp);

    /**
     * 新增评价方式及成绩比例
     * 
     * @param emsp 评价方式及成绩比例
     * @return 结果
     */
    public int insertEmsp(Emsp emsp);

    /**
     * 修改评价方式及成绩比例
     * 
     * @param emsp 评价方式及成绩比例
     * @return 结果
     */
    public int updateEmsp(Emsp emsp);

    /**
     * 批量删除评价方式及成绩比例
     * 
     * @param courseIds 需要删除的评价方式及成绩比例主键集合
     * @return 结果
     */
    public int deleteEmspByCourseIds(Integer[] courseIds);

    /**
     * 删除评价方式及成绩比例信息
     * 
     * @param courseId 评价方式及成绩比例主键
     * @return 结果
     */
    public int deleteEmspByCourseId(Integer courseId);

    /**
     * 查询课号有几个课程目标
     *
     * @param courseId 课号
     * @return
     */
    public int selectCount(Integer courseId);

    public int deleteEmspByCourseIdAndObj(Integer courseId, String courseObjectives);

    public List<Emsp> selectEmspByCourseIdList(List<Integer> courseIdList);
}

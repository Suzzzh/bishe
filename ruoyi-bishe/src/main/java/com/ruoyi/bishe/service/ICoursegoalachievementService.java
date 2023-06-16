package com.ruoyi.bishe.service;

import java.util.List;
import com.ruoyi.bishe.domain.Coursegoalachievement;

/**
 * 课程目标达成度表Service接口
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public interface ICoursegoalachievementService 
{
    /**
     * 查询课程目标达成度表
     * 
     * @param courseId 课程目标达成度表主键
     * @return 课程目标达成度表
     */
    public Coursegoalachievement selectCoursegoalachievementByCourseId(Integer courseId);

    /**
     * 查询课程目标达成度表列表
     * 
     * @param coursegoalachievement 课程目标达成度表
     * @return 课程目标达成度表集合
     */
    public List<Coursegoalachievement> selectCoursegoalachievementList(Coursegoalachievement coursegoalachievement);

    /**
     * 新增课程目标达成度表
     * 
     * @param coursegoalachievement 课程目标达成度表
     * @return 结果
     */
    public int insertCoursegoalachievement(Coursegoalachievement coursegoalachievement);

    /**
     * 修改课程目标达成度表
     * 
     * @param coursegoalachievement 课程目标达成度表
     * @return 结果
     */
    public int updateCoursegoalachievement(Coursegoalachievement coursegoalachievement);

    /**
     * 批量删除课程目标达成度表
     * 
     * @param courseIds 需要删除的课程目标达成度表主键集合
     * @return 结果
     */
    public int deleteCoursegoalachievementByCourseIds(Integer[] courseIds);

    /**
     * 删除课程目标达成度表信息
     * 
     * @param courseId 课程目标达成度表主键
     * @return 结果
     */
    public int deleteCoursegoalachievementByCourseId(Integer courseId);


    public Coursegoalachievement selectDataByCourseIdandCourseObjectives(Integer courseId,String CourseObjectives);

    public int deleteCoursegoalachievementByCourseIdAndObj(Integer courseId, String courseObjectives);

    public List<Coursegoalachievement> selectCoursegoalachievementByCourseIdList(List<Integer> courseIdList);
}

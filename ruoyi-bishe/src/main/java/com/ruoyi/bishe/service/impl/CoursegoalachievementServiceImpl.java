package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.CoursegoalachievementMapper;
import com.ruoyi.bishe.domain.Coursegoalachievement;
import com.ruoyi.bishe.service.ICoursegoalachievementService;

/**
 * 课程目标达成度表Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@Service
public class CoursegoalachievementServiceImpl implements ICoursegoalachievementService 
{
    @Autowired
    private CoursegoalachievementMapper coursegoalachievementMapper;

    /**
     * 查询课程目标达成度表
     * 
     * @param courseId 课程目标达成度表主键
     * @return 课程目标达成度表
     */
    @Override
    public Coursegoalachievement selectCoursegoalachievementByCourseId(Integer courseId)
    {
        return coursegoalachievementMapper.selectCoursegoalachievementByCourseId(courseId);
    }

    /**
     * 查询课程目标达成度表列表
     * 
     * @param coursegoalachievement 课程目标达成度表
     * @return 课程目标达成度表
     */
    @Override
    public List<Coursegoalachievement> selectCoursegoalachievementList(Coursegoalachievement coursegoalachievement)
    {
        return coursegoalachievementMapper.selectCoursegoalachievementList(coursegoalachievement);
    }

    /**
     * 新增课程目标达成度表
     * 
     * @param coursegoalachievement 课程目标达成度表
     * @return 结果
     */
    @Override
    public int insertCoursegoalachievement(Coursegoalachievement coursegoalachievement)
    {
        return coursegoalachievementMapper.insertCoursegoalachievement(coursegoalachievement);
    }

    /**
     * 修改课程目标达成度表
     * 
     * @param coursegoalachievement 课程目标达成度表
     * @return 结果
     */
    @Override
    public int updateCoursegoalachievement(Coursegoalachievement coursegoalachievement)
    {
        return coursegoalachievementMapper.updateCoursegoalachievement(coursegoalachievement);
    }

    /**
     * 批量删除课程目标达成度表
     * 
     * @param courseIds 需要删除的课程目标达成度表主键
     * @return 结果
     */
    @Override
    public int deleteCoursegoalachievementByCourseIds(Integer[] courseIds)
    {
        return coursegoalachievementMapper.deleteCoursegoalachievementByCourseIds(courseIds);
    }

    /**
     * 删除课程目标达成度表信息
     * 
     * @param courseId 课程目标达成度表主键
     * @return 结果
     */
    @Override
    public int deleteCoursegoalachievementByCourseId(Integer courseId)
    {
        return coursegoalachievementMapper.deleteCoursegoalachievementByCourseId(courseId);
    }

    @Override
    public Coursegoalachievement selectDataByCourseIdandCourseObjectives(Integer courseId, String courseObjectives) {
        return coursegoalachievementMapper.selectDataByCourseIdandCourseObjectives( courseId,courseObjectives) ;
    }

    public int deleteCoursegoalachievementByCourseIdAndObj(Integer courseId, String courseObjectives) {

        return coursegoalachievementMapper.deleteCoursegoalachievementByCourseIdAndObj(courseId,courseObjectives);
    }

    public List<Coursegoalachievement> selectCoursegoalachievementByCourseIdList(List<Integer> courseIdList){
        return coursegoalachievementMapper.selectCoursegoalachievementByCourseIdList(courseIdList);
    }
}

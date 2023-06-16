package com.ruoyi.bishe.mapper;

import java.util.List;
import com.ruoyi.bishe.domain.Course;

/**
 * 总课程表Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-03-19
 */
public interface CourseMapper 
{
    /**
     * 查询总课程表
     * 
     * @param courseId 总课程表主键
     * @return 总课程表
     */
    public Course selectCourseByCourseId(Integer courseId);

    /**
     * 查询总课程表列表
     * 
     * @param course 总课程表
     * @return 总课程表集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增总课程表
     * 
     * @param course 总课程表
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改总课程表
     * 
     * @param course 总课程表
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 删除总课程表
     * 
     * @param courseId 总课程表主键
     * @return 结果
     */
    public int deleteCourseByCourseId(Integer courseId);

    /**
     * 批量删除总课程表
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseByCourseIds(Integer[] courseIds);

    public String selectMajor(Long deptId);

    public String selectCourseNameById(Integer courseId);

    public String selectCourseMajorById(Integer courseId);

    public List<Integer> selectCourseIdListByName(String teacherName);

    public List<Integer> selectCourseIdListByMajor(String major);
}

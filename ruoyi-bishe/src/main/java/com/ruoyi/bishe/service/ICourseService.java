package com.ruoyi.bishe.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import com.ruoyi.bishe.domain.Course;
import com.ruoyi.bishe.domain.Zhiliang;
import fr.opensagres.xdocreport.core.XDocReportException;

import javax.servlet.http.HttpServletResponse;

/**
 * 总课程表Service接口
 * 
 * @author suzhonghui
 * @date 2023-03-19
 */
public interface ICourseService 
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
     * 批量删除总课程表
     * 
     * @param courseIds 需要删除的总课程表主键集合
     * @return 结果
     */
    public int deleteCourseByCourseIds(Integer[] courseIds);

    /**
     * 删除总课程表信息
     * 
     * @param courseId 总课程表主键
     * @return 结果
     */
    public int deleteCourseByCourseId(Integer courseId);


    public void downloadZhiliang(HttpServletResponse response, Zhiliang zhiliang) throws XDocReportException, IOException;

    public String selectMajor(Long deptId);


    public List<Integer> selectCourseIdListByName(String teachername);

    public List<Integer> selectCourseIdListByMajor(String major);
}

package com.ruoyi.bishe.mapper;

import java.util.List;
import com.ruoyi.bishe.domain.Emsp;
import org.apache.ibatis.annotations.Param;

/**
 * 评价方式及成绩比例Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public interface EmspMapper 
{
    /**
     * 查询评价方式及成绩比例
     * 
     * @param courseId 评价方式及成绩比例主键
     * @return 评价方式及成绩比例
     */
    public Emsp selectEmspByCourseId(@Param("courseId") Integer courseId, @Param("courseObjectives")String courseObjectives);

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
     * 删除评价方式及成绩比例
     * 
     * @param courseId 评价方式及成绩比例主键
     * @return 结果
     */
    public int deleteEmspByCourseId(Integer courseId);

    /**
     * 批量删除评价方式及成绩比例
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmspByCourseIds(Integer[] courseIds);

    public int selectCount(Integer courseId);

    public int deleteEmspByCourseIdAndObj(@Param("courseId") Integer courseId, @Param("courseObjectives") String courseObjectives);

    public List<Emsp> selectEmspByCourseIdList(List<Integer> courseIdList);
}

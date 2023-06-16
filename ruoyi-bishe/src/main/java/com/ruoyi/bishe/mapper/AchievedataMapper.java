package com.ruoyi.bishe.mapper;

import java.util.List;
import com.ruoyi.bishe.domain.Achievedata;
import org.apache.ibatis.annotations.Param;

/**
 * 生成达成数据Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
public interface AchievedataMapper 
{
    /**
     * 查询生成达成数据
     * 
     * @param id 生成达成数据主键
     * @return 生成达成数据
     */
    public Achievedata selectAchievedataById(Integer id);

    /**
     * 查询生成达成数据列表
     * 
     * @param achievedata 生成达成数据
     * @return 生成达成数据集合
     */
    public List<Achievedata> selectAchievedataList(Achievedata achievedata);

    /**
     * 新增生成达成数据
     * 
     * @param achievedata 生成达成数据
     * @return 结果
     */
    public int insertAchievedata(Achievedata achievedata);

    /**
     * 修改生成达成数据
     * 
     * @param achievedata 生成达成数据
     * @return 结果
     */
    public int updateAchievedata(Achievedata achievedata);

    /**
     * 删除生成达成数据
     * 
     * @param id 生成达成数据主键
     * @return 结果
     */
    public int deleteAchievedataById(Integer id);

    /**
     * 批量删除生成达成数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAchievedataByIds(Integer[] ids);

    public int deleteAchievedataByIdAndNum(@Param("id")Integer id, @Param("studentNumber") String studentNumber);

    public List<Achievedata> selectAchievedataByCourseIdList(List<Integer> courseIdList);
}

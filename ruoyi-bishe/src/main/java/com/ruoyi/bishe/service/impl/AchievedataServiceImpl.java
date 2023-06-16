package com.ruoyi.bishe.service.impl;

import java.util.List;

import com.ruoyi.bishe.domain.Evaluationpahse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.AchievedataMapper;
import com.ruoyi.bishe.domain.Achievedata;
import com.ruoyi.bishe.service.IAchievedataService;

/**
 * 生成达成数据Service业务层处理
 *
 * @author suzhonghui
 * @date 2023-04-03
 */
@Service
public class AchievedataServiceImpl implements IAchievedataService {
    @Autowired
    private AchievedataMapper achievedataMapper;

    /**
     * 查询生成达成数据
     *
     * @param id 生成达成数据主键
     * @return 生成达成数据
     */
    @Override
    public Achievedata selectAchievedataById(Integer id) {
        return achievedataMapper.selectAchievedataById(id);
    }

    /**
     * 查询生成达成数据列表
     *
     * @param achievedata 生成达成数据
     * @return 生成达成数据
     */
    @Override
    public List<Achievedata> selectAchievedataList(Achievedata achievedata) {
        return achievedataMapper.selectAchievedataList(achievedata);
    }

    /**
     * 新增生成达成数据
     *
     * @param achievedata 生成达成数据
     * @return 结果
     */
    @Override
    public int insertAchievedata(Achievedata achievedata) {
        return achievedataMapper.insertAchievedata(achievedata);
    }

    /**
     * 修改生成达成数据
     *
     * @param achievedata 生成达成数据
     * @return 结果
     */
    @Override
    public int updateAchievedata(Achievedata achievedata) {
        return achievedataMapper.updateAchievedata(achievedata);
    }

    /**
     * 批量删除生成达成数据
     *
     * @param ids 需要删除的生成达成数据主键
     * @return 结果
     */
    @Override
    public int deleteAchievedataByIds(Integer[] ids) {
        return achievedataMapper.deleteAchievedataByIds(ids);
    }

    /**
     * 删除生成达成数据信息
     *
     * @param id 生成达成数据主键
     * @return 结果
     */
    @Override
    public int deleteAchievedataById(Integer id) {
        return achievedataMapper.deleteAchievedataById(id);
    }

    @Override
    public int deleteAchievedataByIdAndNum(Integer id, String num) {

        return achievedataMapper.deleteAchievedataByIdAndNum(id,num);
    }

    public List<Achievedata> selectAchievedataByCourseIdList(List<Integer> courseIdList){
        return achievedataMapper.selectAchievedataByCourseIdList(courseIdList);
    }
}

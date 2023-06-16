package com.ruoyi.bishe.service;

import java.util.List;

import com.ruoyi.bishe.domain.Inputscore;

/**
 * 输入学生成绩Service接口
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
public interface IInputscoreService 
{
    /**
     * 查询输入学生成绩
     * 
     * @param id 输入学生成绩主键
     * @return 输入学生成绩
     */
    public Inputscore selectInputscoreById(Integer id);

    /**
     * 根据课号和学号在数据库中进行查询
     * @param id
     * @param num
     * @return
     */
    public Inputscore selectInputscoreByIdandNum(Integer id,String num);
    /**
     * 查询输入学生成绩列表
     * 
     * @param inputscore 输入学生成绩
     * @return 输入学生成绩集合
     */
    public List<Inputscore> selectInputscoreList(Inputscore inputscore);

    /**
     * 新增输入学生成绩
     * 
     * @param inputscore 输入学生成绩
     * @return 结果
     */
    public int insertInputscore(Inputscore inputscore);

    /**
     * 修改输入学生成绩
     * 
     * @param inputscore 输入学生成绩
     * @return 结果
     */
    public int updateInputscore(Inputscore inputscore);

    /**
     * 批量删除输入学生成绩
     * 
     * @param ids 需要删除的输入学生成绩主键集合
     * @return 结果
     */
    public int deleteInputscoreByIds(Long[] ids);

    /**
     * 删除输入学生成绩信息
     * 
     * @param id 输入学生成绩主键
     * @return 结果
     */
    public int deleteInputscoreById(Long id);


    String importUser(List<Inputscore> userList, boolean updateSupport, String operName);


    public int deleteInputscoreByIdAndNum(Integer courseId, String studentNum);

    public List<Inputscore> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList);
}

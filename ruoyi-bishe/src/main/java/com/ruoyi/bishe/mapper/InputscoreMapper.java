package com.ruoyi.bishe.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.bishe.domain.Inputscore;
import org.apache.ibatis.annotations.Param;

/**
 * 输入学生成绩Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
public interface InputscoreMapper 
{
    /**
     * 查询输入学生成绩
     * 
     * @param id 输入学生成绩主键
     * @return 输入学生成绩
     */
    public Inputscore selectInputscoreById(Integer id);




    public Inputscore selectInputscoreByIdandNum(@Param("id")Integer id,@Param("studentNumber") String studentNumber);

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
     * 删除输入学生成绩
     * 
     * @param id 输入学生成绩主键
     * @return 结果
     */
    public int deleteInputscoreById(Long id);

    /**
     * 批量删除输入学生成绩
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInputscoreByIds(Long[] ids);


    public BigDecimal selectHighSco(Integer courseId);
    public BigDecimal selectLowSco(Integer courseId);

    public Integer selectninNum1(Integer courseId);
    public Integer selecteigNum1(Integer courseId);
    public Integer selectsevNum1(Integer courseId);
    public Integer selectsixNum1(Integer courseId);
    public Integer selectlowNum1(Integer courseId);
    public Integer selectninNum2(Integer courseId);
    public Integer selecteigNum2(Integer courseId);
    public Integer selectsevNum2(Integer courseId);
    public Integer selectsixNum2(Integer courseId);
    public Integer selectlowNum2(Integer courseId);

    public int deleteInputscoreByIdAndNum(@Param("id")Integer id,@Param("studentNumber") String studentNumber);

    public List<Inputscore> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList);
}

package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.UserMapper;
import com.ruoyi.bishe.domain.User;
import com.ruoyi.bishe.service.IUserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-19
 */
@Service
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public User selectUserByUserId(Integer userId)
    {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     * 
     * @param user 用户管理
     * @return 用户管理
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户管理
     * 
     * @param user 用户管理
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户管理
     * 
     * @param user 用户管理
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(Integer[] userIds)
    {
        return userMapper.deleteUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserId(Integer userId)
    {
        return userMapper.deleteUserByUserId(userId);
    }
}

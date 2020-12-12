package com.lick.mapper;

import com.lick.pojo.User;

import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/6 17:16
 */
public interface IUserMapper {
    public List<User> findAll();

    public User findOne(Integer id);

    public void updateUser(User user);

    public void deleteUser(User user);

    public void deleteUserById(Integer id);

    public int insertUser(User user);
}
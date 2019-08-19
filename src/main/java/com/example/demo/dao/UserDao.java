package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 22:25
 */
public interface UserDao {
    /**
     * 添加
     *
     * @param user
     * @return
     */
    @Insert("insert into user(name)values(#{user.name})")
    int addUser(@Param("user") UserEntity user);
}

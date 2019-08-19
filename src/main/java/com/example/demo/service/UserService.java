package com.example.demo.service;

import com.example.demo.entity.UserEntity;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 22:25
 */
public interface UserService {
    /**
     * add
     *
     * @param user
     * @return
     */
    int addUser(UserEntity user);

    void method1();

    void method2();

    void addRequiresNew();

    void addRequiresNewException();
    
    void addNested();

    void addNestedException();
}

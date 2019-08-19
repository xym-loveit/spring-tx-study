package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 22:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(UserEntity user) {
        return userDao.addUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method1() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("admin");
        addUser(userEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method2() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("admin");
        addUser(userEntity);
        throw new IllegalArgumentException("method2 exeception");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNew() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("admin-REQUIRES_NEW");
        addUser(userEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNewException() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("admin-REQUIRES_NEW");
        addUser(userEntity);
        throw new IllegalArgumentException("抛出异常--addRequiresNewException");
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNested() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("admin-NESTED");
        addUser(userEntity);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNestedException() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("admin-NESTED");
        addUser(userEntity);
        throw new IllegalArgumentException("抛出异常--addNestedException");
    }
}

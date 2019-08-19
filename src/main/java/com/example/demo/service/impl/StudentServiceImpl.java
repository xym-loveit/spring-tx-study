package com.example.demo.service.impl;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 20:32
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Override
    public StudentEntity findByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public int updateByName(String address, String name) {
        return studentDao.updateByName(address, name);
    }

    @Override
    public int addStudent(StudentEntity student) {
        return studentDao.addStudent(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method1() {
        LOGGER.info("method1---start----------------");
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("西湖区蒋村街道");
        studentEntity.setSex((byte) 1);
        String name = "王" + new Random().nextInt(10) + "五";
        studentEntity.setName(name);
        addStudent(studentEntity);
        //method2(name);
        LOGGER.info("method1---end----------------");
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method2(String name) {
        LOGGER.info("method2---start----------------");
        String address = "翠苑" + new Random().nextInt(5) + "区";
        updateByName(address, name);
        LOGGER.info("method2---end----------------");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNew() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("西湖区蒋村街道--REQUIRES_NEW");
        studentEntity.setSex((byte) 1);
        String name = "王" + new Random().nextInt(10) + "五";
        studentEntity.setName(name);
        addStudent(studentEntity);
    }


    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNested() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("西湖区蒋村街道--NESTED");
        studentEntity.setSex((byte) 1);
        String name = "王" + new Random().nextInt(10) + "五";
        studentEntity.setName(name);
        addStudent(studentEntity);
    }
}

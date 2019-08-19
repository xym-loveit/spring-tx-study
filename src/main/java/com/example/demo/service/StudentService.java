package com.example.demo.service;

import com.example.demo.entity.StudentEntity;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 20:32
 */
public interface StudentService {
    StudentEntity findByName(String name);

    int updateByName(String address, String name);

    int addStudent(StudentEntity student);

    void method1();

    void method2(String name);

    void addRequiresNew();

    void addNested();
}

package com.example.demo.controller;

import com.example.demo.entity.StudentEntity;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Instant;
import java.util.Random;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 20:32
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/findByName")
    public StudentEntity findByName(@RequestParam("name") String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/addStudent")
    public int addStudent() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("西湖区蒋村街道");
        studentEntity.setSex((byte) 2);
        studentEntity.setName("李" + new Random().nextInt(10) + "四");
        studentEntity.setCreateTime(Date.from(Instant.now()));
        return studentService.addStudent(studentEntity);
    }

    @GetMapping("/updateStudent")
    public int updateStudent(String name) {
        return studentService.updateByName("西湖区文新街道", name);
    }

    @GetMapping("/trans1")
    public int transaction001() {
        studentService.method1();
        return 10;
    }
}

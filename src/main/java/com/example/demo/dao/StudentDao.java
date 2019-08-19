package com.example.demo.dao;

import com.example.demo.entity.StudentEntity;
import org.apache.ibatis.annotations.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 20:32
 */
@Mapper
public interface StudentDao {

    /**
     * 查询
     *
     * @param name
     * @return
     */
    @Select("select * from t_student where name=#{name}")
    @Results(@Result(column = "create_time", property = "createTime"))
    StudentEntity findByName(@Param("name") String name);

    /**
     * 修改
     *
     * @param address
     * @param name
     * @return
     */
    @Update("update t_student set address=#{address} where name=#{name}")
    int updateByName(@Param("address") String address, @Param("name") String name);

    /**
     * 添加
     *
     * @param student
     * @return
     */
    @Insert("insert into t_student(name, sex, create_time, address)values(#{student.name}, #{student.sex}, #{student.createTime}, #{student.address})")
    int addStudent(@Param("student") StudentEntity student);

}

package com.example.demo;

import com.example.demo.service.OtherServie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private OtherServie otherServie;

    @Test
    public void t1() {
        otherServie.no_trans_throw_exception();
    }

    @Test
    public void t2() {
        otherServie.no_trans_exception();
    }

    @Test
    public void t3() {
        otherServie.trans_throw_exception();
    }

    @Test
    public void t4() {
        otherServie.trans_inner_exception();
    }

    @Test
    public void t5() {
        otherServie.trans_inner_catch_exception();
    }

    @Test
    public void t6() {
        otherServie.no_trans_throw_exception_new();
    }

    @Test
    public void t7() {
        otherServie.trans_throw_exception_new();
    }

    @Test
    public void t8() {
        otherServie.no_trans_inner_throw_exception_new();
    }

    @Test
    public void t9() {
        otherServie.trans_throw_exception_new_3();
    }

    @Test
    public void t10() {
        otherServie.no_trans_inner_throw_exception_new3();
    }

    @Test
    public void t11(){
        otherServie.no_trans_catch_inner_exception_new3();
    }

    @Test
    public void t12(){
        otherServie.no_trans_exception_nested();
    }

    @Test
    public void t13(){
        otherServie.no_trans_throw_exception_nested();
    }

    @Test
    public void t14(){
        otherServie.trans_throw_exception_nested();
    }

    @Test
    public void t15(){
        otherServie.trans_throw_exception_nested2();
    }

    @Test
    public void t16(){
        otherServie.trans_inner_throw_exception_nested();
    }

    @Test
    public void t17(){
        otherServie.trans_catch_exception_nested();
    }

    @Test
    public void t18(){
        otherServie.trans_catch_exception_nested2();
    }
}

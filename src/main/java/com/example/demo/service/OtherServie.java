package com.example.demo.service;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 22:28
 */
public interface OtherServie {

    void no_trans_throw_exception();

    void no_trans_exception();

    void trans_throw_exception();

    void trans_inner_exception();

    void trans_inner_catch_exception();

    void no_trans_throw_exception_new();

    void trans_throw_exception_new();

    void no_trans_inner_throw_exception_new();

    void trans_throw_exception_new_3();

    void no_trans_inner_throw_exception_new3();

    void no_trans_catch_inner_exception_new3();

    void no_trans_exception_nested();

    void no_trans_throw_exception_nested();

    void trans_throw_exception_nested();

    void trans_throw_exception_nested2();

    void trans_inner_throw_exception_nested();

    void trans_catch_exception_nested();

    void trans_catch_exception_nested2();
}

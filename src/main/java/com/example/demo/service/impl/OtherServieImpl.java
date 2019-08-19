package com.example.demo.service.impl;

import com.example.demo.service.OtherServie;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Propagation.NESTED：以上试验结果我们证明在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，
 * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
 *
 * @author xym
 * @create 2019-08-19 22:28
 */
@Component
public class OtherServieImpl implements OtherServie {

    public static final Logger LOGGER = LoggerFactory.getLogger(OtherServieImpl.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    /**
     * 外围没有开启事务，不会影响内部的事务提交
     */
    @Override
    public void no_trans_throw_exception() {
        studentService.method1();
        userService.method1();
        if (true) {
            throw new IllegalArgumentException("故意抛异常");
        }

    }

    /**
     * 外围开启事务,内部方法自动加入外部事务中，如果有任意一个方法抛出异常，则事务全部回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void trans_throw_exception() {
        studentService.method1();
        userService.method1();
        if (true) {
            throw new IllegalArgumentException("故意抛异常");
        }
    }

    /**
     * 外围开启事务,内部方法自动加入外部事务中，如果有任意一个方法抛出异常，则事务全部回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void trans_inner_exception() {
        studentService.method1();
        userService.method2();
    }

    /**
     * 外围开启事务,内部方法自动加入外部事务中，如果有任意一个方法抛出异常，
     * 即使使用try捕获了异常,但事务还是会全部回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void trans_inner_catch_exception() {
        studentService.method1();
        try {
            userService.method2();
        } catch (Exception e) {
            LOGGER.info("捕获异常--", e);
        }
    }

    @Override
    public void no_trans_exception() {
        studentService.method1();
        //method2抛出异常，会导致自己的事务回滚，不会影响method1的事务提交
        userService.method2();
    }

    /**
     * 外围没有开启事务，不会影响内部的事务提交
     */
    @Override
    public void no_trans_throw_exception_new() {
        studentService.addRequiresNew();
        userService.addRequiresNew();
        if (true) {
            throw new IllegalArgumentException("故意抛异常");
        }

    }

    /**
     * 外围开启事务,内部方法自动加入外部事务中，如果有任意一个方法抛出异常，则事务全部回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void trans_throw_exception_new() {
        studentService.addRequiresNew();
        userService.addRequiresNew();
        if (true) {
            throw new IllegalArgumentException("故意抛异常");
        }
    }

    /**
     * 外围没有事务,内部方法各自创建事务，如果有任意一个方法抛出异常，
     * 则只会回滚自己方法所在的事务
     */
    @Override
    public void no_trans_inner_throw_exception_new() {
        studentService.addRequiresNew();
        userService.addRequiresNewException();
    }

    /**
     * 典型的事务
     * <p>
     * 创建了3个事务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void trans_throw_exception_new_3() {
        /**
         * 加入外围事务
         */
        studentService.method1();
        /******************************/
        /**
         * 下面2个都挂起外围事务是，各自创建自己的新事务
         */
        studentService.addRequiresNew();
        userService.addRequiresNew();
        //只会影响外围事务
        throw new RuntimeException("抛异常了---");
    }


    /**
     * 由于内部的方法抛出异常，导致外面也跟着回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void no_trans_inner_throw_exception_new3() {
        studentService.method1();
        //只有addRequiresNew才会执行，其他都回滚
        userService.addRequiresNew();
        /**************************下面这个方法，异常没有被捕获所以污染了外围的事务，导致外围事务也回滚了************************/
        userService.addRequiresNewException();
    }

    /**
     * 由于内部的方法抛出异常，异常被捕获后外面事务不受影响
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void no_trans_catch_inner_exception_new3() {
        studentService.method1();
        userService.addRequiresNew();
        try {
            //当内部方法抛出异常时候，内部事务回滚，如果此时异常被外部捕获，
            // 则外围事务不受影响（外围事务正常则commit，反之rollback）
            userService.addRequiresNewException();
        } catch (Exception e) {
            LOGGER.info("no_trans_catch_inner_exception_new3s--", e);
        }
    }

    @Override
    public void no_trans_exception_nested() {
        studentService.addNested();
        userService.addNested();
        if (true) {
            throw new IllegalArgumentException("故意抛异常");
        }
    }

    @Override
    public void no_trans_throw_exception_nested() {
        studentService.addNested();
        userService.addNestedException();
    }

    @Override
    @Transactional
    public void trans_throw_exception_nested() {
        studentService.addNested();
        userService.addNested();
        if (true) {
            throw new IllegalArgumentException("故意抛异常");
        }
    }

    @Override
    @Transactional
    public void trans_throw_exception_nested2() {
        studentService.addNested();
        userService.addNested();
    }

    @Override
    @Transactional
    public void trans_inner_throw_exception_nested() {
        studentService.addNested();
        userService.addNestedException();
    }

    @Override
    @Transactional
    public void trans_catch_exception_nested() {
        studentService.addNested();
        try {
            userService.addNestedException();
        } catch (Exception e) {
            LOGGER.info("trans_catch_exception_nested", e);
        }
    }


    @Override
    @Transactional
    public void trans_catch_exception_nested2() {
        studentService.addNested();
        try {
            userService.addNestedException();
        } catch (Exception e) {
            LOGGER.info("trans_catch_exception_nested2", e);
        }
        userService.addNested();
    }
}

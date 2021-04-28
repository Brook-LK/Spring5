package com.brook.conf;

import com.brook.service.impl.TeacherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog {

    //手动创建log级别的log日志
    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    public static void main(String[] args) {
        log.info("hello TeacherServiceImpl");
        log.warn("hello log4j");
    }
}

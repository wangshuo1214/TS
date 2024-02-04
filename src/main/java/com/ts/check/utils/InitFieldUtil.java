package com.ts.check.utils;

import cn.hutool.core.util.ObjectUtil;
import com.ts.check.entity.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

public class InitFieldUtil {

    private static final Logger logger = LoggerFactory.getLogger(InitFieldUtil.class);

    public static boolean initField(Object t){

        Class clazz = t.getClass();
        //初始化主键id
        try{
            Field uuid = clazz.getDeclaredField("uuid");
            if (ObjectUtil.isNotEmpty(uuid)){
                uuid.setAccessible(true);
                uuid.set(t, UUID.randomUUID().toString());
            }
        }catch (Exception e){
            logger.info("初始化uuid属性失败，报错信息为{}", e.getMessage());
        }

        try{
            //初始化创建时间
            Field createTime = clazz.getSuperclass().getDeclaredField("createTime");
            createTime.setAccessible(true);
            createTime.set(t,new Date());
            //初始化更新时间
            Field updateTime = clazz.getSuperclass().getDeclaredField("updateTime");
            updateTime.setAccessible(true);
            updateTime.set(t,new Date());
            //初始化删除标志
            Field isDelete = clazz.getSuperclass().getDeclaredField("isDelete");
            isDelete.setAccessible(true);
            isDelete.set(t, BaseConstant.FALSE);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

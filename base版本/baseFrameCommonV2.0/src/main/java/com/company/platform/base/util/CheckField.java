package com.company.platform.base.util;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class CheckField {

	public static boolean checkFieldAllNull(Object obj) throws Exception{//判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if(val==null) {//只要有1个属性为空
                flag = false;
                break;
            }
        }
        return flag;
    }

}

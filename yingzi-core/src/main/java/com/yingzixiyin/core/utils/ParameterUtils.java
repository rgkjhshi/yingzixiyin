package com.yingzixiyin.core.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author song.shi
 * @date 15/6/10
 */

/**
 * 参数检查工具类，若参数不符合要求，则抛出IllegalArgumentException
 */
public class ParameterUtils {


    /**
     * 检查对象不为null
     *
     * @param obj 待判断对象
     */
    public static void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object can not be null");
        }
    }

    /**
     * 检查对象不为null，可以自己指定描述
     */
    public static void notNull(Object obj, String desc) {
        if (obj == null) {
            throw new IllegalArgumentException(desc);
        }
    }

    /**
     * 检查DTO对象及对象的所有成员变量不为null
     * 该方法不适用于所有对象，仅适用于自定义的DTO，即只包含普通成员那种
     *
     * @param obj
     */
    public static void allNotNull(Object obj) {
        notNull(obj);
        Class clazz = obj.getClass();
        if (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();  // 获取所有成员
            for (Field field : fields) {
                // 只检查非static成员和非primitive成员
                if (!Modifier.isStatic(field.getModifiers())  // 非static成员
                        && !field.getType().isPrimitive()) {  // 非原始类型(int)
                    field.setAccessible(true);
                    try {
                        notNull(field.get(obj), field.getName() + "can not be null");
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
        }
    }
}

package com.yingzixiyin.core.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

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
                        throw new IllegalArgumentException("对象的属性不可访问");
                    }
                }
            }
        }
    }

    /**
     * 检查DTO对象本身不是null,且有属性不是null, 即该对象的所有属性都是null时抛出异常
     * 该方法不适用于所有对象，仅适用于自定义的DTO，即只包含普通成员那种
     *
     * @param obj
     */
    public static void notAllNull(Object obj) {
        notNull(obj, "对象本身不能为null");
        Class clazz = obj.getClass();
        int flag = 0;
        if (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();  // 获取所有成员
            for (Field field : fields) {
                // 只检查非static成员和非primitive成员
                if (!Modifier.isStatic(field.getModifiers())  // 非static成员
                        && !field.getType().isPrimitive()) {  // 非原始类型(int)
                    field.setAccessible(true);
                    try {
                        if ( null != field.get(obj)) {
                            flag = 1;
                            break;
                        }
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException("对象的属性不可访问");
                    }
                }
            }
        }
        if (0 == flag) {
            throw new IllegalArgumentException("的所有属性均为null");
        }
    }

    /**
     * 检查Collection、Map、String不为null且不为空
     * 只能检查Collection、Map、String对象，其他对象无法检查
     *
     * @param obj 待检查对象
     */
    public static void notEmpty(Object obj) {
        notEmpty(obj, "object can not be null or empty");
    }

    /**
     * 检查Collection、Map、String不为null且不为空(只有空格的String也算空)
     * 只能检查Collection、Map、String对象，其他对象无法检查
     *
     * @param obj  待检查对象
     * @param desc 异常描述
     */
    public static void notEmpty(Object obj, String desc) {
        if ((null == obj) ||
                (obj instanceof Map && ((Map) obj).isEmpty()) ||                // 空map
                (obj instanceof String && ((String) obj).trim().isEmpty()) ||   // 空字符串
                (obj instanceof Collection && ((Collection) obj).isEmpty())     // 空集合
                ) {
            throw new IllegalArgumentException(desc);
        }
    }

    /**
     * 判断Collection、Map、String是否为空
     * @param obj Collection、Map、String类型的对象
     * @return 为空返回true
     */
    public static Boolean isEmpty(Object obj) {
        return ((null == obj) ||
                (obj instanceof Map && ((Map) obj).isEmpty()) ||                // 空map
                (obj instanceof String && ((String) obj).trim().isEmpty()) ||   // 空字符串
                (obj instanceof Collection && ((Collection) obj).isEmpty())     // 空集合
        );
    }
}
package com.xw.easypoi_test.anno;

import java.lang.annotation.*;

/**
 * @ClassName: CustomMerge
 * @Author: MaxWell
 * @Description: 自定义注解，用于判断是否需要合并以及合并的主键
 * @Date: 2022/7/4 15:33
 * @Version: 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CustomMerge {

    /**
     * 是否需要合并单元格
     */
    boolean needMerge() default false;

    /**
     * 是否是主键,即该字段相同的行合并
     */
    boolean isPk() default false;
}
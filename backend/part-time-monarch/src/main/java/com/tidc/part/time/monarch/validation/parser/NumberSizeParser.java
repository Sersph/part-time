//package com.tidc.parttimemonarch.validation.parser;
//
//import ResultException;
//import NumberSize;
//import ValidNumberSizeType;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//
//import javax.validation.Valid;
//import java.lang.reflect.Field;
//import java.lang.reflect.Parameter;
//import java.util.Arrays;
//
///**
// * @Author: 李家宝
// * @Date: 2018/10/29 22:54
// */
////@Aspect
//@Deprecated
//@Component
//public class NumberSizeParser {
//
//
//    /**
//     * 切到所有的类的所有方法获取到被 @Valid 注解到的对象
//     * 然后获取到被 @NumberSize 注解的成员变量的值
//     * 和 @NumberSize 里的元数据
//     * 交给分流器进行区分需要验证的是数字的长度还是数字的大小区间
//     * @param joinPoint
//     * @throws IllegalAccessException
//     */
//    @After("execution(* com.tidc.parttimemonarch..*.*(..))")
//    public void t(JoinPoint joinPoint){
//        // 获取参数对象
//        Object[] args = joinPoint.getArgs();
//        // 获取方法参数
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Parameter[] parameters = methodSignature.getMethod().getParameters();
//        for (Parameter parameter: parameters) {
//            if (parameter.isAnnotationPresent(Valid.class)){
//                Class<?> parameterClass = parameter.getType();
//                Object arg = Arrays.stream(args).filter(ar -> parameterClass.isAssignableFrom(ar.getClass())).findFirst().get();
//                // 得到参数的所有成员变量
//                Field[] fields = parameterClass.getDeclaredFields();
//                for (Field field : fields){
//
//                    if (field.isAnnotationPresent(NumberSize.class)){
//
//                        // 获取到被使用的注解
//                        NumberSize numberSize = field.getAnnotation(NumberSize.class);
//
//                        ValidNumberSizeType type = numberSize.type();
//
//                        // 开启 private 的访问权
//                        field.setAccessible(true);
//
//                        Object value;
//                        try {
//                            // 获取到 value 值
//                            value = field.get(arg);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            throw new ResultException(1007, "filed get int error");
//                        }
//
//                        this.shunt(value, numberSize);
//                    }else {
//                        continue;
//                    }
//                }
//            }else{
//                continue;
//            }
//        }
//    }
//
//
//    private void shunt(Object value, NumberSize numberSize){
//        switch (numberSize.type()){
//            case LENGTH:
//                this.isLength(value, numberSize);
//                break;
//
//            case INTERVAL:
//                this.isInterval(value, numberSize);
//                break;
//
//            default:
//                throw new ResultException(1007, "@NumberSize Type Error names:(" + numberSize.type() + ") don't exist!");
//        }
//    }
//
//    private void isLength(Object value, NumberSize numberSize){
//        int max = numberSize.max();
//        int min = numberSize.min();
//
//        String valueString = value + "";
//
//        if (valueString.length() == min || valueString.length() == max){
//            return;
//        }
//
//        if (valueString.length() > max || valueString.length()<min){
//            throw new ResultException(1006, numberSize.message());
//        }
//    }
//
//    private void isInterval(Object value, NumberSize numberSize){
//        int max = numberSize.max();
//        int min = numberSize.min();
//
//        int value2;
//
//        if (!value.getClass().getTypeName().equals("java.lang.Integer")){
//            Double va = (Double) value;
//            value2 = va.intValue();
//        }else {
//            value2 = (int) value;
//        }
//
//        if (value2 > max || value2 < min){
//            throw new ResultException(1006, numberSize.message());
//        }
//    }
//
//}

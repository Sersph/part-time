package com.tidc.parttimemonarch.handler;


import com.tidc.parttimemonarch.exception.ResultException;
import com.tidc.parttimemonarch.vo.RequestResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一异常处理
 * @Author: 李家宝
 */
@ControllerAdvice
public class PartTimeMonarchHandle {


    /**
     * 未知错误
     * @param e
     * @return ov
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RequestResult handle(Exception e){
        e.printStackTrace();

        return new RequestResult().error(1, "未知错误");
    }


    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public RequestResult result(BindException bindException){
        bindException.printStackTrace();
        List<ObjectError> errorList = bindException.getBindingResult().getAllErrors();
        String message;
        if (errorList.size() != 1) {
            List<String> errorsMessage = new ArrayList<>();
            errorList.forEach(error -> errorsMessage.add(error.getDefaultMessage()));
            String defaultDelimiter = "，";
            message = String.join(defaultDelimiter, errorsMessage);
        } else {
            message = errorList.get(0).getDefaultMessage();
        }


        return new RequestResult().error(1006, message);
    }


    @ExceptionHandler(value = ResultException.class)
    @ResponseBody
    public RequestResult resultHandle(ResultException e){
        System.err.println("e.getMessage()" + e.getMessage());
        return new RequestResult().error(e.getCode(), e.getMessage());
    }
}

package com.tidc.parttimemonarch.error;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.RequestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 全局异常处理器
 * <code>ControllerAdvice的类注解</code>用于捕捉全局异常，进行统一异常处理
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理，实际为捕捉全局异常
     *
     * @param exception 全局异常
     * @return 具体异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RequestResult> handlerException(Exception exception) {
        //当未知异常发生时，将信息弹出堆栈
        exception.printStackTrace();
        return new ResponseEntity<>(new RequestResult(Code.ERROR.getCode(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理 Spring boot 注解校验异常 并改为 自定义异常信息
     *
     * @param bindException Spring boot 注解校验异常
     * @return 自定具体异常处理结果
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<RequestResult> handlerMethodArgumentNotValidException(BindException bindException) {
        List<ObjectError> errorList = bindException.getBindingResult().getAllErrors();
        String message;
        if (errorList.size() != 1) {
            List<String> errorsMessage = new ArrayList<>();
            errorList.forEach(error -> errorsMessage.add(error.getDefaultMessage()));
            String defaultDelimiter = " AND ";
            message = "Multiple errors: " + String.join(defaultDelimiter, errorsMessage);
        } else {
            message = errorList.get(0).getDefaultMessage();
        }
        String[] status = message.split(" ");
        return new ResponseEntity<>(new RequestResult(Integer.valueOf(status[0]),status[1]), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
package com.tidc.parttimemonarch.handle;


import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.vo.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class PartTimeMonarchHandle {


    @Autowired
    @Qualifier("requestResult")
    private RequestResult result;


    /**
     * 未知错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RequestResult handle(Exception e){
        e.printStackTrace();
        this.result.error(1, "未知错误");
        return this.result;
    }


    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public RequestResult result(BindException bindException){

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
        this.result.error(1006, message);
        return this.result;
    }


    @ExceptionHandler(value = ResultExceptions.class)
    @ResponseBody
    public RequestResult resultHandle(ResultExceptions e){

        this.result.error(e.getCode(), e.getMessage());

        return this.result;
    }


}

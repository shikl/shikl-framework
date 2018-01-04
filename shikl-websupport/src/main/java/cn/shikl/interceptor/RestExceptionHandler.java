package cn.shikl.interceptor;

import cn.shikl.model.Messages;
import cn.shikl.support.constants.ExceptionCode;
import cn.shikl.support.exceptions.ShiklException;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by yangcm on 2017/1/23.
 */
@ControllerAdvice
public class RestExceptionHandler {

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Messages ioExceptionHandler(IOException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ExceptionCode.IOException.message());
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(ExceptionCode.IOException.code());
        return messages;
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public Messages classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ExceptionCode.ClassCastException.message());
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(ExceptionCode.ClassCastException.code());
        return messages;
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Messages indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ExceptionCode.IndexOutOfBoundsException.message());
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(ExceptionCode.IndexOutOfBoundsException.code());
        return messages;
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public Messages noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ExceptionCode.NoSuchMethodException.message());
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(ExceptionCode.NoSuchMethodException.code());
        return messages;
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Messages nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ExceptionCode.NullPointerException.message());
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(ExceptionCode.NullPointerException.code());
        return messages;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Messages authenticationExceptionHandler(AuthenticationException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ex.getMessage());
        messages.setHttpCode(HttpStatus.UNAUTHORIZED.value());
        messages.setCode(ExceptionCode.AuthenticationException.code());
        return messages;
    }


    //业务异常
    @ExceptionHandler(ShiklException.class)
    @ResponseBody
    public Messages shiklExceptionHandler(ShiklException ex) {
        ex.printStackTrace();
        Messages messages = Messages.failure(ex.getMessage());
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(ex.getBusi() + ex.getCode());
        return messages;
    }

    public Messages getMessage(String code , String message){
        Messages messages = Messages.failure(message);
        messages.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messages.setCode(code);
        return messages;
    }

}

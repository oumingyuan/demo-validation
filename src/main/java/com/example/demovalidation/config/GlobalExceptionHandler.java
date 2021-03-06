package com.example.demovalidation.config;

import com.example.demovalidation.model.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 * 把后端异常传递到前端
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
//    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
//    @ExceptionHandler(AuthorizationException.class)
//    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
//        //log.error(e.getMessage(), e);
//        if (ServletUtils.isAjaxRequest(request)) {
//            return AjaxResult.unauth(PermissionUtils.getMsg(e.getMessage()));
//        } else {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.setViewName("error/unauth");
//            return modelAndView;
//        }
//    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public AjaxResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return AjaxResult.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("服务器错误，请联系管理员");
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult exceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("!");
        }
        return AjaxResult.error(errorMessage.toString());
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("!");
        }
        return AjaxResult.error(errorMessage.toString());


    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public AjaxResult ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return AjaxResult.error(String.join(",", msgList));
    }

    /**
     * 业务异常
     */
//    @ExceptionHandler(BusinessException.class)
//    public AjaxResult businessException(BusinessException e) {
//        log.error(e.getMessage(), e);
//        return AjaxResult.error(e.getMessage());
//    }

    /**
     * 演示模式异常
     */
//    @ExceptionHandler(DemoModeException.class)
//    public AjaxResult demoModeException(DemoModeException e) {
//        return AjaxResult.error("演示模式，不允许操作");
//    }

}

package com.example.demovalidation.controller;


import com.example.demovalidation.model.ResultInfo;
import com.example.demovalidation.model.User;
import com.example.demovalidation.model.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            // 日志中输出所有请求头
            log.info(String.format("Header '%s' = %s", key, value));
        });
        return "hello world";
    }

    @PostMapping("validate")
//    @Validated
    public Map<Object, Object> validate2(@Validated @RequestBody User user, BindingResult bindingResult) {

        // 参数校验
        if (bindingResult.hasErrors()) {
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            //这里可以抛出自定义异常,或者进行其他操作
            throw new IllegalArgumentException(messages);
        }
//    public User validate2(@Valid @RequestBody User user, BindingResult bindingResult) {

        Map<Object, Object> res = new HashMap<>();
        if (bindingResult.hasErrors()) {
            res.put("status", 400);
            res.put("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        } else {
            res.put("status", 200);
            res.put("msg", "ok");
            res.put("data", user);
        }
        return res;

    }

    @PostMapping("add1")
    public String add1(@Validated @RequestBody UserVO userVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            return fieldErrors.get(0).getDefaultMessage();
        }
        return "OK";
    }

    @PostMapping("add2")
    public ResultInfo<List<String>> add2(@Validated @RequestBody UserVO userVO, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResultInfo<List<String>>().success(200, "请求成功", collect);
    }

    @PostMapping("add3")
    public ResultInfo<String> add3(@Validated @RequestBody UserVO userVO, BindingResult result) {

        return new ResultInfo<String>().success(200, "请求成功", "aa");
    }

}

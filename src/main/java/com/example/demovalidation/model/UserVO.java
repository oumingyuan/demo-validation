package com.example.demovalidation.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserVO {
    @NotNull(message = "age 不能为空")
    private Integer age;
}

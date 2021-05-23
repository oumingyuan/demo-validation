package com.example.demovalidation.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class Person {
    /**
     * @PersonName(prefix = "song")：自定义注解
     */
    @NotBlank
//    @PersonName(prefix = "song")
    private String name;
    @Min(value = 18)
    @Max(value = 30, message = "超过30岁的不要")
    private Integer age;

}

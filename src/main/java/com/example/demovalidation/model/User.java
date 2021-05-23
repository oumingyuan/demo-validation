package com.example.demovalidation.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class User {
    @Size(min = 1, max = 10, message = "姓名长度必须为1到10")
    private String name;
//
//    @NotEmpty
//    private String firstName;
//
    @Min(value = 10,message = "年龄最小为10")@Max(value = 100,message = "年龄最大为100")
    private Integer age;

//    @Future
//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
//    private Date birth;

}

package com.imooc.controller;

import com.imooc.mapper.StuMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@ApiIgnore
public class TextController {
    @Resource
    private StuMapper stuMapper;

    @RequestMapping("/v1/helloWorld")
    public Object hello() {
        return stuMapper.selectAll();
    }
}

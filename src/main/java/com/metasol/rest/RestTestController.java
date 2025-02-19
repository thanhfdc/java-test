package com.metasol.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/tests")
public class RestTestController {

    @GetMapping("")
    public  String getTest() {
        String test = "Hello world 333";
        return test;
    }
}

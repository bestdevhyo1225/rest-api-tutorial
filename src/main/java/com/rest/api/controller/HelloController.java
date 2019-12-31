package com.rest.api.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Setter
    @Getter
    public static class Hello {
        private String message;
    }

    // 1. 화면에 Hello World 출력
    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public String helloworldString() {
        return "Hello World";
    }

    // 2. 화면에 { message: "Hello World" } 출력
    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "Hello World";
        return hello;
    }

    // 3. 화면에 helloworld.ftl의 내용이 출력
    @GetMapping(value = "/helloworld/page")
    public String helloworld() {
        return "helloworld";
    }
}

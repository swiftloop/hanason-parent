package com.framework.hanason.app.controller;

import com.framework.hanason.app.service.AddUserService;
import com.framework.hanason.common.DefaultValueUtil;
import com.framework.hanason.web.controller.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sorata 2020-05-07 14:58
 */
@RestController

public class UserController extends AbstractApiController {

    @Autowired
    private AddUserService addUserService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        addUserService.add();
        String value = DefaultValueUtil.value("cc", "ee");
        System.out.println(value);
        return "hello" + name;
    }


    public static void main(String[] args) {
        String name = null;
        name = "cc";
        String hello = DefaultValueUtil.value(name, "hello");
        System.out.println(hello);
    }


}

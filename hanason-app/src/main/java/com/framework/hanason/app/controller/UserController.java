package com.framework.hanason.app.controller;

import com.framework.hanason.app.service.AddUserService;
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

        return "hello" + name;
    }


}

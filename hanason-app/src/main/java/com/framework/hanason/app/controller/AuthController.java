package com.framework.hanason.app.controller;

import com.framework.hanason.web.user.AuthorizationApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sorata 2020-05-18 15:20
 */
@RestController
@RequestMapping("/ops")
@AuthorizationApi
public class AuthController {


    @GetMapping("/info")
    public String info(){
        return "info";
    }


    @GetMapping("/info2")
    public String info2(){
        return "info2";
    }

}

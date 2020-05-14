package com.framework.hanason.admin.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author sorata
 */
@Controller
@RequestMapping("/manager")
public class PageController {

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/main.html")
    public String main(){
        return "main";
    }

}

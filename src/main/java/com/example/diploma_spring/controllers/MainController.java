package com.example.diploma_spring.controllers;

import com.example.diploma_spring.services.MainService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    private MainService mainService;

    @Autowired
    public MainController(MainService mainService){
        this.mainService = mainService;
    }

    @GetMapping()
    public String mainPage(@NotNull Model model, @NotNull HttpServletRequest request) {
        model.addAttribute("ssouser", request.getSession().getAttribute("ssouser"));
        return "main";
    }

    @PostMapping("/")
    public String smth() {
        return "";
    }
}

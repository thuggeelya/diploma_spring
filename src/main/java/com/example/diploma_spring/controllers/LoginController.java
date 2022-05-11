package com.example.diploma_spring.controllers;

import com.example.diploma_spring.data.User;
import com.example.diploma_spring.exceptions.MyLoginException;
import com.example.diploma_spring.services.LoginService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/sso")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping()
    public String login(@NotNull Model model) {
//        logger.info("GET /login returns login.html");
        model.addAttribute("ssouser", new User());
        return "login";
    }

    @PostMapping("/auth")
    public String authenticate(@ModelAttribute("ssouser") User user, HttpServletRequest request) throws MyLoginException {
        if (loginService.authenticate(user)) {
            Logger.getLogger(LoginController.class.getName()).info(user.getPerson().getClass().getName());
            request.getSession().setAttribute("ssouser", user); // session user
//            logger.info("login OK redirect to main page");
            return "redirect:/main";
        } else {
//            logger.info("login FAIL redirect back to login");
            throw new MyLoginException("Сбой аутентификации. Повторите попытку");
        }
    }
}
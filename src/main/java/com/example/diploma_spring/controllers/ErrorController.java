package com.example.diploma_spring.controllers;

import com.example.diploma_spring.data.User;
import com.example.diploma_spring.exceptions.MyLoginException;
import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(MyLoginException.class)
    public String handleError(@NotNull Model model, @NotNull MyLoginException myLoginException) {
        model.addAttribute("ssouser", new User());
        model.addAttribute("errorMessage", myLoginException.getMessage());
        return "login";
    }
}
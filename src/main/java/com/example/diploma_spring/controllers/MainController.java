package com.example.diploma_spring.controllers;

import com.example.diploma_spring.services.MainService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    @Getter
    @AllArgsConstructor
    private enum Types {
        TP("Творческий проект"), PR("Научно-исследовательский проект");
        private final String name;
    }

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @ModelAttribute("tpCount")
    public long tpCount() {
        return mainService.countAllByTypeName(Types.TP.name);
    }

    @ModelAttribute("prCount")
    public long prCount() {
        return mainService.countAllByTypeName(Types.PR.name);
    }

    @ModelAttribute("tp1Count")
    public long tp1Count() {
        return mainService.countActualByTypeNameAndCourse(Types.TP.name, 1);
    }

    @ModelAttribute("tp2Count")
    public long tp2Count() {
        return mainService.countActualByTypeNameAndCourse(Types.TP.name, 2);
    }

    @ModelAttribute("pr2Count")
    public long pr2Count() {
        return mainService.countActualByTypeNameAndCourse(Types.PR.name, 2);
    }

    @ModelAttribute("pr3Count")
    public long pr3Count() {
        return mainService.countActualByTypeNameAndCourse(Types.PR.name, 3);
    }

    @ModelAttribute("pr4Count")
    public long pr4Count() {
        return mainService.countActualByTypeNameAndCourse(Types.PR.name, 4);
    }

    @GetMapping()
    public String mainPage(@NotNull Model model, @NotNull HttpServletRequest request) {
        model.addAttribute("ssouser", request.getSession().getAttribute("ssouser"));
        return "main";
    }

    @PostMapping("/")
    public String smth() {
        return "main";
    }
}

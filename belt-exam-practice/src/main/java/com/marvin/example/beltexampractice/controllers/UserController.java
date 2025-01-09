package com.marvin.example.beltexampractice.controllers;

import com.marvin.example.beltexampractice.models.LoginUser;
import com.marvin.example.beltexampractice.models.User;
import com.marvin.example.beltexampractice.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult, Model model, HttpSession session) {
        userService.validateAndRegister(newUser, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }

        session.setAttribute("userId", newUser.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser loginUser, BindingResult bindingResult, Model model,  HttpSession session) {
        User user = userService.login(loginUser, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }

        session.setAttribute("userId", user.getId());
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/";
    }

}
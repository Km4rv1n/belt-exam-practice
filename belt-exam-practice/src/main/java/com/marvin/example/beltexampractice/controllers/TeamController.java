package com.marvin.example.beltexampractice.controllers;

import com.marvin.example.beltexampractice.models.Team;
import com.marvin.example.beltexampractice.models.User;
import com.marvin.example.beltexampractice.services.TeamService;
import com.marvin.example.beltexampractice.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    private final UserService userService;
    private final TeamService teamService;

    public TeamController(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    @GetMapping ("/teams/new")
    public String newTeam(@ModelAttribute Team team) {
        return "form";
    }

    @PostMapping ("/teams/add")
    public String addTeam(BindingResult bindingResult, @ModelAttribute @Valid Team team, HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        if (bindingResult.hasErrors()) {
            return "index";
        }
        Integer userId = (Integer) session.getAttribute ("userId");
        User currentLoggedUser = userService.findUserById(userId);

        team.setCreatedBy(currentLoggedUser);
        teamService.saveNewTeam(team);
        return ""; // return to dashboard
    }

}

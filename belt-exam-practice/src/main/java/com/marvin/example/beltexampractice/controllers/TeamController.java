package com.marvin.example.beltexampractice.controllers;

import com.marvin.example.beltexampractice.models.Player;
import com.marvin.example.beltexampractice.models.Team;
import com.marvin.example.beltexampractice.models.User;
import com.marvin.example.beltexampractice.repositories.TeamRepository;
import com.marvin.example.beltexampractice.services.PlayerService;
import com.marvin.example.beltexampractice.services.TeamService;
import com.marvin.example.beltexampractice.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeamController {

    private final UserService userService;
    private final TeamService teamService;
    private final PlayerService playerService;

    public TeamController(UserService userService, TeamService teamService, PlayerService playerService) {
        this.userService = userService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping ("/teams/new")
    public String newTeam(@ModelAttribute Team team) {
        return "form";
    }

    @GetMapping ("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        Integer userId = (Integer) session.getAttribute ("userId");
        User currentLoggedUser = userService.findUserById(userId);
        model.addAttribute("user", currentLoggedUser);
        return "dashboard";
    }

    @PostMapping ("/teams/add")
    public String addTeam(@ModelAttribute @Valid Team team, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        if (bindingResult.hasErrors()) {
            return "form";
        }
        Integer userId = (Integer) session.getAttribute ("userId");
        User currentLoggedUser = userService.findUserById(userId);

        team.setCreatedBy(currentLoggedUser);
        teamService.saveNewTeam(team);
        return "dashboard"; // return to dashboard
    }

    @GetMapping("/team/{id}")
    public String viewTeam(@PathVariable Long id, Model model,HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        model.addAttribute("player", new Player());
        return "details";
    }


    @GetMapping ("/team/edit/{id}")
    public String editTeam(@PathVariable Long id, Model model,  HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        return "edit";
    }

    @PutMapping("/team/modify/{id}")
    public String addTeam(@PathVariable Long id, @ModelAttribute @Valid Team team, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        Team existingTeam = teamService.findById(id);
        team.setCreatedBy(existingTeam.getCreatedBy());
        teamService.saveNewTeam(team);
        return "redirect:/dashboard";
    }

    @GetMapping("/team/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute ("userId" ) == null) {
            return "index";
        }
        teamService.deleteById(id);
        return "redirect:/dashboard";
    }


    @PutMapping("/teams/add-player/{id}")
    public String addPlayer(@PathVariable Long id, @ModelAttribute @Valid Player player, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "index";
        }
        if (bindingResult.hasErrors()) {
            return "details";
        }

        Player existingPlayer = playerService.save(player);
        Team existingTeam = teamService.findById(id);

        if (existingTeam == null) {
            bindingResult.rejectValue("id", "error.team", "Team not found");
            return "details";
        }

        existingTeam.getPlayers().add(existingPlayer);
        teamService.saveNewTeam(existingTeam);

        return "redirect:/dashboard";
    }

}

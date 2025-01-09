package com.marvin.example.beltexampractice.services;

import com.marvin.example.beltexampractice.models.Team;
import com.marvin.example.beltexampractice.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team saveNewTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}

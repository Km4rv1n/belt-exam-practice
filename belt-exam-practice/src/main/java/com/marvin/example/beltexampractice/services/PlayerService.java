package com.marvin.example.beltexampractice.services;

import com.marvin.example.beltexampractice.models.Player;
import com.marvin.example.beltexampractice.models.Team;
import com.marvin.example.beltexampractice.repositories.PlayerRepository;
import com.marvin.example.beltexampractice.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository teamRepository) {
        this.playerRepository = teamRepository;
    }


    public Player save(Player player) {
        return playerRepository.save(player);
    }

}

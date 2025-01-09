package com.marvin.example.beltexampractice.models;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;

import java.util.*;

import com.marvin.example.beltexampractice.enums.GameDay;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Teams must have a name")
    private String name;

    @NotNull
    @Min(1)
    @Max(5)
    private int skillLevel;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameDay gameDay;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "team_player",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> players = new HashSet<Player>();
    
    
    public Team(){

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public GameDay getGameDay() {
        return this.gameDay;
    }

    public void setGameDay(GameDay gameDay) {
        this.gameDay = gameDay;
    }

    public int getSkillLevel() {
        return this.skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

}

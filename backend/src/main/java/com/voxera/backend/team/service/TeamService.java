package com.voxera.backend.team.service;

import com.voxera.backend.team.entity.Team;
import com.voxera.backend.team.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public Team getTeam(UUID teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Team not found: " + teamId));
    }
}

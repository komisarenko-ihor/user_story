package tech_task.user_story.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech_task.user_story.domain.web.CreatePlayerRequest;
import tech_task.user_story.domain.web.PlayerResponse;
import tech_task.user_story.mappers.PlayerMapper;
import tech_task.user_story.repositories.PlayerRepository;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;


    public PlayerResponse create(CreatePlayerRequest playerRequest) {
        return playerMapper.playerToPlayerResponse(playerRepository.save(playerMapper.playerRequestToPlayer(playerRequest)));
    }
}

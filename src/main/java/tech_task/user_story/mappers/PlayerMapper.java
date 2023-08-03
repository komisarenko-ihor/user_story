package tech_task.user_story.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech_task.user_story.domain.model.Player;
import tech_task.user_story.domain.web.CreatePlayerRequest;
import tech_task.user_story.domain.web.PlayerResponse;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Player playerRequestToPlayer(CreatePlayerRequest playerRequest);

    PlayerResponse playerToPlayerResponse(Player player);
}

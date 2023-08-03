package tech_task.user_story.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech_task.user_story.domain.model.SessionPlayer;

@Mapper(componentModel = "spring")
public interface SessionPlayerMapper {

    @Mapping(target = "sessionPlayerId.playerId", source = "playerId")
    @Mapping(target = "sessionPlayerId.sessionId", source = "sessionId")
    SessionPlayer toSessionPlayer(String playerId, String sessionId);
}

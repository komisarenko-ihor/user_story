package tech_task.user_story.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech_task.user_story.domain.model.Session;
import tech_task.user_story.domain.web.CreateSessionRequest;
import tech_task.user_story.domain.web.SessionResponse;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "player.id", source = "playerId")
    @Mapping(target = "deskType.id", source = "createSessionRequest.deskTypeId")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Session createSessionRequestToSession(String playerId, CreateSessionRequest createSessionRequest);

    @Mapping(target = "deskTypeId", source = "deskType.id")
    @Mapping(target = "playerId", source = "player.id")
    SessionResponse sessionToSessionResponse(Session session);
}

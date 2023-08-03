package tech_task.user_story.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech_task.user_story.domain.model.UserStory;
import tech_task.user_story.domain.web.CreateUserStoryRequest;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "session.id", source = "sessionId")
    @Mapping(target = "player.id", source = "playerId")
    @Mapping(target = "status", constant = "PENDING")
    UserStory toUserStory(String sessionId, String playerId, CreateUserStoryRequest request);
}

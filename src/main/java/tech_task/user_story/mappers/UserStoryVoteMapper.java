package tech_task.user_story.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech_task.user_story.domain.model.UserStoryVote;
import tech_task.user_story.domain.web.VoteRequest;

@Mapper(componentModel = "spring")
public interface UserStoryVoteMapper {

    @Mapping(target = "userStoryVoteId.userStoryId", source = "userStoryId")
    @Mapping(target = "userStoryVoteId.sessionId", source = "sessionId")
    @Mapping(target = "userStoryVoteId.playerId", source = "playerId")
    UserStoryVote toUserStoryVote(String sessionId, String userStoryId, String playerId, VoteRequest request);
}

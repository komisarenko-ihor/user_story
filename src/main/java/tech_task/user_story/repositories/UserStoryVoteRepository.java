package tech_task.user_story.repositories;

import org.springframework.data.repository.CrudRepository;
import tech_task.user_story.domain.model.UserStoryVote;
import tech_task.user_story.domain.model.UserStoryVoteId;

import java.util.List;

public interface UserStoryVoteRepository extends CrudRepository<UserStoryVote, UserStoryVoteId> {
    List<UserStoryVote> findByUserStoryVoteId_UserStoryIdIn(List<String> sessionIds);
    void deleteAllByUserStoryVoteId_SessionId(String sessionId);
    void deleteAllByUserStoryVoteId_UserStoryId(String userStoryId);



}
